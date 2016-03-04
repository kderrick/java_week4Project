# Band and Venue Tracker Web App

#### March , 2016

#### By Kyle Derrick

## Description

This is a web application written in Java that will allow a user to add bands as well as concert venues.

## Setup/Installation Requirements

* Clone this repository.
* Make sure you have Gradle and Java installed.
* Start Psql and Postgres
* in PSQL:
* CREATE DATABASE band_venues;
* Connect to database by using \c command: \c band_venues;
* CREATE TABLE bands (id serial PRIMARY KEY, name varchar);
* CREATE TABLE venues (id serial PRIMARY KEY, name varchar);
CREATE TABLE bands_venues (id serial PRIMARY KEY, bandId int, venueId int);
* In the top level of the cloned directory, run the following command in your terminal:

`gradle run`

* Open your web browser of choice to localhost:4567

## Please Make This Better

Please fork this repository and send a pull request if something in here could be better.

## Technologies Used

Java, Spark, JUnit, FluentLenium, Velocity, Bootstrap, PSQL
