import java.util.regex.Pattern
import org.apache.spark.sql.SparkSession
object greaterthan96 {
  //getting the humidity greater than 96 from the data
  def getda(toArray: Array[String]):Double={
    var maxtemp=0d
    toArray.foreach(line=>
      try
      {
        val temp=toArray(5).toString.toDouble
        if(temp>maxtemp)
        {
          maxtemp=temp.toDouble
        }
      }
      catch {
        case e:Exception=>{
        }
      }
    )
    return maxtemp
  }
  //getting the date the data
  def getyear(str: String):Int = {
    val patr=Pattern.compile("(\\d+).\\d+.\\d+ \\d.*")
    val line=str.toString
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
    val sc = sparksession.sparkContext
    //loading the data of the file to the variavle
    val rdd = sc.textFile("F:\\Sankir\\Dataset\\datasets\\weather\\weather_2009_2016.txt")
    //transformation of the file
    val humidity=rdd.map(line => line.split("\t")).map(arra=>(getyear(arra(0)),arra(5))).groupByKey().
      map(data => (getda(data._2.toArray),data._1 )).
      filter(line=>if(line._1>96){true}else false)
    humidity.foreach(println)
  }
}
