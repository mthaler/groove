package com.mthaler.groove.gridbaglayout

import java.awt.Dimension
import javax.swing._

import com.mthaler.groove.gridbaglayout.GridBagLayoutBuilder.Constraints

object GridBagLayoutBuilderTestApp extends App {

  val frame = new JFrame("GridBagLayoutBuilderTestApp")
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  val textFieldFirstName = new JTextField()
  val textFieldLastName = new JTextField()
  val comboBoxGender = new JComboBox[String]()
  comboBoxGender.addItem("male")
  comboBoxGender.addItem("female")
  val textFieldDescription = new JTextField("Enter some text")

  val p = new JPanel with GridBagLayoutBuilder {
    implicit val labelConstraints = Constraints[JLabel](GridBagConstraints(anchor = Anchor.WEST))
    implicit val textFieldConstraints = Constraints[JTextField](GridBagConstraints(fill = Fill.HORIZONTAL, weightx = 0.5))
    implicit val comboBoxConstraints = Constraints[JComboBox[_]](GridBagConstraints(fill = Fill.HORIZONTAL, weightx = 0.5))

    gridbaglayout {
      row("First name:", textFieldFirstName)
      row("Last name:", textFieldLastName)
      row(empty, comboBoxGender)
    }

    gridbaglayout {
      row(component(textFieldDescription, 2))
    }
  }

  frame.setContentPane(p)
  frame.setPreferredSize(new Dimension(200, 120))
  frame.pack()
  frame.setVisible(true)
}
