import org.apache.spark.sql.SparkSession

object test1 {
  val sparkSession = SparkSession.builder().appName("dataframework").
    master("local").getOrCreate()
  val sc = sparkSession.sparkContext
  val rdd = sc.textFile("C:\\Users\\Thipu\\Desktop\\text1.txt")
  var numBlankLines = sc.accumulator(0)

  def main(args: Array[String]): Unit = {
    var words = rdd.flatMap(toWords)
    words.saveAsTextFile("C:\\Users\\Thipu\\Desktop\\words3")
  }
  def toWords(line: String): Array[String] = {
    if (line.length == 0) {
      numBlankLines += 1
    }
    return line.split(" ");
  }


}

