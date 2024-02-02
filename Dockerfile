FROM gradle:7.4.2-jdk17

ENV APP_ENV=production

WORKDIR /app

COPY /app/ .

RUN gradle build

CMD ./build/install/app/bin/app