# DropWizard Authentication (and questions)

How to start the KshHomepage application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/demonstrateAuthenticationDropwizard-0.1-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080/auth`

Health Check
---

To see your applications health enter url `http://localhost:8081/auth/healthcheck`

What this project is all about
---
This project is to demonstrate a problem I have with my authentication in my real project. I asked the question here: [Stackoverflow: Updating header with authorization-credentials without browser pop-up (dropwizard 2.0.x)](https://stackoverflow.com/questions/62843914/updating-header-with-authorization-credentials-without-browser-popup-dropwizard)

The problem is not reduced to its smallest footprint. Many other examples e.g. use hard coded username and password. But then things like access to the database get too much simplified.
However I hope this example is much easier for demonstration purposes, as e.g. it is just one very simple page, I do not use project lombok for generating getters and setters and I hope by using H2 instead of MySQL as a database this project can be easily run by everyone.

What I want to achieve in  my application:
* Do form based authentication (instead of popup window by browser)
* Do a logout