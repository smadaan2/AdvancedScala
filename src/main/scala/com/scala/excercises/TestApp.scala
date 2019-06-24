package com.scala.excercises

import scala.annotation.tailrec



object TestApp extends App {

  def f1(no: Int): Int = {
      @tailrec
      def f2(l: Int, inc: Int):Int = {
        l match {
          case a if l == no => inc
          case a => if ((a.toString contains "4") || (a.toString contains "13")) f2(l + 1, inc + 2) else f2(l + 1, inc + 1)
        }
      }
      f2(0,0)
    }

    println("solution:::::::::::" + f1(12))



  // input  : Array("apple", "oranges", "apple", "banana", "apple", "oranges", "oranges")
  // output : Array(apple:3,oranges:3,banana:1)
  val s = Array("apple", "oranges", "apple", "banana", "apple", "oranges", "oranges")
  val s1 = s.groupBy(identity).map{case (key,value) => s"$key:${value.size}"}.toArray
  val m1 = s.groupBy(identity).mapValues(_.size).toList.map(a=> (s"${a._1}:${a._2}")).toArray




}
