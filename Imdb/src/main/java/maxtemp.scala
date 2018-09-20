
import org.apache.spark.sql.SparkSession

object maxtemp {
  def main(args: Array[String]): Unit = {
    //creation of a session
    val sparksession = SparkSession.builder().config("spark.driver.memory", "471859200").appName("HelloWorld").master("local[*]").getOrCreate()
    //creation of a sparkcontext
    val sparkcontext1 = sparksession.sparkContext
    //loading the data of the file to the variavle
    val rdd = sparkcontext1.textFile("F:\\Sankir\\Dataset\\datasets\\weather\\weather_2009_2016.txt")
    try {
      //transformation of the file
      val maxtemp = rdd.map(line => line.split("\t")(3))
      println("The max temp in last 7 years is:")
      println(maxtemp.max())
    }
    catch {
      case exception: Exception => {

      }
    }
  }
}
