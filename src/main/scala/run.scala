/**
  * Created by kentac55 on 16/04/23.
  */
import org.chasen.mecab._
import Implicits._


object run extends App {
  System.loadLibrary("MeCab")
  val tagger = new Tagger("-d /usr/lib/mecab/dic/mecab-ipadic-neologd/ -O wakati")

  val a = new SingleUser
  val res = a.fetchList("kentac55", 2)

  def parser(input: List[String], acc: List[String] = Nil): List[String] = {
    if (input == Nil) {
      acc
    } else {
      parser(input.tail, tagger.parse(input.head).split(" ").toList.reverse_:::(acc))
    }
  }
  println(parser(res).aggregate2)
}
