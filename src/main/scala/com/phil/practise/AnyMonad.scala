package com.phil.practise

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global

trait AnyMonad[M[_]] {

  def map[T,T1] (m: M[T])(f: T => T1): M[T1]
  def flatMap[T,T1](m: M[T])(f: T => M[T1]): M[T1]
  def lift[T](t: T): M[T]
}

object AnyMonad {
  implicit val ec = ExecutionContext
  implicit object futureMonad extends AnyMonad[Future] {
    override def map[T, T1](m: Future[T])(f: (T) => T1): Future[T1] = m.map(f)

    override def flatMap[T, T1](m: Future[T])(f: (T) => Future[T1]): Future[T1] = m.flatMap(f)

    override def lift[T](t: T): Future[T] = Future(t)
  }
}


