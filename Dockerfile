FROM amazoncorretto:11-alpine
EXPOSE 8070

RUN addgroup -S --gid 2000 afdlgdmsgroup && adduser -S afdlgdmsuser -G afdlgdmsgroup
RUN mkdir -p /afdlgdmsapp
RUN chown -R afdlgdmsuser /afdlgdmsapp
USER afdlgdmsuser
WORKDIR /afdlgdmsapp

ADD target/com-twitter-app-1.0.0.jar com-twitter-app.jar

ENV TZ=Asia/Colombo
ENV enabledTLSProtocols=TLSv1.2

ENTRYPOINT ["java", "-jar", "com-twitter-app.jar"]