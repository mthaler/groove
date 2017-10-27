package com.mthaler.groove.gridbaglayout

import java.awt.{Component, GridBagLayout}
import javax.swing.JPanel
import scala.collection.mutable.ArrayBuffer

trait SimpleGridBagLayoutBuilder {

  me: JPanel =>

  private val builder = ArrayBuffer.empty[Row]

  case class Item(component: Component, constraints: GridBagConstraints)

  case class Row(items: Seq[Item])

  def gridbaglayout(body: => Unit): Unit = {
    setLayout(new GridBagLayout)
    body
    for ((row, rowIndex) <- builder.zipWithIndex) {
      for ((item, colIndex) <- row.items.zipWithIndex) {
        add(item.component, GridBagConstraints(colIndex, rowIndex).toAWT)
      }
    }
  }

  def row(items: Component*) = {
    Row(items.map(component => Item(component, GridBagConstraints.Default)))
    builder += Row(items.map(component => Item(component, GridBagConstraints.Default)))
  }
}
