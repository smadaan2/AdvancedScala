package com.redbook.exercises

object FindFirst extends App{

  def findFirst[A](key: A,arr: Array[A]) : Int = arr.zipWithIndex.find{case (a,b) => a == key}.map(_._2).getOrElse(-1)

  val stringKey: String = "Shikha"
  val intKey: Int= 2
  println(s"$stringKey is in ${findFirst("Shikha",Array("Shikha","Abd"))} place")
  println(s"$intKey is in ${findFirst(2,Array(1,2))} place")

  // Implementation with HOF

  def findFirstWithHOF[A](arr: Array[A],f: A => Boolean): Int = arr.toList.zipWithIndex.find{case(a,b) => f(a)}.map(_._2).getOrElse(-1)

  findFirstWithHOF(Array(1,2,3), (a: Int) => intKey == a)
  findFirstWithHOF(Array("Shikha","Abd"),(a: String) => stringKey == a)

}



