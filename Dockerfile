FROM eclipse-temurin:17-jdk

ENV APP_ENV=production

WORKDIR /app

COPY /app/ .

RUN gradle installDist

CMD ./build/install/app/bin/app