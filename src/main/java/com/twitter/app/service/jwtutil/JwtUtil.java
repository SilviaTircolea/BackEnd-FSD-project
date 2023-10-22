package com.twitter.app.service.jwtutil;

import com.twitter.app.dao.common.CommonDAO;
import com.twitter.app.model.user.User;
import com.twitter.app.model.user.UserCommon;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service("JwtUtil")
public class JwtUtil {

    private final String SECRET_KEY = "secret";

    private final CommonDAO commonDAO;
    private final Environment environment;

    @Autowired
    public JwtUtil(CommonDAO commonDAO, Environment environment) {
        this.commonDAO = commonDAO;
        this.environment = environment;
    }

    public String extractUsername(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) throws SQLException {
        return this.extractExpiration(token).before(commonDAO.getCurrentTimestamp());
    }

    public String generateToken(User user) throws SQLException {
        Map<String, Object> claims = new HashMap<>();
        return this.createToken(claims, user.getUserId(), TimeUnit.MINUTES.toMillis(Long.valueOf(environment.getProperty("application.user.token.timeout.minutes"))));
    }

    public String generateAppToken(UserCommon userCommon) throws SQLException {
        Map<String, Object> claims = new HashMap<>();
        TimeUnit.SECONDS.toMillis(30);
        return this.createToken(claims, userCommon.getUser().getUserId(), TimeUnit.SECONDS.toMillis(Long.valueOf(environment.getProperty("application.authentication.token.timeout.seconds"))));
    }

    private String createToken(Map<String, Object> claims, String subject, long timeOut) throws SQLException {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(commonDAO.getCurrentTimestamp())
                .setExpiration(new Date(commonDAO.getCurrentTimestamp().getTime() + timeOut))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, User user) throws SQLException {
        final String username = extractUsername(token);
        return (username.equals(user.getUserId()) && !this.isTokenExpired(token));
    }
}
