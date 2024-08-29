# UserAPI

## Description
A small back-end app that exposes one GraphQL Entity with queries and
mutations for CRUD operation for a simple User object with the following
3 fields:
- id
- email
- name

The tech specs/stack for the project:

- Framework: Spring Framework/Latest
- GQL:       DGS Framework
- DAO:       Hibernate/JPA for DAO layer
- Build:     Gradle

## Usage
1. Build application
    - Run build script from `scripts` directory (`build.sh` for Unix based OS, `build.bat` for Windows)
    - Build script will create `.war` package in `/build/libs` directory
2. Copy war archive and deploy it to your web server
3. Try out the application (there is ready Postman collection in `postman` directory, which can be used)