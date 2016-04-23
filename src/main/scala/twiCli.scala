/**
  * Created by kentac55 on 16/03/30.
  */

import twitter4j._
import scala.collection.JavaConverters._

object twiCli extends App{
  val twitter = TwitterFactory.getSingleton
  val query = new Query("#春から法政")
  fetch(query)

  def fetch(query: Query, acc:Int = 0): Unit = {
    if (acc > 10) System.exit(0)
    val res = twitter.search(query)
    val tweets = res.getTweets
    tweets.asScala.foreach(tweet => {
      if (tweet.getText.length < 50) {
        println("------------")
        println(tweet.getUser.getScreenName)
        println(tweet.getText)
      }
    })
    val next = Option(res.nextQuery())
    next match {
      case Some(v) =>
        println("Fetch Start")
        fetch(v, acc + 1)
      case _ =>
        println("end")
        System.exit(0)
    }
  }
}
