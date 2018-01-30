package com.redbook.exercises

case class Coffee(name: String,price: Double)
case class CreditCard(number: Int)
case class Charge(cc: CreditCard, price: Double)

trait CoffeeMenu {
  val coffees: List[Coffee]
}

class Cafe(coffeeMenu: CoffeeMenu) {
  def buyCoffee(coffeeName: String, cc: CreditCard): (Coffee,Charge) = {
    val coffeePrice =findCoffeePrice(coffeeName)
    (Coffee(coffeeName,coffeePrice),Charge(cc,coffeePrice))
  }

  def buyCoffees(listOfCoffeesOrdered: List[String],cc: CreditCard): (List[Coffee],Charge) = {
    val listOfcoffees: List[Coffee] = listOfCoffeesOrdered.map(coffeeName => Coffee(coffeeName,findCoffeePrice((coffeeName))))
    val totalCharge: Double = listOfcoffees.map(coffee => coffee.price).reduce((a,b) => a + b)
    (listOfcoffees, Charge(cc,totalCharge))
  }

  private def findCoffeePrice(coffeeName: String): Double = {
    val coffeeItem: Coffee = coffeeMenu.coffees.find(_.name == coffeeName).get
    coffeeItem.price
  }
}

object CoffeeApp extends App {

  val coffeeMenu: CoffeeMenu = new CoffeeMenu {
    val coffees = (List(Coffee("expresso",1.50),Coffee("latte",1.98)))
  }

  val cafe = new Cafe(coffeeMenu)
  val cc = CreditCard(123)
  val (coffee: Coffee,charge: Charge) = cafe.buyCoffee("expresso", cc)
  val orderedCoffees = List("expresso","latte")
  val (coffees: Seq[Coffee],charges: Charge) = cafe.buyCoffees(orderedCoffees,cc)
}





