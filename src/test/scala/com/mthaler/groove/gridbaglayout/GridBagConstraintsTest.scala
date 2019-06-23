package com.mthaler.groove.gridbaglayout

import org.scalatest.FunSuite
import java.awt.{ GridBagConstraints => JGridBagConstraints }
import java.awt.{ Insets => JInsets }

class GridBagConstraintsTest extends FunSuite {

  test("toAWT") {
    val c = GridBagConstraints(3, 4, 5, 6, 0.3, 0.6, Anchor.NORTH, Fill.HORIZONTAL, Insets(1, 2, 3, 4), 7, 8)
    val result = c.toAWT
    assert(result.gridx === 3)
    assert(result.gridy === 4)
    assert(result.gridwidth === 5)
    assert(result.gridheight === 6)
    assert(result.weightx === 0.3)
    assert(result.weighty === 0.6)
    assert(result.anchor === JGridBagConstraints.NORTH)
    assert(result.fill === JGridBagConstraints.HORIZONTAL)
    assert(result.insets === new JInsets(1, 2, 3, 4))
    assert(result.ipadx === 7)
    assert(result.ipady === 8)
  }
}
