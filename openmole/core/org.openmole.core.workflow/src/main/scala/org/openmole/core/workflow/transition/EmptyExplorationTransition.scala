/*
 * Copyright (C) 2012 reuillon
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

package org.openmole.core.workflow.transition

import org.openmole.core.context.{ Context, Val }
import org.openmole.core.expansion.{ Condition, FromContext }
import org.openmole.core.workflow.mole._
import org.openmole.core.workflow.validation.ValidateTransition
import org.openmole.core.workspace.NewFile

import scala.collection.mutable.ListBuffer

class EmptyExplorationTransition(start: Capsule, end: Slot, size: FromContext[Int], condition: Condition = Condition.True, filter: BlockList = BlockList.empty) extends ExplorationTransition(start, end, condition, filter) with ValidateTransition {

  override def validate(inputs: Seq[Val[_]]) = condition.validate(inputs)

  override def submitIn(context: Context, ticket: Ticket, subMole: SubMoleExecution, executionContext: MoleExecutionContext) = {
    import executionContext.services._
    for (i ← 0 until size.from(context)) ITransition.submitNextJobsIfReady(this)(ListBuffer() ++ filtered(context).values, subMole.moleExecution.nextTicket(ticket), subMole)
  }

}
