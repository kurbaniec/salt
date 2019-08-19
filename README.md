# passwort-manager-dev

# TODO
- [ ] Database-mongo wrapper for salt
- [ ] convert login in script to th:login & update README
- [ ] Config default page when logged on
- [ ] Terminator-service
- [ ] ssl certificate renew -> swap socket?
- [x] Thymeleaf Parser?
- [x] Autherization header + session + cookie
- [x] cookie session key
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

## Naming conventions
Classes can for now not end with `Kt` or `$1` (because kotlin creates `[className](Kt|$1).class` files).

## Security concept
* Use https for tls encryption
* Login via Authorization header
* With successfull login -> send back valid session cookie
* Logout or timeout -> delete session cookie

## HTML-Template Engine
The custom template parser is inspired by Thymeleaf and supports for now following functionality:  
* ```js
  <span th:text="Hello, ${message}"></span>
  ```   
  The `th:text` attribute will replace the value between tags eg. `span`. `${message}` stands for an object that
  is added in the controller via `model.addAttribute("message", "baum")`. So the ouput, when parsed will be `<span>Hello, baum</span>`.
  If baum is for example not a String but an other object like `User` added as `user` with a attribute `login`, you can also acces it     via `${user.login}`.
  
* ```js
  <a th:href="@{/some/path/${testo}}">Link!</a>
  ```
  Creates a link that can refer to other pages or resources of the application. As seen, you can add also models like `${testo}` that     will be resolved to create a dynamic link.
  
* ```js
  <th:block th:each="user : ${users}">
    <tr style="border: 1px solid black">
        <td style="border: 1px solid black" th:text="${user.login}">...</td>
        <td th:text="${user.name}">...</td>
    </tr>
    <tr>
        <td th:text="${user.address}">...</td>
    </tr>
  </th:block>
  ```   
  Creates a loop of a block marked with `th:block`. `users` is a list added via a model in the controller. For every entry of `users`     marked as `user` the block will be dynamically created. Note: No `<th:block>` will be seen on the parsed site.

## TOML-Parser (for config-files)
Supports:
* Boolean, Float, Integer, String and Array-Datatypes
* Arrays can be nested
* Multiline-Array initialization is not supported
* Sections and sub-sections are supported
* Full-Line and End-Line comments are supported

## MongoDB
MongoDB is used as a database, therefore the mongo-java-driver is needed. You can find him [here](http://central.maven.org/maven2/org/mongodb/mongo-java-driver/).
