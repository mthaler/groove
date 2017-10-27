package com.mthaler.groove.gridbaglayout

import java.awt.{Component, GridBagLayout}
import javax.swing.{JLabel, JPanel}

import scala.collection.mutable.ArrayBuffer

trait SimpleGridBagLayoutBuilder {

  me: JPanel =>

  import SimpleGridBagLayoutBuilder._

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

  def row(items: (Component, GridBagConstraints)*) = {
    builder += Row(items.map { case (component, constraint) => Item(component, constraint) })
  }

  def label(text: String)(implicit constraints: LabelGridBagConstraints) = (new JLabel(text), constraints.constraints)
}

object SimpleGridBagLayoutBuilder {
  case class LabelGridBagConstraints(constraints: GridBagConstraints)

  implicit val labelDefaultGridBagConstraints = LabelGridBagConstraints(GridBagConstraints.Default)
}