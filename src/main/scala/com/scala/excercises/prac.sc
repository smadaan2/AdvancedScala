import java.io
import java.net.{MalformedURLException, URL}
import java.util.UUID

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.Exception._

val opt1 = Option("shikha")
val opt2 = Option("abd")
val opt3 = Option.empty
val opt4 = Option(Failure(new Exception("try not found")))
val opt5 = Try(Option(true))

val opt6: Either[Throwable,Option[Int]] = Right(Some(1))
val opt7: Either[Throwable,Try[Int]] = Right(Try(3))
val opt8: Either[Throwable,String] = Left(new Exception("server error happened"))


//Extracting value opt1 & opt2 by handling not present and trying to recover if exception occur
println("#############testing case 1")
val t: Either[String, String] = opt1.toRight("encrpted token not found")
val t1: Either[String, String] = opt2.toRight("unencrpted token not found")

val result: Either[String, (String, String)] = {
  for {
    e1 <- t
    e2 <- t1
  } yield (e1,e2)
}

val re1: (String, String) = result match {
  case Right(a) => a
  case Left(ex) => sys.error(ex)
}

//Handling depends if you have further operation then use monadic way or otherwise apply fn under Success if its last step
val handle = Try(re1) match {
  case Success(a) => a
  case Failure(ex) => "try failed"
}

println("///////////////" + handle)

//Extracting value opt4 = Option(Try("abd"))
println("#############testing case 2")

val re4 = opt4.flatMap(a => a.toOption)

val t11: Try[String] = opt4 match {
  case Some(t) => t
  case None => Failure(new Exception("option not found"))
}
//or
val t12: Try[String] = opt4.fold(Failure(new Exception("option not found")))(identity)

val s: String = t11.fold(t => t.getMessage, re => re)

//Extracting value opt5 = Try(Some(true))
println("#############testing case 3")

opt5.flatMap{ opt => opt match {
  case Some(a) => Success(a)
  case None => Failure(new Exception("option not found"))
}
}

//Playing with val opt6 = Right(Some(1)),opt7 = Right(Try(3)), opt8 =Left(new Exception("server error happened"))

val e78: Either[Throwable, (Int, Int)] = for {
  opt66 <- opt6
  opt666 <- opt66.toRight(new Exception("option not found"))
  opt77 <- opt7
  opt777 <- opt77.toEither
} yield (opt666, opt777)


//Playing with Future
val opt9 = Future(Try(1))
val opt10 = Future(Right(1))
val opt11 = Future(None)
val opt12 = Future(Some("mybabu"))
val opt13 = Future(Left(new Exception("server error happened")))

//Control Exception

val st = "http://www.scala-lang.org/"
val st123 = "www.scala-la"
val x1: Option[URL] = catching(classOf[MalformedURLException]) opt new URL(st)
















































