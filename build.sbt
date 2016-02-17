name := """play-hibernate"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, JavaAppPackaging)

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalaVersion := "2.11.7"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
  "org.hibernate" % "hibernate-spatial" % "5.1.0.Final",
  "org.postgresql" % "postgresql" % "9.4.1207",
  cache,
  javaWs,
  "org.assertj" % "assertj-core" % "3.1.0" % "test",
  "org.apache.commons" % "commons-lang3" % "3.4",
  //encrypting tool
  "org.mindrot" % "jbcrypt" % "0.3m",
  //default play framework mailer
  "com.typesafe.play" %% "play-mailer" % "3.0.1"
)

routesGenerator := InjectedRoutesGenerator