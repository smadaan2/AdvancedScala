package com.redbook.exercises

import scala.io.Source

object HOFExerciesApp extends App {

  def curry[A,B,C](a: A,f: (A,B) => C) : B => C = {
    b: B => f(a,b)
  }

  def curry[A,B,C](f: (A,B) => C): A => (B => C) = ???

  def uncurry[A,B,C](f: A=>B=>C): (A,B) => C = ???

/////// Creating  functional data structure

  def fold[A,B](list: List[A])(initial: B,f: (A,B) => B): B = {
    list match {
      case Nil => initial
      case head :: tail => f(head,fold(tail)(initial,f))
    }
  }

  def sum[A](list: List[A],z: A)(implicit i: Numeric[A]): A = {
    fold(list)(z,(a,b) => i.plus(a,b))
  }

  def product[A](list: List[A],z: A)(implicit i: Numeric[A]): A = {
    fold(list)(z,(a,b) => i.times(a,b))
  }

///////////////////////////////lifting fn into Option

  def map2[A,B,C](a: Option[A],b: Option[B])(f: (A,B) => C): Option[C] = {
    for {
      a1 <- a
      b1 <- b
    } yield f(a1,b1)
  }

  def plus(a: String,b: String): Option[Int] = {
    val optA = WrapOption(a)
    val optB = WrapOption(b)
    map2(optA,optB)(add)
  }

  def WrapOption(a: String): Option[Int] =
    try{
      Some(a.toInt)
    }
  catch {
    case e: Exception => None
  }

  // fn adding values is in library which we cant change
  def add(a: Int,b: Int): Int = a + b

  println(s" Adding two strins ${plus("Abd","2")}")

  //////////////////////////////////////

  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    a.foldRight[Option[List[A]]](Some(Nil)){(o,o1) => (o,o1) match {
      case (Some(x), Some(x1)) => Some(x :: x1)
      case _ => None:Option[List[A]]
    }}
  }

}
