# jdbc-app
You need to implement a console CRUD application that interacts with the database and allows you to perform all CRUD operations on entities:

- Developer
- Skill
- Account
- AccountStatus (enum ACTIVE, BANNED, DELETED)
- Developer -> Set skills + Account account
- Account -> AccountStatus

Requirements:

Adhere to the MVC pattern (model, repository, service, controller, view packages)
Use https://www.liquibase.org/ for database migration
The service layer of the application should be covered by unit tests (junit + mockito).
To import libraries use Maven
The result of the project should be a separate repository on github, with a description of the task, project and instructions on how to run the project locally.
Technologies: Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito.
