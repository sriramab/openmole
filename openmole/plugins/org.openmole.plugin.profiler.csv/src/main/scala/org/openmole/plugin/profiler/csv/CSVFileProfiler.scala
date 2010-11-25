/*
 * Copyright (C) 2010 reuillon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
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

package org.openmole.plugin.profiler.csv

import au.com.bytecode.opencsv.CSVWriter
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import org.openmole.core.implementation.mole.Profiler
import org.openmole.core.model.job.IMoleJob
import org.openmole.core.model.mole.IMoleExecution
import org.openmole.plugin.profiler.csv.MoleJobInfoToCSV._

class CSVFileProfiler(file: File) extends Profiler {

    @transient lazy val writer = new CSVWriter(new BufferedWriter(new FileWriter(file)))

    def this(moleExecution: IMoleExecution, file: File) {
      this(file)
      register(moleExecution)
    }
  
    def this(moleExecution: IMoleExecution, file: String) = this(moleExecution, new File(file))

    override def moleJobFinished(moleJob: IMoleJob) = {
        writer.writeNext(toColumns(moleJob))
    }

    override def moleExecutionFinished = {
         writer.close
    }

    override def moleExecutionStarting = {}

}
