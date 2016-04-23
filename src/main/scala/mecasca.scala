/**
  * Created by kentac55 on 16/03/30.
  */

import org.chasen.mecab._

object mecasca extends App{
  System.loadLibrary("MeCab")
//  val tagger = new Tagger("-d /usr/lib/mecab/dic/mecab-ipadic-neologd/ -O wakati")
  val tagger = new Tagger("-d /usr/lib/mecab/dic/mecab-ipadic-neologd/")
  val parsed = tagger.parse("10日放送の「中居正広のミになる図書館」（テレビ朝日系）で、SMAPの中居正広が、篠原信一の過去の勘違いを明かす一幕があった。")
  println(parsed.split(" ").toList)


}
