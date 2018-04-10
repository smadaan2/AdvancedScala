package com.coursera.fpexercises

import scala.annotation.tailrec

object TailRecursiveApp extends App{

  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    @tailrec
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a+1, acc + f(a))
    }
    loop(a, 0)
  }
  println(s"sum:::: ${sum(x=> x)(1,4)}" )

  //****************Curring

  def product(f: Int => Int)(a: Int,b: Int): Int = {

    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a+1, acc * f(a))
    }
    loop(a, 1)
  }

  println(s"product:::: ${product(x=> x)(1,3)}")

  def fact(n: Int) = product(x=> x)(1,n)

  println(s"fact::::${fact(3)}")

  //generlize sum and product

  def generalize(f: Int => Int, combine: (Int,Int) => Int, identity: Int )(a: Int,b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a+1,combine(acc,f(a)))
    }
    loop(a, identity)
  }

  println(s"generalize:::::${generalize(x=>x,(a,b)=>a+b,0)(1,3)}")

}
