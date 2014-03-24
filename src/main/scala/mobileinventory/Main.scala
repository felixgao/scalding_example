/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileinventory


import com.twitter.scalding._
import com.twitter.scalding.avro._
import org.apache.hadoop.util.GenericOptionsParser
import org.apache.hadoop.conf.Configuration

class Main(args: Args) extends CountMobileInventory(args) {
  /********
   * Use the following code to start the job
    val mode = new Hdfs(true, conf)
    val arguments = Mode.putMode(mode, Args(""))
    val job: com.twitter.scalding.Job = new MyJob(arguments, <params> ...)
    val flow = job.buildFlow
    flow.writeDOT(<some_path>)
    flow.start()
   */
    
}
