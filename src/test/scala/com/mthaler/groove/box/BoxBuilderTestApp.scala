package com.mthaler.groove.box

import java.awt.Dimension
import javax.swing.{JCheckBox, JFrame, JLabel}
import BoxBuilder._

object BoxBuilderTestApp extends App {
  val frame = new JFrame("BoxBuilderTestApp")
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  val label = new JLabel("test")
  val checkBox = new JCheckBox("check me")

  val b = horizontalBox(
    label,
    horizontalGlue,
    checkBox
  )

  frame.setContentPane(b)
  frame.setPreferredSize(new Dimension(200, 120))
  frame.pack()
  frame.setVisible(true)
}
