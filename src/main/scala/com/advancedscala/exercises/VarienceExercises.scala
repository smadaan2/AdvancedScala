package com.advancedscala.exercises

object VarienceExercises extends App {

  sealed trait Fruit {
    def describe: String
  }

  class Apple extends Fruit {
    override def describe: String = "I am a Apple"
  }

  class AppleChild extends Apple {
    override def describe: String = "I am a Apple Child"
  }

  class Mango extends Fruit {
    override def describe: String = "I am a Mango"
  }

  //covarient along with contravarient
  class Box[+A <: Fruit ] {
    def describe[AA >: A <: Fruit] (a: AA): String = "I am only one"
  }

  val apples = new Box[Apple]
  val fruit = new Fruit {
    override def describe = "I am a fruit"
  }
  val apple = new Apple

  val s: Any="abd"
  println (s"only apple:::::: ${apples.describe(apple)}")
  println (s"only fruit :::::: ${apples.describe(fruit)}")
  //println (s"only abd :::::: ${apples.describe(s)}")

  ///////////// Another Implementation with Contravarient

  class Box1[-A] {
    def describe[A <: Fruit] (a: A): String = "I am only one"
  }

  val apples1 = new Box1[Apple]
  val apple1 = new Apple
  val appleChild = new AppleChild
  val fruit1 = new Fruit {
    override def describe = "I am a fruit"
  }
  val mango = new Mango
  println (s"only apple:::::: ${apples1.describe(apple1)}")
  println (s"only fruit:::::: ${apples1.describe(fruit1)}")
  //println (s"only abd:::::: ${apples1.describe(s)}")
  println (s"only applechild:::::: ${apples1.describe(appleChild)}")
  println (s"only mango:::::: ${apples1.describe(mango)}")

}
