package com.scala.excercises

object AlgoApp extends App {

  val leftNode = new Node[Int](1)
  val rightNode = new Node[Int](6)
  val node = new Node[Int](5, Some(leftNode), Some(rightNode))

  //val node1 = node.copy(value = node.value * 2, left=node.left.map(_.value*2))

  def add(tree: Node[Int]): Node[Int] = {
    tree.copy(value = tree.value * 2, left = tree.left.map(f => add(f)), right = tree.right.map(f => add(f)))
  }


  def count(tree: Node[Int]): Int = {
    def inner(n: Option[Node[Int]], count: Int): Int = n match {
      case None => count
      case Some(node) => inner(node.left,inner(node.right, count+1))
    }

    inner(tree.left, inner(tree.right, 1))
  }

  //println(add(node))

  println(count(node))




}

case class Node[A](value: A, left: Option[Node[A]] = None, right: Option[Node[A]] = None)