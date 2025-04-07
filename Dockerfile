FROM amazoncorretto
LABEL authors="BriarCarlie"

WORKDIR /code

COPY . ./LEGOInventoryApp

ENTRYPOINT ["top", "-b"]