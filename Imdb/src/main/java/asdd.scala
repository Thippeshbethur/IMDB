import org.apache.spark.sql.SparkSession

object asdd {


     def main(args: Array[String]): Unit = {
      import org.apache.spark.sql
      val sparkSession = SparkSession.builder().appName("").master("local").getOrCreate()
      import sparkSession.implicits._
      var sc = sparkSession.sparkContext

      var rdd = sc.textFile("F:\\Sankir\\Dataset\\datasets\\weather\\weather_2009_2016.txt")

      var weather=rdd.map(line=>line.split("\t")).map(row=>try {weath(row(0),row(1).toFloat,row(2).toFloat,row(3).toFloat,row(4).toFloat,
        row(5).toFloat,row(6).toFloat,row(7).toFloat,row(8).toFloat,row(9).toFloat,row(10).toFloat,row(11).toFloat,
        row(12).toFloat,row(13).toFloat,row(14).toFloat)}).toDF()
      weather.createOrReplaceTempView("weather_dataset")
      sparkSession.sql("select max(TdegC) from weather_dataset ").show()

    }
  }
  case class weath(DateTime:String,pmbar:Float,TdegC:Float,TpotK:Float,TdewdegC:Float,
                   rh:Float,VPmaxmbr:Float,VPactmbar:Float,VPdefmbar:Float,
                   shgkg:Float,H2OCmmolmol:Float,rhogm:Float,wvms:Float,maxwvms:Float,wddeg:Float)


