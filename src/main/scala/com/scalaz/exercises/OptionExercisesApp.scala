package com.scalaz.exercises

import scalaz._
import Scalaz._

object OptionExercisesApp {
  println(some(10))
  println(none[Int])
  //You might wonder why this is useful, but look at the following: Say we have a list of Options, over which we want to fold:
  //in general scala

  val l = List(Some(10), Some(20), None, Some(30))
  // l.foldLeft(None) { (el, z) => el.orElse(z)  } This will fail, because our fold expects the result to be a None.type and not an Option When we use the Scalaz versions, it works as expected:
  l.foldLeft(none[Int]) { (el, z) => el.orElse(z)  }

  // Alternative for getOrElse
   println(Some(10) | 20)
   println(none | 10)
   println(Some(10) ? 5 | 4)   //Ternary operator
   val m = some(List())
   ~m    // Returns the item contained in the Option

}
