package com.scalaz.exercises

import scalaz._
import Scalaz._

object BooleanExercisesApp {

  //Ternary operations are back
  val m  = true ? "This is true" | "This is false"

  //Returns the given argument if this is `true`, otherwise, the zero element for the type of the given argument.
  val n = false ?? List(120,20321)

  val p = true ?? List(120,20321)

}
