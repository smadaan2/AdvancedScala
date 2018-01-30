package com.daniel.exercises


trait NumberLike[T] {
  def plus(a: T,b: T): T
  def minus(a: T,b: T): T
  def divide(a: T,b: Int): T
}

object NumberLike {
  implicit  object NumberLikeDouble extends NumberLike[Double] {

    override def plus(a: Double, b: Double): Double = a + b

    override def minus(a: Double, b: Double): Double = a - b

    override def divide(a: Double, b: Int): Double = a / b
  }

  implicit  object NumberLikeInteger extends NumberLike[Int] {

    override def plus(a: Int, b: Int): Int = a + b

    override def minus(a: Int, b: Int): Int = a - b

    override def divide(a: Int, b: Int): Int = a / b
  }

}

object Stat {
  def mean[T](xs: Vector[T])(implicit im: NumberLike[T]): T = im.divide(xs.reduce(im.plus(_ ,_)),xs.size)
  def median[T](xs: Vector[T]): T =  xs(xs.size / 2)
  def quartiles[T](xs: Vector[T]): (T, T, T) = (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))
  def iqr[T: NumberLike](xs: Vector[T]): T = quartiles(xs) match {
    case (lowerQuartile, _, upperQuartile) => implicitly[NumberLike[T]].minus(upperQuartile,lowerQuartile)
  }
}







