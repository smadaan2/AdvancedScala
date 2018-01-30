package com.professional.scala

object TypeWitnessApp {

}

//sealed abstract class Option1[+A] {
//  def flatten[B](implicit ev: A <:< Option1[B]): Option1[B] = None
//  //if (isEmpty) None else ev(this.get)
//}
