A Twitter-like application using Play Framework
==============================================

Overview
--------
![Overview](/overview.png?raw=true "Overview")


What's in it?
-------------
The application makes use of the following:

* [Play Framework](http://www.playframework.com/) to implement the backend
* [Akka](http://akka.io/) and [WebSockets](https://www.websocket.org/) to push new messages to the client
* [AngularJS](https://angularjs.org/) to communicate with the backend and manage the frontend

Running the application
-----------------------
You need Java 8 and [Typesafe Activator](https://typesafe.com/activator) to run the application.

Then within the project directory just type

    activator run