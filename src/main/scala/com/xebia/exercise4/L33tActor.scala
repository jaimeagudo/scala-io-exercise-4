package com.xebia
package exercise4

import akka.actor.{Props, Actor}

object L33tActor {
  case class L33tify(value:String)
  case class L33tResult(value:String)

  def props = Props[L33tActor]
  def name = "l33t"
}

class L33tActor extends Actor {
  import L33tActor._

  //TODO create a val for the settings

  //TODO get 'useShiftDigit' boolean from the Settings L33t object
  val uppercase = false

  //TODO get 'useShiftDigit' boolean from the Settings L33t object
  val useShiftDigit = false

  val conversionMap =      Map('o'-> 0, 'i'-> 1, 'z'->2, 'e'-> 3, 'a' -> '4', 's' -> 5, 'b'-> 6, 't' -> 7, 'B'-> 8, 'g'-> 9)
  val shiftConversionMap = Map('o'-> ')', 'i'->'!', 'z'->'@', 'e'-> '#', 'a'-> '$', 's' -> '%', 'b'-> '^', 't' -> '&', 'B'-> '*', 'g'-> '(')

  def convertToDigit(value:String) = value.map(c=> conversionMap.get(c).map(number=> number.toString).getOrElse(c)).mkString("")
  def convertToShiftDigit(value:String) = value.map(c=> shiftConversionMap.getOrElse(c,c)).mkString("")

  def receive = {
    case L33tify(value) =>
      val result = if(useShiftDigit) convertToShiftDigit(value) else convertToDigit(value)
      val msg = if(uppercase) result.toUpperCase() else result

      sender ! L33tResult(msg)
  }
}
