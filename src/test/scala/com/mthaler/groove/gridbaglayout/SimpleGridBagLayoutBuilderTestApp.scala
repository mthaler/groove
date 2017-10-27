package com.mthaler.groove.gridbaglayout

import java.awt.Dimension
import javax.swing.{JFrame, JLabel, JPanel}
import SimpleGridBagLayoutBuilder._
import scala.language.existentials

object SimpleGridBagLayoutBuilderTestApp extends App {

  val frame = new JFrame("MouseHoverTestApp")
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  val p = new JPanel {

    implicit  val self = this

    gridbaglayout {
      row(new JLabel("label1"), new JLabel("label2"))
      row(new JLabel("label3"), new JLabel("label4"))
    }
  }
  frame.setContentPane(p)
  frame.setPreferredSize(new Dimension(640, 480))
  frame.pack()
  frame.setVisible(true)
}
