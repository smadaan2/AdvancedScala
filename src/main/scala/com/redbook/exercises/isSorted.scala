package com.redbook.exercises

object IsSortedApp extends App {


//  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
//    @annotation.tailrec
//    def iter(i: Int): Boolean = {
//      if (i >= as.length - 1) true
//      else gt(as(i), as(i + 1)) && iter(i + 1)
//    }
//    iter(0)
//  }

  val arr: Array[Int] = Array(1, 2, 3, 4)
  val f = (a: Int, b: Int) => a < b
  val list = arr.toList
  def isSorted[A](arr: Array[A])(f: (A,A) => Boolean): Boolean = arr.toList.foldLeft((arr.head, true)) { case ((h, b), n) => (n, b && f(h, n))}._2

  println(s" Array is sorted acc to function ${isSorted(Array())((a: Int,b: Int) => a <= b)}")
}
