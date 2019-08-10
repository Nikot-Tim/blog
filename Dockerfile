FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD target/blog-1.0-SNAPSHOT.jar blog-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","blog-1.0-SNAPSHOT.jar"]