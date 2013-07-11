/*
 * Copyright (C) 2011 Mathieu Mathieu Leclaire <mathieu.Mathieu Leclaire at openmole.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openmole.ide.plugin.task.stat

import org.openmole.ide.core.model.factory.ITaskFactoryUI
import org.openmole.core.model.task.ITask
import org.openmole.ide.core.model.builder.IPuzzleUIMap
import org.openmole.ide.core.implementation.builder.SceneFactory
import org.openmole.plugin.task.stat.SumTask

class SumTaskFactoryUI extends ITaskFactoryUI {
  override def toString = "Sum"

  def buildDataUI = new SumTaskDataUI

  def buildDataProxyUI(task: ITask, uiMap: IPuzzleUIMap) = {
    val t = SceneFactory.as[SumTask](task)
    uiMap.task(t, x ⇒ new MedianTaskDataUI(t.name, t.sequences.toList.map { p ⇒ (uiMap.prototypeMap(p._1), uiMap.prototypeMap(p._2)) }))
  }

  override def category = List("Task", "Stat")
}
