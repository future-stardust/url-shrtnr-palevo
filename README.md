# KPD - URL shortener :zap:


## Students group

- Давиденко Ілля ilya.davydenkoo@gmail.com
- Козирєв Олексій alexiskozyrev@gmail.com
- Пінькавський Богдан lordeldrus@gmail.com

## Design document

The [design document](https://docs.google.com/document/d/1LI5nTj62YYdrZC5BI8Hh1udQL9t_SMFt_q_7fhNJwr4/edit) that
describes architecture and implementation details of this project.

#### Main scenario endpoints

1. Sign up

```shell
curl --request POST \
  --url http://localhost:8080/users/signup \
  --header 'content-type: application/json' \
  --data '{
  "email": "aaa@example.com",
  "password": "passw000rd"
}' -v
```

2. Login

```shell
curl --request POST \
  --url http://localhost:8080/login \
  --header 'content-type: application/json' \
  --data '{
  "username": "aaa@example.com",
  "password": "passw000rd"
}' -v
```

3. Shorten URL

```shell
curl --request POST \
  --url http://localhost:8080/urls/shorten \
  --header 'authorization: Bearer <TOKEN FROM THE LOGIN RESPONSE>' \
  --header 'content-type: application/json' \
  --data '{
  "url": "https://github.com/future-stardust/url-shrtnr-palevo",
  "alias": "palevo"
}' -v
```

4. Redirect

```shell
curl --request GET --url http://localhost:8080/r/palevo -v
```

## Environment prerequisites

### Java
This is a Java project, so you will need an environment with installed [JDK] 15. For installation, 
you could use:
- [sdkman] on Linux/MacOS 
- [AdoptOpenJDK] on Windows

### IDE  
As IDE use [IntelliJ Idea Edu].

### Checkstyle
We use [checkstyle] to ensure coding standards. To get real-time detection in IDE you could use [Checkstyle-IDEA] 
plugin. We use Google rules (local copy `./config/checkstyle/checkstyle.xml`).

## How to start development

1. Clone this repo
2. Open the project directory in IntelliJ Idea Edu
3. Configure IDE code style settings
  
    1. Open `Settings`
    2. Go to `Editor` -> `Code Style` -> `Import Scheme`
       ![Settings screenshot](./media/code-style-import.png)
    3. Import scheme from `./config/idea/intellij-java-google-style.xml`

## Commit messages

Write commit messages accordingly by [7 rules of good commit messages].
  
[JDK]: https://en.wikipedia.org/wiki/Java_Development_Kit
[IntelliJ Idea Edu]: https://www.jetbrains.com/idea-edu/
[sdkman]: https://sdkman.io/
[AdoptOpenJDK]: https://adoptopenjdk.net/
[7 rules of good commit messages]: https://chris.beams.io/posts/git-commit/#seven-rules
[Micronaut]: https://micronaut.io/
[checkstyle]: https://checkstyle.org/
[Checkstyle-IDEA]: https://plugins.jetbrains.com/plugin/1065-checkstyle-idea
