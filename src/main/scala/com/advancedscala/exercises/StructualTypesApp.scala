package com.advancedscala.exercises

object StructualTypesApp {

  class Bar {
    def close = println("Closing the Bar")
  }

  class Foo {
    def close = println("Closing the Foo")
  }

  def closeResource(resource: {def close: Unit}): Unit = {
       println("Closing Resource")
       resource.close
  }

  val bar = new Bar
  val foo = new Foo
  closeResource(bar)
  closeResource(foo)

}
