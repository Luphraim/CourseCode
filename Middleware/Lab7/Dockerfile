FROM hub.c.163.com/library/java:latest
VOLUME /tmp

COPY eureka/target/eureka-0.0.1-SNAPSHOT.jar /eureka.jar
COPY alumni/target/alumni-0.0.1-SNAPSHOT.jar /alumni.jar
COPY user/target/user-0.0.1-SNAPSHOT.jar /user.jar
COPY zuul/target/zuul-0.0.1-SNAPSHOT.jar /zuul.jar


COPY start_cloud.sh /usr/bin/start_cloud.sh
RUN chmod +x /usr/bin/start_cloud.sh
CMD nohup sh -c "start_cloud.sh && java -jar /eureka.jar"