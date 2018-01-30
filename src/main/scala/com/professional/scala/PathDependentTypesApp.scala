package com.professional.scala


class NetworkSwitch {
  class Port
  def switchPortOn(port: Port): Unit = { println(s"Switching on $port") }
}


//demonstrate the usefulness of path-dependent types

object DependentTypesApp extends App{
  val ntwswitch1 = new NetworkSwitch
  val port1 = new ntwswitch1.Port
  ntwswitch1.switchPortOn(port1)


  val ntwswitch2 = new NetworkSwitch
  val port2 = new ntwswitch2.Port
  //ntwswitch2.switchPortOn(port1)   //Not Compile
  ntwswitch2.switchPortOn(port2)

  //create methods outside of the class definition that can still exploit the path-dependent types

  def switchOffPort(switch: NetworkSwitch)(port: switch.Port): Unit = println(s"Switching off $port")

  switchOffPort(ntwswitch1)(port1)
  //switchOffPort(ntwswitch1)(port2)     //getting runtime error

}


