package com.monad.transformer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaz._
import scalaz.Scalaz._

object OptionTApp extends App{

  type HttpResult[A] = OptionT[Future,A]

  object HttpResult {
    def applyF[A](f: Future[A]) = OptionT(f.map(a=> Option(a)))
    def applyO[A](f: Option[A]) = OptionT(Future.successful(f))
    def applyFO[A](v: Future[Option[Int]]) = OptionT(v)

    def f1[A,B](op: Option[A],failure: Exception => B) = {
      ???
    }
  }

}
