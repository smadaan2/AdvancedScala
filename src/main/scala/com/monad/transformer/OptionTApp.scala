package com.monad.transformer


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaz._


object OptionTApp extends App{

  type HttpResult[A] = OptionT[Future,A]

  object HttpResult {
    def apply[A](f: Future[A]) = OptionT(f.map(a=> Option(a)))
    def apply[A](f: Option[A]) = OptionT(Future.successful(f))
    def apply[A](v: Future[Option[Int]]) = OptionT(v)

    def f1[A,B](op: Option[A],failure: Exception => B) = {
      ???
    }
  }

}
