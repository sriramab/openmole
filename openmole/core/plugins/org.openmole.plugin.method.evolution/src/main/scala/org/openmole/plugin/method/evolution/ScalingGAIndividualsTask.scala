/*
 * Copyright (C) 2012 Romain Reuillon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.openmole.plugin.method.evolution

import fr.iscpif.mgo._
import org.openmole.core.implementation.data._
import org.openmole.core.implementation.task._
import org.openmole.core.model.data._
import org.openmole.core.model.sampling._
import org.openmole.core.model.domain._
import org.openmole.core.model.task._
import ga._

object ScalingGAIndividualsTask {

  def apply(evolution: GAAlgorithm)(
    name: String,
    individuals: Prototype[Array[Individual[evolution.G, evolution.P, evolution.F]]])(implicit plugins: PluginSet) = {

    val (_evolution, _name, _individuals) = (evolution, name, individuals)

    new TaskBuilder { builder ⇒

      addInput(individuals)
      evolution.inputsPrototypes foreach { i ⇒ addOutput(i.toArray) }
      evolution.outputPrototypes foreach { o ⇒ addOutput(o.toArray) }

      def toTask = new ScalingGAIndividualsTask with Built {
        val evolution = _evolution
        val name = _name
        val individuals = _individuals.asInstanceOf[Prototype[Array[Individual[evolution.G, evolution.P, evolution.F]]]]
      }
    }
  }

}

sealed abstract class ScalingGAIndividualsTask extends Task {

  val evolution: GAAlgorithm
  val individuals: Prototype[Array[Individual[evolution.G, evolution.P, evolution.F]]]

  override def process(context: Context) =
    evolution.toVariables(context(individuals), context)

}
