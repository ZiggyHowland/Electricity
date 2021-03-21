# Database migration
## Flyway
### Documentation 
https://flywaydb.org/documentation/usage/maven/migrate
### Project commands
```console
mvn -Dflyway.configFiles=src/main/resources/myFlywayConfig.properties flyway:migrate
mvn -Dflyway.configFiles=src/main/resources/myFlywayConfig.properties flyway:clean
```
Notes (Important)
- Command is run from the project root folder
- sql-script folder is hardcoded in the myFlywayConfig.properties-file




