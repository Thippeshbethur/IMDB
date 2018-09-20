import java.util.regex.Pattern

import org.apache.spark.sql.SparkSession

object barmorethan1 {
  //getting the max bar by day where bar is greater than 1 from the data
  def getdata(toArray: Array[Double]):Double={
    var maxtemp = 0d
    toArray.foreach(line =>

      try {
        val temp = toArray(0).toString.toDouble
        if (temp > maxtemp && temp > 1) {
          maxtemp = temp.toDouble
        }
      }
      catch {
        case e: Exception => {
        }

      }
    )

    return maxtemp
  }

  def getd(asdf: Array[String]):String = {

    for (asd<-asdf)
    {
      return asd
    }
    return ""
  }

  def main(args: Array[String]): Unit = {
    //creation of a session
    val sparksession = SparkSession.builder().config("spark.driver.memory", "471859200").appName("HelloWorld").master("local[*]").getOrCreate()
    //creation of a sparkcontext
    val sc = sparksession.sparkContext
    //loading the data of the file to the variavle
    val rdd = sc.textFile("F:\\Sankir\\Dataset\\datasets\\weather\\weather_2009_2016.txt")
    //transformation of the file
    /*var mass = rdd.map(line => line.split("\t"))
      .map(line => (getdate(line(0)), line(3) + "," + line(9)))
      .map(line => (line._1, line._2.split(",")(0)))
      .map(line => (line._1,getda(line._2) )).distinct().groupByKey().sortByKey(true,1).
      map(line=>(getdata(line._2.toArray),line._1)).foreach(println)*/
    rdd.map(line=>line.split("\t")).map(line=>(line(0),line)).map(line=>getd(line._2)).foreach(println)
  }

  def getda(toArray: String): Double = {
    var maxtemp = 0d
    toArray.foreach(line =>

      try {
        val temp = toArray.toString.toDouble
        if (temp > maxtemp && temp > 1) {
          maxtemp = temp.toDouble
        }
      }
      catch {
        case e: Exception => {
        }

      }
    )

    return maxtemp
  }
  //getting the date the data
  def getdate(str: String): String = {
    val patr = Pattern.compile("(\\d+).\\d+.\\d+ \\d.*")
    val line = str.toString
    val matcher = patr.matcher(line)
    if (matcher.find()) {
      return matcher.group(1).toString
    }
    return ""
  }
}
