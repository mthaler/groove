package com.mthaler.groove.gridbaglayout

import java.awt.{Insets => JInsets}

case class Insets(top: Int, left: Int, bottom: Int, right: Int) {

  def toAWT: JInsets = new JInsets(top, left, bottom, right)
}

object Insets {
  val Empty = Insets(0, 0, 0, 0)
}
