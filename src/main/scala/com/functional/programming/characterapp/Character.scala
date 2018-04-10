package com.functional.programming.characterapp

import java.util.concurrent.atomic.AtomicInteger

import com.typesafe.scalalogging.LazyLogging

case class OldCharacter(name: String, alive: LiveStatus = Alive, hitPoints: HitPoints = HitPoints(1000)) extends LazyLogging{

  private val damageCount = new AtomicInteger()

  def damage(hitPoints: HitPoints) = try {
    if (hitPoints.lessThanZero) this else {
      val newHitPoints = this.hitPoints - hitPoints
      damageCount.incrementAndGet()
      val result = if (newHitPoints.lessThanZero)
        copy(alive = Dead, hitPoints = HitPoints(0))
      else
        copy(hitPoints = newHitPoints)
      logger.info(s"Damaged $this for $hitPoints producing $hitPoints")
      result
    }
  }
  catch {
    case e: Exception =>
      logger.error(s"Unexpected error damaging $this for $hitPoints", e);
      throw e
  }
}

sealed trait LiveStatus
case object Alive extends LiveStatus
case object Dead extends LiveStatus

case class HitPoints(hp: Int) {
  def lessThanZero: Boolean = hp < 0

}

object HitPoints {
  implicit class ImplicitHitPoints(h: HitPoints) {
    def -(that: HitPoints):HitPoints = HitPoints(h.hp - that.hp)
  }
}



