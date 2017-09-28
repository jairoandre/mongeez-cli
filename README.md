# Mongeez Client

MongoDB migration Tool.

This project simplifies the usage of the [mongeez](https://github.com/mongeez/mongeez).

### Features:

- No need to mantain a "mongeez.xml" file.
- Dockerized.

## Usage as a jar:

```java 
java -jar mongeez-cli-0.0.1.jar [OPTIONS]
```

## Options:

- help: Show all options
- path: Path to the migration files folder (default: . )
- host: MongoDB host (default: localhost)
- port: MongoDB port (default: 27017)
- user: MongoDB user (optional)
- password: MongoDB password (optional)
- db: database name

### Example:

`java -jar mongeez-cli-0.0.1.jar -host=another_host -port=27018 -db=mydb -path=/path/to/migrations/folder/`

See the docker-compose.yml located in this project to see a "dockerized" usage example.

## Docker

`docker pull jairoandre/mongeez-cli`
