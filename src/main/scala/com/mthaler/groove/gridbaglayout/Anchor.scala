package com.mthaler.groove.gridbaglayout

import java.awt.{ GridBagConstraints => JGridBagConstraints }

object Anchor extends Enumeration {
  type Anchor = Value
  val CENTER, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST = Value

  implicit class AnchorValue(val anchor: Value) extends AnyVal {
    def toInt = anchor match {
      case CENTER => JGridBagConstraints.CENTER
      case NORTH => JGridBagConstraints.NORTH
      case NORTHEAST => JGridBagConstraints.NORTHEAST
      case EAST => JGridBagConstraints.EAST
      case SOUTHEAST => JGridBagConstraints.SOUTHEAST
      case SOUTH => JGridBagConstraints.SOUTH
      case SOUTHWEST => JGridBagConstraints.SOUTHWEST
      case WEST => JGridBagConstraints.WEST
      case NORTHWEST => JGridBagConstraints.NORTHWEST
    }
  }
}
