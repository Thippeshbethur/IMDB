import org.apache.spark.sql.SparkSession

object Dataframe {
  def main(args: Array[String]): Unit = {
    import org.apache.spark.sql
    //creation of a session

    val sparksession = SparkSession.builder().config("spark.driver.memory", "471859200").appName("HelloWorld").master("local[*]").getOrCreate()
    import sparksession.implicits._
    //creation of a sparkcontext
    val sc = sparksession.sparkContext
    //loading the data of the file to the variavle
    val rdd = sc.textFile("F:\\Sankir\\Dataset\\datasets\\weather\\weather_2009_2016.txt")
    //transformation of the file
    val imdbdf = rdd.map(_.split("\t")).map(ROW =>
      try {
        IMDB(ROW(0), ROW(1), ROW(2), ROW(3), ROW(4), ROW(5), ROW(6), ROW(7), ROW(8), ROW(9), ROW(10), ROW(11), ROW(12))
      }
      catch {
        case exp0@(_: ArrayIndexOutOfBoundsException | _: NumberFormatException) => {
          IMDB("", "", "", "", "", "", "", "", "", "", "", "", "")
        }
      }
    ).toDF
    //creation of table view
    imdbdf.createOrReplaceTempView("thippesh")
    imdbdf.printSchema()
    //calling the data from the tableview
    sparksession.sql("select count(Atm_pres )from thippesh where Atm_pres>36.8 ").show(30, false)
  }
}

//creation of a schema to add the structure schema from the file
case class IMDB(Date_an_time: String, temp: String, Atm_pres: String, Dew_point: String, humidity: String
                , saturation: String, actual: String, water: String
                , specific_water: String, airdensity: String, wind_velocity: String, max_wind_velcoity: String, wind_direction: String)
