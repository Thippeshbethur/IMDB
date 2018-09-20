import org.apache.spark.sql.SparkSession

case class imdb(Rank: String, Title: String, Genre: String, Description: String, Director: String,
                Actors: String, Year: String,
                Runtime: Int, Rating: Float, Votes: Long)

object asdd5 {

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().appName("dataframework").
      master("local").getOrCreate()

    var sc = sparkSession.sparkContext

    import sparkSession.implicits._
    var rdd = sc.textFile("F:\\Sankir\\imdbsample.txt")

    var imdbf = rdd.map(line => line.split("\t")).map(row =>
      try {
        imdb(row(0), row(1), row(2), row(3), row(4), row(5), row(6), row(7).toInt,
          row(8).toFloat, row(9).toLong)
      }
      catch {
        case exp: IllegalArgumentException => {
          imdb("", "", "", "", "", "", "", -999, -999, -999)
        }
      }
    ).toDF()

    imdbf.createOrReplaceTempView("imdb_table")

    /*sparkSession.sql("select Title,Rating,Year from imdb_table where Votes>50000").
      select("Title", "Rating", "Year").groupBy("Year").max("Rating").show()
*/
    import org.apache.spark.sql.functions._
    imdbf.select("Title","Rating","Year").show(2,false)
  }
}

