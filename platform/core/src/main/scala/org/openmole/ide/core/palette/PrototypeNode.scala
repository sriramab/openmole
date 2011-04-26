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

import java.awt.datatransfer.DataFlavor
import org.openide.util.datatransfer.ExTransferable
import org.openmole.ide.core.properties.IPrototypeFactoryUI
import org.openmole.ide.core.commons.ApplicationCustomize

class PrototypeNode(key: DataFlavor,prototypeFactory: IPrototypeFactoryUI) extends GenericNode(key, prototypeFactory) {

  override def drag= {
    val retValue = ExTransferable.create(super.drag)
    retValue.put(new ExTransferable.Single(ApplicationCustomize.PROTOTYPE_DATA_FLAVOR){override def getData= prototypeFactory})
    retValue
  }
}
//import java.awt.datatransfer.DataFlavor;
//import java.awt.datatransfer.Transferable;
//import java.awt.datatransfer.UnsupportedFlavorException;
//import java.io.IOException;
//import org.openide.util.datatransfer.ExTransferable;
//import org.openmole.commons.exception.UserBadDataError;
//import org.openmole.ide.core.commons.ApplicationCustomize;
//import org.openmole.ide.core.workflow.implementation.IEntityUI;
//import org.openmole.ide.core.workflow.implementation.Preferences;
//import org.openmole.ide.core.workflow.implementation.PropertyManager;

//public class PrototypeNode extends GenericNode {
//
//    IEntityUI prototype;
//
//    public PrototypeNode(DataFlavor key,
//            IEntityUI prototype) throws UserBadDataError {
//        super(key, Preferences.getInstance().getProperties(Category.CategoryName.PROTOTYPE_INSTANCE,prototype.getType()).getProperty(PropertyManager.THUMB_IMG), prototype.getName());
//        this.prototype = prototype;
//    }
//
//    //DND start
//    @Override
//    public Transferable drag() throws IOException {
//        ExTransferable retValue = ExTransferable.create(super.drag());
//        retValue.put(new ExTransferable.Single(ApplicationCustomize.PROTOTYPE_DATA_FLAVOR) {
//
//            @Override
//            protected Object getData() throws IOException, UnsupportedFlavorException {
//                return prototype;
//            }
//        });
//        return retValue;
//    }
//}