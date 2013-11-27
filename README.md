smartapp
========

smartapp is my one week challenge project that integrates with
a cloud service market. It covers some Java-based technologies:

- Spring framework (3.2.x)
- Spring Security framework (3.1.x) supports OAuth + OpenId security,
which mimics Google OpenID+OAuth hybrid protocol :)
To support 2-legged OAuth (1a), OAuth Signpost is used to sign the request.
- Spring JPA 2.0
- Spring Hibernate 4.x (not fully implement but you can find it in one of unit tests)
- Jersey framework 1.15
- Also Google App Engine Java SDK to deploy the project to appspot.


config
======

Before you want to make a build, please update your datasource in
'config.propeties' file. The app works with MYSQL by default.

If you want to make it work with other database, then you must
to update 'database' property under 'HibernateJpaVendorAdapter'
bean in 'config-spring-jpa.xml', as well as add a dependency
for that database driver in 'pom.xml'.

HSQLDB is used for Spring4JUnit tests.

In order to test 2-legged OAuth, 'consume_key' and 'consume_secret'
must be filled in the 'config.properties'


build and deploy
================
run 'mvn clean install' to make a war file, and could be deployed
on any app server. If you want to deploy the application to
google app engine, the build should be done with jdk 7.


notes
====

The code is lack of comments for classes and methods.
However someone said that "good code does not need comments".
It totally doesn't mean my code is good :))))))

One more thing, I borrow (apologise for my laziness) a couple
classes from the other authors. You must see the other author's
name on those classes.


potential
=========

With Spring Security integrated in the application. It could be
easily to support other security methods as SAML, LDAP.

A factory for Hibernate and Spring JPA should be considered as
a potential feature to support Persistence and multiple database.