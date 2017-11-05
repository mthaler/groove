package com.mthaler.groove.box

import java.awt.Dimension
import javax.swing.JFrame


object BoxBuilderTestApp extends App {
  val frame = new JFrame("BoxBuilderTestApp")
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  //frame.setContentPane(p)
  frame.setPreferredSize(new Dimension(200, 120))
  frame.pack()
  frame.setVisible(true)
}
