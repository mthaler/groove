package com.mthaler.groove.box

import java.awt.{Component, Dimension}
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

  def glue: Component = Box.createGlue()

  def horizontalGlue: Component = Box.createHorizontalGlue()

  def verticalGlue: Component = Box.createVerticalGlue()

  def horizontalStrut(width: Int): Component = Box.createHorizontalStrut(width)

  def verticalStrut(height: Int): Component = Box.createVerticalStrut(height)

  def rigidArea(width: Int, height: Int): Component = Box.createRigidArea(new Dimension(width, height))
}
