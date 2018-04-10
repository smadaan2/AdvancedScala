import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait Monad[M[_]] {
  def map[T, T1](m: M[T])(f: T => T1) : M[T1]
  def flatMap[T, T1](m: M[T])(f: T => M[T1]) : M[T1]
  def point[T](t: T): M[T]
}

object Monad {
  implicit object futureMonad extends Monad[Future] {
    override def map[T, T1](m: Future[T]) (f: (T) => T1): Future[T1] = m.map(f(_))

    override def flatMap[T, T1](m: Future[T]) (f: (T) => Future[T1]): Future[T1] = m.flatMap(f)

    override def point[T](t: T): Future[T] = Future.successful(t)
  }

}


case class AnyMonad[M[_],T](inner: M[Option[T]])(implicit m: Monad[M]) {
  def map[T1](f: T => T1): AnyMonad[M,T1] = AnyMonad {
    m.map(inner)(opt => opt.map(f))
  }

  def flatMap[T1](f: T => AnyMonad[M,T1]): AnyMonad[M,T1] = AnyMonad {
    m.flatMap(inner){
      case Some(a) => f(a).inner
      case None => m.point(None)
    }
  }
}


object MonadApp extends App {
  import Monad._
  val t = AnyMonad[Future, Int](Future(Some(1)))(futureMonad).map(i => i.toString).inner
  println(t)

}






