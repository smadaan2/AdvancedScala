package com.monad.transformer

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

case class FutureOption[T](inner: Future[Option[T]]) {
  def map[T1](fn: T => T1): FutureOption[T1] = FutureOption {
    inner.map(_.map(fn))
  }

  def flatMap[T1](fn: T => FutureOption[T1]): FutureOption[T1] = FutureOption {
     inner.flatMap{
       case Some(a) => fn(a).inner
       case None => Future.successful(None)
     }
  }
}


object FutureOptionMonadTransformer extends App{

  val x = FutureOption(Future(Some(1)))
  val y = FutureOption(Future(Some(5)))

  x.flatMap(x1 => y.map(y1 => x1 + y1))

  val result: FutureOption[Int] = for {
    x1 <- x
    y1 <- y
  } yield x1 + y1

 result.inner.onComplete {
    case Success(Some(a)) => println(a)
    case Failure(ex) => println("result not completed")
  }



}
