package com.mthaler.groove.gridbaglayout

import java.awt.Dimension
import javax.swing.{JFrame, JPanel}

object SimpleGridBagLayoutBuilderTestApp extends App {

  val frame = new JFrame("MouseHoverTestApp")
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  val p = new JPanel with SimpleGridBagLayoutBuilder {
    gridbaglayout {
      row(label("label1"), label("label2"))
      row(label("label3"), label("label4"))
    }
  }
  frame.setContentPane(p)
  frame.setPreferredSize(new Dimension(200, 100))
  frame.pack()
  frame.setVisible(true)
}
