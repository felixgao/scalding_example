/*  
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileinventory

import com.twitter.scalding._
import com.twitter.scalding.avro._
import org.apache.avro.Schema
import com.bluekai.ds.pojo._;


class CountMobileInventory(args: Args) extends JobBase(args) {
   //PackedAvroSource reads and writes actual Avro primitives or SpecificRecords. 
   //The UnpackedAvroSource reads Avro data and flattens it into a Cascading tuple before you can use it. 
   //On the write side, the UnpackedAvroSource takes a tuple and makes it into an Avro file. 
   //val typedAvros  = TypedPipe.from[UserProfile](PackedAvroSource[UserProfile]( args("input") ))
//   val categories = Set(17,18,19,3004,12246,21490,22605,107938)
   val allowedCategoriesString = Option( args.getOrElse("categories", "17,18,19,3004,12246,21490,22605,107938") )
              .filterNot(_.isEmpty).get.split(",").map(_.toInt).distinct.toSet
   
    val typedAvros  = UnpackedAvroSource[UserProfile]( args("input") )
    val records = typedAvros.read.filter( 'cat, 'uuid ){ p: (Int, String) => (allowedCategoriesString contains p._1) && p._2.startsWith("FHz99")} 
                  .project('uuid, 'site_id, 'cat)
    val category_total = records.project('uuid, 'cat).unique('uuid, 'cat).groupBy('cat ){ _.size('cat_size) }.rename('cat -> 'cat2)
    
    val cat_site_total = records.unique('uuid, 'site_id, 'cat).groupBy('cat, 'site_id){ _.size('cat_site_size) }
    
    val final_output = cat_site_total.joinWithTiny('cat -> 'cat2, category_total )
                       .project('cat, 'site_id, 'cat_size, 'cat_site_size)

    final_output.write( Tsv( args("output") ) )

}