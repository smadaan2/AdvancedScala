package com.advancedscala.exercises

object SelfTypesApp extends App {

  trait Foo {
    def describe: String
  }

  trait MyFoo extends Foo {
    def describe: String = "This is my foo"
  }

  trait Bar {
    self: Foo =>
    def fullDescribe: String = s" I am a bar but depending upon foo brother message ${describe}"
  }

  object TestBar extends Bar with MyFoo

  println(s"fullDescribe::::: ${TestBar.fullDescribe}")

}
