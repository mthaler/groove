package com.mthaler.groove.box

import java.awt.{ Component, Dimension }
import javax.swing.Box

import scala.collection.mutable.ArrayBuffer

trait BoxBuilder {

  private val builder = ArrayBuffer.empty[Component]

  def horizontalBox(body: => Unit): Box = {
    body
    val box = Box.createHorizontalBox()
    for (component <- builder) box.add(component)
    builder.clear()
    box
  }

  def verticalBox(body: => Unit): Box = {
    body
    val box = Box.createVerticalBox()
    for (component <- builder) box.add(component)
    builder.clear()
    box
  }

  def glue: Unit = {
    builder += Box.createGlue
  }

  def horizontalGlue: Unit = {
    builder += Box.createHorizontalGlue()
  }

  def verticalGlue: Unit = {
    builder += Box.createVerticalGlue()
  }

  def horizontalStrut(width: Int): Unit = {
    builder += Box.createHorizontalStrut(width)
  }

  def verticalStrut(height: Int): Unit = {
    builder += Box.createVerticalStrut(height)
  }

  def rigidArea(width: Int, height: Int): Unit = {
    builder += Box.createRigidArea(new Dimension(width, height))
  }

  def component(component: Component): Unit = {
    builder += component
  }
}
