package com.mthaler.groove.gridbaglayout

import java.awt.{Component, GridBagLayout}
import javax.swing.{JComboBox, JLabel, JPanel, JTextField}
import scala.collection.mutable.ArrayBuffer
import scala.language.implicitConversions

trait GridBagLayoutBuilder {

  me: JPanel =>

  import GridBagLayoutBuilder._

  private val builder = ArrayBuffer.empty[Row]

  protected case class Item(component: Component, constraint: GridBagConstraints)

  protected case class Row(items: Seq[Item])

  protected def gridbaglayout(body: => Unit): Unit = {
    setLayout(new GridBagLayout)
    body
    for ((row, rowIndex) <- builder.zipWithIndex) {
      var colIndex = 0
      for (item <- row.items) {
        val component = item.component
        // update constraint with gridx and gridy position
        val constraint = item.constraint.copy(gridx = colIndex, gridy = rowIndex)
        add(component, constraint.toAWT)
        // update column index taking gridwith into account
        colIndex += constraint.gridwidth
      }

      for ((item, colIndex) <- row.items.zipWithIndex) {
        add(item.component, item.constraint.copy(gridx = colIndex, gridy = rowIndex).toAWT)
      }
    }
  }

  protected def row(items: (Component, GridBagConstraints)*) = {
    builder += Row(items.map { case (component, constraint) => Item(component, constraint) })
  }

  protected def empty = (new EmptyComponent, GridBagConstraints.Default)

  protected def empty(gridwidth: Int) = (new EmptyComponent, GridBagConstraints.Default.copy(gridwidth = gridwidth))

  protected implicit def label(label: JLabel)(implicit constraints: LabelGridBagConstraints) = (label, constraints.constraints)

  protected def label(label: JLabel, gridwidth: Int)(implicit constraints: LabelGridBagConstraints) = (label, constraints.constraints)

  protected implicit def string2label(text: String)(implicit constraints: LabelGridBagConstraints) = (new JLabel(text), constraints.constraints)

  protected implicit def textfield(textField: JTextField)(implicit constraints: TextFieldGridBagConstraints) = (textField, constraints.constraints)

  protected def textfield(textField: JTextField, gridwidth: Int)(implicit constraints: TextFieldGridBagConstraints) = (textField, constraints.constraints.copy(gridwidth = gridwidth))

  protected implicit def combobox[T](comboBox: JComboBox[T])(implicit constrains: ComboBoxGridBagConstraints) = (comboBox, constrains.constraints)

  protected def combobox[T](comboBox: JComboBox[T], gridwidth: Int)(implicit constrains: ComboBoxGridBagConstraints) = (comboBox, constrains.constraints.copy(gridwidth = gridwidth))
}

object GridBagLayoutBuilder {
  case class LabelGridBagConstraints(constraints: GridBagConstraints)
  case class TextFieldGridBagConstraints(constraints: GridBagConstraints)
  case class ComboBoxGridBagConstraints(constraints: GridBagConstraints)

  implicit val defaultLabelGridBagConstraints = LabelGridBagConstraints(GridBagConstraints.Default)
  implicit val defaultTextFieldGridBagConstraints = TextFieldGridBagConstraints(GridBagConstraints.Default)
  implicit val defaultComboBoxGridBagConstraints = ComboBoxGridBagConstraints(GridBagConstraints.Default)
}