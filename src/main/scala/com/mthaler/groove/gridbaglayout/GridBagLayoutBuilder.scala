package com.mthaler.groove.gridbaglayout

import java.awt.{Component, GridBagLayout}
import javax.swing.{JComboBox, JLabel, JPanel, JTextField}
import scala.collection.mutable.ArrayBuffer
import scala.language.implicitConversions

trait GridBagLayoutBuilder {

  me: JPanel =>

  import GridBagLayoutBuilder._

  private val builder = ArrayBuffer.empty[Row]
  private var rowIndex = 0

  protected case class Item(component: Component, constraint: GridBagConstraints)

  protected case class Row(items: Seq[Item])

  protected def gridbaglayout(body: => Unit): Unit = {
    setLayout(new GridBagLayout)
    body
    for (row <- builder) {
      var colIndex = 0
      for (item <- row.items) {
        val component = item.component
        // update constraint with gridx and gridy position
        val constraint = item.constraint.copy(gridx = colIndex, gridy = rowIndex)
        add(component, constraint.toAWT)
        // update column index taking gridwith into account
        colIndex += constraint.gridwidth
      }
      rowIndex += 1
    }
  }

  protected def row(items: (Component, GridBagConstraints)*) = {
    builder += Row(items.map { case (component, constraint) => Item(component, constraint) })
  }

  protected def empty = (new EmptyComponent, GridBagConstraints.Default)

  protected def empty(gridwidth: Int) = (new EmptyComponent, GridBagConstraints.Default.copy(gridwidth = gridwidth))

  protected implicit def label(label: JLabel)(implicit constraints: Constraints[JLabel]) = (label, constraints.constraints)

  protected def label(label: JLabel, gridwidth: Int)(implicit constraints: Constraints[JLabel]) = (label, constraints.constraints)

  protected implicit def string2label(text: String)(implicit constraints: Constraints[JLabel]) = (new JLabel(text), constraints.constraints)

  protected implicit def textfield(textField: JTextField)(implicit constraints: Constraints[JTextField]) = (textField, constraints.constraints)

  protected def textfield(textField: JTextField, gridwidth: Int)(implicit constraints: Constraints[JTextField]) = (textField, constraints.constraints.copy(gridwidth = gridwidth))

  protected implicit def combobox[T](comboBox: JComboBox[T])(implicit constrains: Constraints[JComboBox[_]]) = (comboBox, constrains.constraints)

  protected def combobox[T](comboBox: JComboBox[T], gridwidth: Int)(implicit constrains: Constraints[JComboBox[_]]) = (comboBox, constrains.constraints.copy(gridwidth = gridwidth))
}

object GridBagLayoutBuilder {
  case class Constraints[T](constraints: GridBagConstraints)

  implicit val defaultLabelGridBagConstraints = Constraints[JLabel](GridBagConstraints.Default)
  implicit val defaultTextFieldGridBagConstraints = Constraints[JTextField](GridBagConstraints.Default)
  implicit val defaultComboBoxGridBagConstraints = Constraints[JComboBox[_]](GridBagConstraints.Default)
}