package com.advancedscala.exercises

object HOFexercises extends App {

  val authenticatedUsers = List("Alex", "Sam")
  // example url /hello/{userName}
  //  def hello(userName: String): String = {
  //    if (authenticatedUsers.contains(userName)) s"world $userName"
  //    else "Unauthorized access" }
  //  // example url /foo/{userName}
  //  def foo(userName: String): String = {
  //    if (authenticatedUsers.contains(userName)) s"bar $userName"
  //    else "Unauthorized access" }
  //

  //  println(s"request to /hello/Alex: ${hello1("Alex")}")
  //  println(s"request to /hello/David: ${hello1("David")}")
  //  println(s"request to /foo/Alex: ${foo1("Alex")}")

  //Removing duplicasy by using HOF

  def hello1(userName: String, s: String, fn: (String, String) => String) = {
    fn(userName, s)
  }

  def setup(userName: String, s: String) = {
    if (authenticatedUsers.contains(userName)) s"world $userName"
    else "Unauthorized access"
  }

  println(hello1("Alex", "world", setup))
  println(hello1("Alex", "bar", setup))
  println(hello1("Shikha", "yes", setup))
  println(hello1("Shikha", "bye", setup))




}
