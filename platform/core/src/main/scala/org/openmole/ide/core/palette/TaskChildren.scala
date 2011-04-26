/*
 * Copyright (C) 2011 Mathieu leclaire <mathieu.leclaire at openmole.org>
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

package org.openmole.ide.core.palette

import java.util.ArrayList
import org.openide.nodes.Node
import org.openide.util.Lookup
import org.openmole.ide.core.properties.ITaskFactoryUI
import org.openmole.ide.core.commons.ApplicationCustomize
import scala.collection.JavaConversions._

class TaskChildren extends GenericChildren{

  override def initCollection: java.util.List[Node] = {
    val lookup=  Lookup.getDefault.lookupAll(classOf[ITaskFactoryUI])
    val childrenNodes = new ArrayList[Node](lookup.size)
  //  TasksUI.getAll.foreach(t=>{childrenNodes.add(new TaskNode(ApplicationCustomize.TASK_DATA_FLAVOR,t))})
    lookup.foreach(t=>{childrenNodes.add(new TaskNode(ApplicationCustomize.TASK_DATA_FLAVOR,t))})
    childrenNodes
  }
}
//package org.openmole.ide.core.palette;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import org.openide.nodes.Node;
//import org.openmole.commons.exception.UserBadDataError;
//import org.openmole.ide.core.commons.ApplicationCustomize;
//import org.openmole.ide.core.exception.MoleExceptionManagement;
//import org.openmole.ide.core.workflow.implementation.IEntityUI;
//import org.openmole.ide.core.workflow.implementation.TasksUI;
//
///**
// *
// * @author Mathieu Leclaire <mathieu.leclaire@openmole.fr>
// */
//public class TaskChildren extends GenericChildren {
//
//    @Override
//    protected java.util.List<Node> initCollection() {
//
//        Collection<IEntityUI> tasks = TasksUI.getInstance().getAll();
//        ArrayList childrenNodes = new ArrayList(tasks.size());
//        for (IEntityUI task : tasks) {
//            try {
//                childrenNodes.add(new TaskNode(ApplicationCustomize.TASK_DATA_FLAVOR,
//                        task));
//            } catch (UserBadDataError ex) {
//                MoleExceptionManagement.showException(ex);
//            }
//
//        }
//        return childrenNodes;
//    }
//}
