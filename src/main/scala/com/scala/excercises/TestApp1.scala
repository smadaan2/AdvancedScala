package com.scala.excercises

import scala.annotation.tailrec

object TestApp1 extends App {


  //  A[0] = 5
  //  A[1] = 4
  //  A[2] = 0
  //  A[3] = 3
  //  A[4] = 1
  //  A[5] = 6
  //  A[6] = 2

  //  A[0] = 5, A[5] = 6, A[6] = 2, A[2] = 0
  //  A[1] = 4 , A[4] = 1
  //  A[3] = 3


  val arr = Array(5,4,0,3,1,6,2)

  def solution1(a: Array[Int]): Int = {

    def inner(ind: Int, current: Int, result: List[Int]): List[Int] = {
      val v = a(current)
      if (v == ind) result
      else inner(ind, v, result :+ v)
    }

    val v: Array[(Int, Int)] = a.zipWithIndex
    if (v.isEmpty) 0
    else {
      val r = v.map { case (value, ind) => inner(ind, value, List(ind, value))}.map(_.size)
      r.max
    }
  }


  println("solution1:::::::::::::::::" + solution1(arr))



  def solution2(A: Array[Int]): Int = {
    val withIndex = A.zipWithIndex

    def func(tempArray: Array[(Int, Int)], current: (Int, Int), max: Int): Int = {
      tempArray.find(_._1 == current._1) match {
        case Some(dd) => Math.max(max, Math.abs(current._2 - dd._2))
        case None => max
      }
    }

    withIndex.foldLeft(0) { case (m, current) => func(withIndex, current, m) }
  }


  println("solution2:::::::::::::::::" + solution2(Array.fill(50000)(2)))


}
