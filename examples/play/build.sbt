name := """play-java-seed"""
organization := "com.racter"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += "com.clivern" % "racter" % "1.0.4"