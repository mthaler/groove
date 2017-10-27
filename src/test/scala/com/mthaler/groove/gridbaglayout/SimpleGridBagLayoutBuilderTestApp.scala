package com.mthaler.groove.gridbaglayout

import java.awt.Dimension
import javax.swing.{JFrame, JPanel, JTextField}

import com.mthaler.groove.gridbaglayout.SimpleGridBagLayoutBuilder.{LabelGridBagConstraints, TextFieldGridBagConstraints}

object SimpleGridBagLayoutBuilderTestApp extends App {

  val frame = new JFrame("SimpleGridBagLayoutBuilderTestApp")
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  val textFieldFirstName = new JTextField()
  val textFieldLastName = new JTextField()

  val p = new JPanel with SimpleGridBagLayoutBuilder {
    implicit val labelConstraints = LabelGridBagConstraints(GridBagConstraints(anchor = Anchor.WEST))
    implicit val textFieldConstraints = TextFieldGridBagConstraints(GridBagConstraints(fill = Fill.HORIZONTAL, weightx = 0.5))

    gridbaglayout {
      row("First name:", textFieldFirstName)
      row("Last name:", textFieldLastName)
    }
  }

  frame.setContentPane(p)
  frame.setPreferredSize(new Dimension(200, 100))
  frame.pack()
  frame.setVisible(true)
}
