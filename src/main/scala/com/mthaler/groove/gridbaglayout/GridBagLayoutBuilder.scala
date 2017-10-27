package com.mthaler.groove.gridbaglayout

import java.awt.{Component, GridBagLayout}
import javax.swing.{JComboBox, JLabel, JPanel, JTextField}
import scala.collection.mutable.ArrayBuffer
import scala.language.implicitConversions

trait GridBagLayoutBuilder {

  me: JPanel =>

  import GridBagLayoutBuilder._

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

  def empty = (new EmptyComponent, GridBagConstraints.Default)

  implicit def label(label: JLabel)(implicit constraints: LabelGridBagConstraints) = (label, constraints.constraints)

  implicit def label2String(text: String)(implicit constraints: LabelGridBagConstraints) = (new JLabel(text), constraints.constraints)

  implicit def textField(textField: JTextField)(implicit constraints: TextFieldGridBagConstraints) = (textField, constraints.constraints)

  implicit def comboBox[T](comboBox: JComboBox[T])(implicit constrains: ComboBoxGridBagConstraints) = (comboBox, constrains.constraints)
}

object GridBagLayoutBuilder {
  case class LabelGridBagConstraints(constraints: GridBagConstraints)
  case class TextFieldGridBagConstraints(constraints: GridBagConstraints)
  case class ComboBoxGridBagConstraints(constraints: GridBagConstraints)

  implicit val defaultLabelGridBagConstraints = LabelGridBagConstraints(GridBagConstraints.Default)
  implicit val defaultTextFieldGridBagConstraints = TextFieldGridBagConstraints(GridBagConstraints.Default)
  implicit val defaultComboBoxGridBagConstraints = ComboBoxGridBagConstraints(GridBagConstraints.Default)
}