#FROM java:8
#VOLUME /tmp
#EXPOSE 10222
#ADD /build/libs/store-0.0.1-SNAPSHOT.jar store-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","store-0.0.1-SNAPSHOT.jar"]
#


FROM openjdk:8-jdk-alpine AS build
WORKDIR /workspace/app
#COPY . /workspace/app
COPY build/libs/store-0.0.1-SNAPSHOT.jar /workspace/app
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../../*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
RUN sed -i "s|localhost:6033|docker-mysql|g" /app/application-cold-box.yml
ENTRYPOINT ["java","-cp","app:app/lib/*","com.castillo.pavel.store.StoreApplication"]

