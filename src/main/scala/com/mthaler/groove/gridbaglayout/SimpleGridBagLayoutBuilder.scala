package com.mthaler.groove.gridbaglayout

import javax.swing.JComponent

import scala.collection.mutable.ArrayBuffer

object SimpleGridBagLayoutBuilder {

  case class Item(component: JComponent, constraints: GridBagConstraints)

  case class Row(items: Seq[Item])

  def row(items: JComponent*)(implicit b: SimpleGridBagLayoutBuilder) = {
    b.addRow(Row(items.map { case component => Item(component, GridBagConstraints.Default) }))
  }

  def row(items: Tuple2[JComponent, GridBagConstraints]*)(implicit b: SimpleGridBagLayoutBuilder) = {
    b.addRow(Row(items.map { case(component, constraint) => Item(component, constraint) }))
  }
}


class SimpleGridBagLayoutBuilder {

  import SimpleGridBagLayoutBuilder._

  val rows = ArrayBuffer.empty[Row]

  def addRow(row: Row): Unit = {
    rows += row
  }
}
