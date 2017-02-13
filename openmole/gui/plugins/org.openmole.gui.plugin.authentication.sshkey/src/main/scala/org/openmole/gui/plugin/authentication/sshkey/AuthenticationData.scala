package org.openmole.gui.plugin.authentication.sshkey

import org.openmole.gui.ext.data.{ AuthenticationData, Test, Error }

/*
 * Copyright (C) 12/01/17 // mathieu.leclaire@openmole.org
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

sealed trait SSHAuthenticationData extends AuthenticationData

case class PrivateKeyAuthenticationData(
    privateKey:       String         = "",
    login:            String         = "",
    cypheredPassword: String         = "",
    target:           String         = "",
    port:             String         = "22"
) extends SSHAuthenticationData {
  def name = s"$login@$target"

}