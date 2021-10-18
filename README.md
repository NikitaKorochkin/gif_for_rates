# Gif For Rates
---
## Description
---
### This is a web service intagrated with two APIs. It returns a random ```ResponseEntity<byte[]> obj``` of gif file according of the ratio of the exchange rate to "RUB".
---
### Exchange REST API: 

#### https://docs.openexchangerates.org/ 

### Gif REST API:
---
#### https://developers.giphy.com/docs/api#quick-start-guide
---
## Stack
### Java 17, Spring Boot Spring Cloud (Feign Client), Spring MVC, Mockito, Junit 5, Gradle, Docker.
---
## Endponits
#### `GET gif-for-rate/gif?code='rate'`
---
## Start
1. #### `gradle build`
2. #### `docker build -t gif-for-rates`
3. #### `docker run --name gif-for-rate -p 8080:8080 -d gif-for-rates`
