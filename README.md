# Mongeez Client

This project simplifies the usage of the [mongeez](https://github.com/mongeez/mongeez) - a mongodb migration tool.

Features:

- No need to mantain a "mongeez.xml" file.
- Dockerized

## Usage as a jar:

```java 
java -jar mongeez-cli-0.0.1.jar [OPTIONS]
```

## Options:

-help: Show all options
-host
-port
-user
-password
-db

### Example:
`java -jar mongeez-cli-0.0.1.jar -host=another_host -port=27018 -db=mydb -path=/path/to/migrations/folder/`

See the docker-compose.yml located in this project to see an dockerized usage example.
