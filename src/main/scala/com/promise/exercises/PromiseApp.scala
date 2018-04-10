package com.promise.exercises

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global

object PromiseApp extends App {


  def f1: Unit = {
    val p = Promise[Int]()
    val f = p.future

    val producer = Future {
      val r = doSomethingh
      p.success(r)
      continueDoingSomethingh
    }


    val consumer = Future {
      startDoingSomethingh
      f onSuccess {
        case r => println("promise complete:::::")
      }
    }
  }


  def doSomethingh: Int = {
    Thread.sleep(10)
    println("doSomethingh")
    1
  }

  def continueDoingSomethingh = {
    Thread.sleep(2)
    println("continueDoingSomethingh")
  }

  def startDoingSomethingh = {
      println("startDoingSomethingh::::")
    }


}
