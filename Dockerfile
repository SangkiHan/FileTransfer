FROM openjdk:17-oracle
RUN microdnf install findutils

RUN mkdir -p /home/project/FileTransfer
WORKDIR /home/project/FileTransfer

COPY . /home/project/FileTransfer

RUN ./gradlew clean
RUN ./gradlew bootWar

ENTRYPOINT java -Duser.timezone=GMT+09:00 -jar /home/project/FileTransfer/build/libs/FileTransfer-0.0.1-SNAPSHOT.war