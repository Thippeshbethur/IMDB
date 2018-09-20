import java.util.regex.Pattern

import org.apache.spark.sql.SparkSession

object Maxtempperyr {

  //getting the year from the data
  def getyear(str: String):Int = {
    val patr=Pattern.compile("\\d+.\\d+.(\\d+) \\d.*")
    val line=str
    val matcher=patr.matcher(line)
    if(matcher.find()){
      return matcher.group(1).toInt
    }
    return 0
  }
  def main(args: Array[String]): Unit = {
    //creation of a session
    val sparksession = SparkSession.builder().config("spark.driver.memory", "471859200").appName("HelloWorld").master("local[*]").getOrCreate()
    //creation of a sparkcontext
    val sparkcon = sparksession.sparkContext
    //loading the data of the file to the variavle
    val rdd = sparkcon.textFile("F:\\Sankir\\Dataset\\datasets\\weather\\weather_2009_2016.txt")
    //transformation of the file
    println("The max temp per year is:")
    rdd.map(line => line.split("\t")).map(arra=>(getyear(arra(0)),arra(3))). groupByKey().map(data => (data._1, (data._2.toArray.map(_.toFloat).max))).foreach(println)
  }
}
