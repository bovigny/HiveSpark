/**
 * Created by cbovigny on 10/23/15.
 */


/**
 * Created by cbovigny on 10/22/15.
 */

import com.google.common.io.{Files, ByteStreams}

import java.io.File

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql._
import org.apache.spark.sql.hive.HiveContext


object HiveSpark {



  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("HiveFromSpark")
    val sc = new SparkContext(sparkConf)

    // A hive context adds support for finding tables in the MetaStore and writing queries
    // using HiveQL. Users who do not have an existing Hive deployment can still create a
    // HiveContext. When not configured by the hive-site.xml, the context automatically
    // creates metastore_db and warehouse in the current directory.
    val hiveContext = new HiveContext(sc)
    val sqlContext = new org.apache.spark.sql.hive.HiveContext(sc)
    import hiveContext.implicits._
    import hiveContext.sql


    // Here you have to complete the query :
    val YearCount=hiveContext.sql("from bxdataset SELECT yearofpublication, count(booktitle) group by yearofpublication order by yearofpublication").collect().foreach(println)


    sc.stop()
  }


}

