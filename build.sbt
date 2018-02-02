name := """eSunrich-DryOps"""
organization := "com.esunrich"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += javaJpa
libraryDependencies += ws
libraryDependencies += javaForms
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % "test"
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % "test"
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % "test"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.3"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1211"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
libraryDependencies += "com.squareup.okhttp" % "okhttp" % "2.3.0"
libraryDependencies += "commons-io" % "commons-io" % "2.4"
libraryDependencies += "com.googlecode.ez-vcard" % "ez-vcard" % "0.10.3"
libraryDependencies += "com.itextpdf" % "itextpdf" % "5.5.7"
libraryDependencies += filters
resolvers += "spray repo" at "http://repo.spray.io"
testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")
Keys.javaOptions+="-Dconfig.file=conf/application.conf"
