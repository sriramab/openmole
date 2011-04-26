/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openmole.ide.core.properties

import java.awt.Color
import org.openmole.ide.core.workflow.implementation.TaskUI

trait ITaskFactoryUI extends IFactoryUI {

  override def entity(name: String) = new TaskUI(name, this)
  
  override def entity = new TaskUI(this)
  
  // Default border task color
  override def borderColor = new Color(255,0,0)
  
  // Default background task color
  override def backgroundColor = new Color(255,0,0,128)
  
  // Default background task image
  override def imagePath = "img/thumb/default.png"
}
