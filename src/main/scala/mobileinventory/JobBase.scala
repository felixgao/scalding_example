/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mobileinventory
import com.twitter.scalding.{Job, Args}

class JobBase(args: Args) extends Job(args) {
  
  // Prior to 0.9.0 we need the mode, after 0.9.0 mode is a def on Job.
  override def config : Map[AnyRef,AnyRef] = {
    super.config ++ Map("cascading.app.appjar.class" -> classOf[CountMobileInventory])
  }  

}
