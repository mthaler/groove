package com.mthaler.groove.gridbaglayout

import java.awt.{ GridBagConstraints => JGridBagConstraints }

object Fill extends Enumeration {

  type Fill = Value
  val NONE, HORIZONTAL, VERTICAL, BOTH = Value

  implicit class FillValue(val fill: Fill) extends AnyVal {
    def toInt = fill match {
      case NONE => JGridBagConstraints.NONE
      case HORIZONTAL => JGridBagConstraints.HORIZONTAL
      case VERTICAL => JGridBagConstraints.VERTICAL
      case BOTH => JGridBagConstraints.BOTH
    }
  }
}
