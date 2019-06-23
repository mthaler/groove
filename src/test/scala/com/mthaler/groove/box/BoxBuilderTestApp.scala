package com.mthaler.groove.box

import java.awt.Dimension
import javax.swing.{ JCheckBox, JFrame, JLabel }

object BoxBuilderTestApp extends App with BoxBuilder {
  val frame = new JFrame("BoxBuilderTestApp")
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  val label = new JLabel("test")
  val checkBox = new JCheckBox("check me")

  val b = horizontalBox {
    component(label)
    horizontalGlue
    component(checkBox)
  }

  frame.setContentPane(b)
  frame.setPreferredSize(new Dimension(250, 36))
  frame.pack()
  frame.setVisible(true)
}
