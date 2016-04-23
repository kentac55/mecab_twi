import scala.collection.immutable.TreeMap

/**
  * Created by kentac55 on 16/04/23.
  */
trait list2T {
  def aggregate2: Map[String, Int]
}

trait map2T {
  def group2: TreeMap[Int, List[String]]
}

object Implicits {
  implicit class list2(val src: List[String]) extends list2T {
    def aggregate2: Map[String, Int] = {
      aggregateR(src)
    }
    private def aggregateR(lst: List[String], acc: Map[String, Int] = Map()): Map[String, Int] = {
      // Mapの要素数が5以上になるとMapからHashMapに変換される
      if (lst == Nil) {
        acc
      } else {
        aggregateR(lst.tail, acc.updated(lst.head, acc.getOrElse(lst.head, 0) + 1))
      }
    }
  }
}
