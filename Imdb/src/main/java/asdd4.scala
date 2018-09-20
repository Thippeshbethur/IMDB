import java.util.regex.Pattern
import org.apache.spark.sql.SparkSession

object asdd4 {
  def getyear(str: String):String = {
    val patr=Pattern.compile("(\\d+).\\d+.\\d+ \\d.*")
    val line=str.toString
    val matcher=patr.matcher(line)
    if(matcher.find()){
      return matcher.group(1).toString
    }
    return ""
  }
  def main(args: Array[String]): Unit = {
    import org.apache.spark.sql
    val sparkSession = SparkSession.builder().appName("").master("local").getOrCreate()
    import sparkSession.implicits._
    var sc = sparkSession.sparkContext
    var rdd = sc.textFile("F:\\Sankir\\Dataset\\datasets\\weather\\weather_2009_2016.txt")
    var weather=rdd.map(line=>line.split("\t")).map(row=>try {weath1(getyear(row(0)),
      row(1).toFloat,row(2).toFloat,row(3).toFloat,row(4).toFloat,
      row(5).toFloat,row(6).toFloat,row(7).toFloat,row(8).toFloat,
      row(9).toFloat,row(10).toFloat,row(11).toFloat,
      row(12).toFloat,row(13).toFloat,row(14).toFloat)}).toDF()
    weather.createOrReplaceTempView("weather_dataset")
    try {
      sparkSession.sql("select DateTime,TpotK from weather_dataset where VPdefmbar>1").groupBy("DateTime").
        min().show(31)
    }
  }
}
case class weath1(DateTime:String,pmbar:Float,TdegC:Float,TpotK:Float,TdewdegC:Float,
                  rh:Float,VPmaxmbr:Float,VPactmbar:Float,VPdefmbar:Float,
                  shgkg:Float,H2OCmmolmol:Float,rhogm:Float,wvms:Float,maxwvms:Float,wddeg:Float)


