package com.mthaler.groove.box

import java.awt.Component
import javax.swing.Box

object BoxBuilder {

  def horizontalBox(components: Component*): Box = {
    val b = Box.createHorizontalBox()
    b
  }

  def verticalBox(components: Component*): Box = {
    val b = Box.createVerticalBox()
    b
  }
}
