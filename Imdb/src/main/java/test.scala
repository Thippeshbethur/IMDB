import org.apache.spark.sql.SparkSession

object test {

  def getda(toint: Int): Boolean = {
    if(toint%2==0)
      {
        return true
      }
    else false
  }

  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder().appName("dataframework").
      master("local").getOrCreate()
    var sc = sparkSession.sparkContext
    var ddd=sc.broadcast(1,10)
    //println(ddd.value._2)
    val rdd=sc.textFile("C:\\Users\\Thipu\\Desktop\\text1.txt")
    rdd.flatMap(line=>line.split(",")).map(line=>line).filter(line=>if(line.length.equals(10)) true else false).foreach(println)

  }
}
