package com.phil.practise

import java.util.concurrent.atomic.AtomicInteger


case class Character(name: String, alive: LiveStatus = Alive, hitPoints: HitPoints = HitPoints(1000)) {
  def damage(hitPoints: HitPoints): (Character => Character) = { character: Character =>
    if (hitPoints.lessThanZero) character else {
      val newHitPoints = character.hitPoints - hitPoints
      if (newHitPoints.lessThanZero)
        character.copy(alive = Dead, hitPoints = HitPoints(0))
      else
        character.copy(hitPoints = newHitPoints)
    }
  }
}

class NonFunctionalRequirements[From,To] {
  def logging(pattern: String) = { (delegate: From => To) => new Logging[From, To](pattern, delegate) }

  def metrics(atomicInteger: AtomicInteger) = { (delegate: From => To) => new Metrics(atomicInteger, delegate) }

  def error = { (delegate: From => To) => new ErrorHandler(delegate) }
}

object NonFunctionalRequirements {
  private val nonFunctionalRequirements = new NonFunctionalRequirements[Character, Character]
  import nonFunctionalRequirements._
  val damageCount = new AtomicInteger()
  def nonfunctional: ((Character) => Character) => ErrorHandler[Character, Character] = (logging("Damaged {1}") andThen metrics(damageCount)) andThen error
}
//or
object NonFunctionalRequirements1 {
//  def nonFunctional(delegate: Character => Character): (Character => Character) =
//    new Logging[Character, Character]("Damaged {1}",
//      new Metrics(Character.damageCount,
//        new ErrorHandler(
//          delegate)))
}

object ChracterApp extends App {
  import NonFunctionalRequirements._
  val chracter = Character("abd")
  val hitPoints = HitPoints(40)
  val result = nonfunctional
  val result1= nonfunctional(chracter.damage(hitPoints))(chracter)
  println(s"result:::${result.toString}")
}



