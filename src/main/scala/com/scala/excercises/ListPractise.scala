package com.scala.excercises

import scala.annotation.tailrec

object ListPractise  extends App {

  val list = List(1,2,3,4)

  //Find the last element of a list.

  println(s"last item:::::::${list.last}")

  //Find the last but one element of a list.

  println(s"Before the last:::::::${list.init.last}")

  // Find the Kth element of a list.

  def findKthItem(n: Int) = {
    list.zipWithIndex.find(_._1 == n).toList.head._2
  }

  println(s"findKthItem:::::::::::;${findKthItem(2)}")

  //Find the number of elements of a list.
  println("length of the list:::::::" + list.length)

  //Reverse a list
  println(s"reverse of a list:::: ${list.reverse}")

  //Find out whether a list is a palindrome without reverse

  def isPalindrome(l: List[Int]): Boolean= {
    @tailrec
    def isPalindrome1(rem: List[Int], check: Boolean): Boolean = {
      rem match {
        case Nil => true
        case head :: tail => if (head == tail.last) isPalindrome1(tail.init, true) else false
      }
    }
      isPalindrome1(l, true)
  }

  println("List is a palindrome:::::" + isPalindrome(List(1,2,3,3,2,1))) //yes
  println("List is a palindrome::::::" + isPalindrome(List(1,2,3,4,2,1))) //no
  println("List is a palindrome::::::" + isPalindrome(List(1,2,3,4,2,0))) //no


  // P07 (**) Flatten a nested list structure.
  // Example:
  // scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
  // res0: List[Any] = List(1, 1, 2, 3, 5, 8)

  val tryFlattenList: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))

  def flatten(values: List[Any]) : List[Any] =
    flattenTail(Nil, values)

  def flattenTail(seen: List[Any], remaining: List[Any]) : List[Any] =
    remaining match {
      case Nil                => seen
      case (x: List[_]) :: xs => flattenTail(seen ::: flatten(x), xs)
      case x :: xs            => flattenTail(seen ::: List(x), xs)
    }

   println(s"Flatten a nested list structure::::::${flatten(tryFlattenList)}")

  //Eliminate consecutive duplicates of list elements.
  //Example:
  //scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)

  def compress(l: List[Symbol]): List[Symbol] = {
    @tailrec
    def compress1(seen: List[Symbol], remaining: List[Symbol]): List[Symbol] = {
      remaining match {
        case Nil => seen
        case head :: Nil => seen :+ head
        case head :: tail => if (head == tail.head) compress1(seen,tail) else compress1(seen :+ head, tail)
      }
    }

    compress1(List.empty[Symbol],l)
  }

  println(s"compressing the list:::::${compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))}")


  //Pack consecutive duplicates of list elements into sublists.
  //Example:
  //scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))

  val input = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  def pack(input: List[Symbol]): List[List[Symbol]] = {
    @tailrec
    def pack1(seen: List[List[Symbol]], append: List[Symbol], remaining: List[Symbol]): List[List[Symbol]] = {
      remaining match {
        case Nil => seen
        case head :: Nil => seen :+ (append :+ head)
        case head :: tail => if (head == tail.head) pack1(seen, append :+ head, tail) else pack1(seen :+ (append :+ head), List.empty, tail)
      }
    }

    pack1(List(List.empty[Symbol]), List.empty[Symbol], input).tail
  }

  println(s"Packing the list:::::${pack(input)}")


  //Run-length encoding of a list.
  //Example
  //scala> encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

  def encode(input: List[List[Symbol]]): List[(Int,Symbol)] = {
    input.map(l => (l.length,l.distinct.head))
  }

  println(s"Encoding the list:::::::::::${encode(pack(input))}")


  //Modified run-length encoding.
  //Example
  //scala> encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  //res0: List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))

  def encodeModified(input1: List[(Int,Symbol)]): List[Any] = {
    input1.map(x => if(x._1 > 1) x else x._2)
  }

  println(s"EncodeModified the list:::::::: ${encodeModified(encode(pack(input)))}")


  //Decode a run-length encoded list.
  //Run-length encoding of a list (direct solution).
  //Duplicate the elements of a list.
  //Duplicate the elements of a list a given number of times.
  // Drop every Nth element from a list.
  //Split a list into two parts.
  //Extract a slice from a list.
  //Rotate a list N places to the left.
  //Remove the Kth element from a list.
  //Insert an element at a given position into a list.
  //Create a list containing all integers within a given range.
  //Extract a given number of randomly selected elements from a list.
  //Sorting a list of lists according to length of sublists.


}
