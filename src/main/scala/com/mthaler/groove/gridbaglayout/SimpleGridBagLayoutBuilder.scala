package com.mthaler.groove.gridbaglayout

import java.awt.{Component, GridBagLayout}
import javax.swing.{JLabel, JPanel, JTextField}
import scala.collection.mutable.ArrayBuffer
import scala.language.implicitConversions

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
        add(item.component, item.constraints.copy(gridx = colIndex, gridy = rowIndex).toAWT)
      }
    }
  }

  def row(items: (Component, GridBagConstraints)*) = {
    builder += Row(items.map { case (component, constraint) => Item(component, constraint) })
  }

  implicit def label(text: String)(implicit constraints: LabelGridBagConstraints) = (new JLabel(text), constraints.constraints)

  implicit def textField(textField: JTextField)(implicit constraints: TextFieldGridBagConstraints) = (textField, constraints.constraints)
}

object SimpleGridBagLayoutBuilder {
  case class LabelGridBagConstraints(constraints: GridBagConstraints)
  case class TextFieldGridBagConstraints(constraints: GridBagConstraints)

  implicit val labelDefaultGridBagConstraints = LabelGridBagConstraints(GridBagConstraints.Default)
  implicit val textFieldDefaultGridBagConstraints = TextFieldGridBagConstraints(GridBagConstraints.Default)
}