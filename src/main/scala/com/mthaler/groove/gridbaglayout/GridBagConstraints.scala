package com.mthaler.groove.gridbaglayout

import java.awt.{ GridBagConstraints => JGridBagConstraints }

case class GridBagConstraints(
  gridx: Int = JGridBagConstraints.RELATIVE,
  gridy: Int = JGridBagConstraints.RELATIVE,
  gridwidth: Int = 1,
  gridheight: Int = 1,
  weightx: Double = 0.0,
  weighty: Double = 0.0,
  anchor: Anchor.Anchor = Anchor.CENTER,
  fill: Fill.Fill = Fill.NONE,
  insets: Insets = Insets.Empty,
  ipadx: Int = 0,
  ipady: Int = 0) {

  def toAWT: JGridBagConstraints = new JGridBagConstraints(gridx, gridy, gridwidth, gridheight,
    weightx, weighty, anchor.toInt, fill.toInt, insets.toAWT, ipadx, ipady)
}

object GridBagConstraints {

  val Default = GridBagConstraints()
}

