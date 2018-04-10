package com.advancedscala.exercises

object AbstractDataTypesApp extends App{

  trait Food

  case object Grass extends Food

  case object  Fish extends Food

  trait Animal {
    type SuitableFood <: Food
    def eat(food: SuitableFood): this.type =  this
  }

  class Cow extends Animal {
    type SuitableFood = Grass.type
  }

  class Cat extends Animal {
    type SuitableFood = Fish.type
  }

  val cow = new Cow
  val cat = new Cat

  println(s"Cow eat ${cow.eat(Grass).eat(Grass)}")
  println(s"Cat eat ${cat.eat(Fish).eat(Fish)}")
}

// Abstract type members also can be implemented with Type Classes but if there is more types then with abstract data types its more cleaner

//trait SuitableFood
//
//case object Grass extends SuitableFood
//
//case object  Fish extends SuitableFood
//
//trait Animal[A <: SuitableFood] {
//  def eat(food: A): this.type = this
//}
//
//class Cow extends Animal[Grass.type]
//class Cat extends Animal[Fish.type]


