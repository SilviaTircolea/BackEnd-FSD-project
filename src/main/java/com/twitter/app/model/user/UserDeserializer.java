package com.twitter.app.model.user;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserDeserializer extends StdDeserializer<User> {

    private static final Logger LOG = LoggerFactory.getLogger(UserDeserializer.class);

    protected UserDeserializer() {
        this(null);
    }

    protected UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        User user = new User();
        try {
            user.setUserId(node.get("userId").asText());
        } catch (NullPointerException e) {
            LOG.info("User ID is null");
        }
        try {
            user.setPassword(node.get("password").asText());
        } catch (NullPointerException e) {
            LOG.info("Password ID is null");
        }

        try {
            user.setFirstName(node.get("firstName").asText());
        } catch (NullPointerException e) {
            LOG.info("First name is null");
        }

        try {
            user.setProfilePicture(node.get("profilePicture").asText());
        } catch (NullPointerException e) {
            LOG.info("Profile Picture is null");
        }

        try {
            user.setBio(node.get("bio").asText());
        } catch (NullPointerException e) {
            LOG.info("Bio is null");
        }

        try {
            user.setLastName(node.get("lastName").asText());
        } catch (NullPointerException e) {
            LOG.info("Last name is null");
        }
        return user;
    }
}
