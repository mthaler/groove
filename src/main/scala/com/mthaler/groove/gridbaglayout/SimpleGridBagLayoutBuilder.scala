package com.mthaler.groove.gridbaglayout

import java.awt.{Component, GridBagLayout}
import javax.swing.JPanel

object SimpleGridBagLayoutBuilder {

  case class Item(component: Component, constraints: GridBagConstraints)

  case class Row(items: Seq[Item])

  def gridbaglayout(rows: Row*)(implicit panel: JPanel): Unit = {
    panel.setLayout(new GridBagLayout)
    for((row, rowIndex) <- rows.zipWithIndex) {)
      for ((item, colIndex) <- row.items.zipWithIndex) {
        val c = GridBagConstraints(colIndex, rowIndex)
        panel.add(item.component, c.toAWT)
      }
    }
  }

  def row(items: Component*): Row = Row(items.map(component => Item(component, GridBagConstraints.Default)))
}
