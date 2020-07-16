# DropWizard Authentication (and questions)

How to start the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/authDemo-0.1.0-SNAPSHOT.jar server config.yml`
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

When you start the application and go to the url `http://localhost:8081/auth/healthcheck` in your browser, then you can see a list of users on the left. Furthermore you have 2 ways to do the login. I would prefer the first one to work, but right now only the second does. Use any of the users shown on the left of the page, no passwork is needed.
If you then use the reload button on the bottom of the page, you can see that the user you used for the login is lost if you did log in with the way I would like to do it.
If however you do the login (the second way, which works), then you can use the logout button, but you will stay logged in.

Any help is very much appreciated, see my question on Stackoverflow. Please answer there, possibly in addition with a proposed solution here.
