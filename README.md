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
    
Caveats
-------
All routes are hardwired to localhost in the frontend. Thus you can only run the application on the same maschine.
In a real application we would also separate the frontend and deliver it from a dedicated HTTP server,
reducing the number of requests for the backend.

By separating the frontend and the backend you might quickly have to deal with [CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS)
and adding routes to handle Preflight Requests using OPTIONS.

Although there is already the concept of an account, there is no real authentication/authorization.
If you want to learn more about it visit [Dedbolt 2](https://github.com/schaloner/deadbolt-2) or [SecureSocial](http://securesocial.ws/) if you want to integrate with Facebook, Twitter & Co.           