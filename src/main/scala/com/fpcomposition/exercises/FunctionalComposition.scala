package com.fpcomposition.exercises

import scala.Option
import scalaz.Scalaz._
import scalaz._
import scalaz.{Kleisli => FKleisli}


object FunctionalComposition extends App {

  case class Make(id: Int, name: String)

  case class Part(id: Int, name: String)

//1) ***********Implementation with functional composition
  val make1: (Int) => Option[Make] = (x: Int) => if (x == 1) Option(Make(1, "Suzuki")) else None

  val parts1: (Make) => Option[List[Part]] = (x: Make) => if (x.id == 1) Option(List(Part(1, "Gear Box"), Part(2, "Clutch cable"))) else None

  val fn1: Option[Make] => Option[List[Part]] = {
    case Some(m) => parts1(m)
    case _ => None
  }

  val g: (Int) => Option[List[Part]] = make1 andThen fn1

  println(s"Making Suzuki  Car with Functional composition:: ${g(1)}")

  //With the above solution, While this works, we had to manually create the plumbing between the two functions.
  // You can imagine that with different return and input types, this plubming would have to be rewritten over and over.
  //So we have to generalize this

//2) ********************Implementation with Monadic bind
  //Option is a monad so we can define f using a for comprehension:

  val fn2 = (x: Int) => for {
    m <- make1(x)
    p <- parts1(m)
  } yield p

  println(s"Making Suzuki Car with Monadic bind:: ${fn2(1)}")

  //The reason this is better is that make and parts could operate under a different monad but the client code would not need to change.
  //Notwithstanding, this still feels like unnecessary plumbing. All we are doing with the for comprehenstion / flatMap is extracting the values// from their respective monads to simply put them back in.It would be nice if we could simply do something like make compose parts

//3) *****************Implementation with Kliesli Arrows

  //You can read this type as being a function which knows how to get a value of type Make from the Option monad and will ultimately return an Option[NonEmptyList[Part]]. Now you might be asking, why would we want to wrap our functions in a kleisli arrow?
  //By doing so, you have access to a number of useful functions defined in the Kleisli trait, one of which is <==< (aliased as composeK):

  val make2: (Int) => Option[Make] = (x: Int) => (x == 1).option(Make(1, "Suzuki"))

  val parts2: Make => Option[List[Part]] = {
    case Make(1, _) => Some(List(Part(1, "Gear Box"), Part(2, "Clutch cable")))
    case _ => None
  }

  val fn3 = FKleisli(parts2) <==< make2   //compose
  val fn4 = FKleisli(make2)  >==> parts2   //andThen

  println(s"Making Suzuki Car with kliesli arrows compose:: ${fn3(1)}")
  println(s"Making Suzuki Car with kliesli arrows andThen:: ${fn4(1)}")

//4) *****************Implementation with Lifting i.e lift is functor combinator
  //Lifting is the name of the operation that transforms the function A => B into a function of type F[A] => F[B].

  //make: Int => Option[Make]
  //parts: Make => List[Part]

  //We can’t get a function Int => List[Part] because make returns an Option[Make] meaning it can fail. We need to propagate this possibility in the
  //composition. We can however lift parts into the Option monad, effectively changing its type from Make => List[Part] to Option[Make] => Option[List[Part]]:

  val make3: (Int) => Option[Make] = (x: Int) => (x == 1).option(Make(1, "Suzuki"))

  val parts3: Make => List[Part] = {
    case Make(1, _) => List(Part(1, "Gear Box"), Part(2, "Clutch cable"))
    case _ => Nil
  }

   val fn5 = make3 andThen Functor[Option].lift(parts3)
  println(s"Making Suzuki Car with functor:: ${fn5(1)}")

  //now has the type Int => Option[List[Part]] and we have once again successfully composed both functions without writing any plumbing code ourselves.

}


