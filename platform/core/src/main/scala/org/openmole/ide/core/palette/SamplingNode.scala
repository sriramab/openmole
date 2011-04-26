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
import org.openmole.ide.core.properties.ISamplingFactoryUI
import org.openide.util.datatransfer.ExTransferable
import org.openmole.ide.core.commons.ApplicationCustomize

class SamplingNode(key: DataFlavor, samplingFactory: ISamplingFactoryUI) extends GenericNode(key, samplingFactory) {

  override def drag= {
    val retValue = ExTransferable.create(super.drag)
    retValue.put(new ExTransferable.Single(ApplicationCustomize.SAMPLING_DATA_FLAVOR){override def getData= samplingFactory})
    retValue
  }
}
//package org.openmole.ide.core.palette;
//
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
//
///**
// *
// * @author Mathieu Leclaire <mathieu.leclaire@openmole.org>
// */
//public class SamplingNode extends GenericNode {
//
//    private IEntityUI sampling;
//
//    public SamplingNode(DataFlavor key,
//            IEntityUI sampling) throws UserBadDataError {
//        super(key, Preferences.getInstance().getProperties(Category.CategoryName.SAMPLING_INSTANCE, sampling.getType()).getProperty(PropertyManager.THUMB_IMG), sampling.getName());
//        this.sampling = sampling;
//    }
//
//    @Override
//    public Transferable drag() throws IOException {
//        ExTransferable retValue = ExTransferable.create(super.drag());
//        retValue.put(new ExTransferable.Single(ApplicationCustomize.SAMPLING_DATA_FLAVOR) {
//
//            @Override
//            protected Object getData() throws IOException, UnsupportedFlavorException {
//                return sampling;
//            }
//        });
//        return retValue;
//    }
//}