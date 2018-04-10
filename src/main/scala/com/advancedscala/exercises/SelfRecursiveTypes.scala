package com.advancedscala.exercises

//self recursive types
trait Doubler[T] {
  def double: T
}

//case class Square(base: Double) extends Doubler[Square] {
//  override def double: Square = Square(base * 2)
//}

//the compiler will not complain. The problem is that it won’t complain even if you write something outrageous like the following code:
case class Person(firstname: String, lastname: String, age: Int)
case class Square(base: Double) extends Doubler[Person] {
  override def double: Person = Person("John", "Smith", 42)
}

//You want to avoid something like that by enforcing a compile-time check. Enter a self-recursive type:
trait Doubler1[T <: Doubler1[T]] {
  def double: T
}

case class Square1(base: Double) extends Doubler1[Square1] {
  override def double: Square1 = Square1(base * 2)
}

//case class Square1(base: Double) extends Doubler1[Person] {
//  override def double: Person = Person("John", "Smith", 42)
//}

object SRTApp extends App {
  val square = new Square(2.0)
  println(s"Square genearting person which is not right ::: ${square.double}")

  val square1 = new Square1(2.0)
  println(s" to make it constraint Square should not generate person ::: ${square1.double}")

}


