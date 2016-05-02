Installation
============

Install 'node' with 'npm' from https://nodejs.org

Change into folder 'javascript-client' and run

$ npm update

Client is now ready to run!


Compile and develop typescript files
====================================

Just compiling

$ tsc

This command runs the compiler in watch mode, starts the server, 
launches the app in a browser, and keeps the app running while we continue to build the app.

$ npm start

'start' is an entry in package.json within the scripts body. 

Run angular2-App
================

You can configure the server location using file 'app/application.config.js'.
This configuration file will be loaded on bootstrap to set the server url.

To access the switch-engine 'frodo' use "url":"http://86.119.38.74:8080"