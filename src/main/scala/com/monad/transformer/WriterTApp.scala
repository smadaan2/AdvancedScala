package com.monad.transformer

import scalaz.Scalaz._
import scalaz._

object WriterTApp  extends App{

  type Result[T] = Writer[List[String], T]

  def doSomeAction() : Result[Int] = {
    // do the calculation to get a specific result
    val res = 10
    // create a writer by using set
    res.set(List(s"Doing some action and returning res"))
  }

  def doingAnotherAction(b: Int) : Result[Int] = {
    // do the calculation to get a specific result
    val res = b * 2
    // create a writer by using set
    res.set(List(s"Doing another action and multiplying $b with 2"))
  }

  def andTheFinalAction(b: Int) : Result[String] = {
    val res = s"bb:$b:bb"

    // create a writer by using set
    res.set(List(s"Final action is setting $b to a string"))
  }

  // returns a tuple (List, Int)
  println(doSomeAction().run)

  val combined = for {
    a <- doSomeAction()
    b <- doingAnotherAction(a)
    c <- andTheFinalAction(b)
  } yield c

  // Returns a tuple: (List, String)
  println(combined.run) //List(Doing some action and returning res, Doing another action and multiplying 10 with 2, Final action is setting 20 to a string),bb:20:bb

}
