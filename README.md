# passwort-manager-dev

# TODO
- [ ] Autherization header + session + cookie
- [ ] Terminator-service
- [ ] cookie session key
- [ ] ssl certificate renew -> swap socket?
- [x] redirect http->https

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

## Security concept
* Use https for tls encryption
* Login via Authorization header
* With successfull login -> send back valid session cookie
* Logout or timeout -> delete session cookie

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
