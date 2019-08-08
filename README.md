# passwort-manager-dev

# Notes
## Project Structure
Following project structure is needed.
```bash    
.    
├── out 
|   └── production   
|       └── project_name   
|           └── packages_and_class_files   
├── res   
|   ├── default.toml   
|   ├── config.toml   
|   └── web    
|       └── html_files   
├── src 
|   └── main_package_!important
|       └── other_packages_and_classes 
```

## Naming conventions
Classes can for now not end with `Kt` or `$1` (because kotlin creates `[className](Kt|$1).class` files).

## TOML-Parser
Supports:
* Boolean, Float, Integer, String and Array-Datatypes
* Arrays can be nested
* Multiline-Array initialization is not supported
* Sections and sub-sections are supported
* Full-Line and End-Line comments are supported

## MongoDB
MongoDB is used as a database, therefore the mongo-java-driver is needed. You can find him [here](http://central.maven.org/maven2/org/mongodb/mongo-java-driver/).
