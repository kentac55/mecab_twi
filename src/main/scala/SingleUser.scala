/**
  * Created by kentac55 on 16/04/23.
  */

import twitter4j._

sealed trait SingleUserT {
  def fetchList(accountName: String, limit: Int): List[String]
}

class SingleUser extends SingleUserT {
  // Pagingの引数は1以上
  // getの引数は0〜19

  def fetchList(accountName: String, limit: Int = 1): List[String] = {
    fetchTextR(accountName, limit)
  }

  private def fetchTextR(screenName: String, limit: Int = 1, c: Int = 1, acc: List[String] = Nil)(implicit t: Twitter): List[String] = {
    if(c > limit) {
      acc
    } else {
      fetchTextR(screenName, limit, c + 1, fetchTextPerPageR(t.getUserTimeline(screenName, new Paging(c))) ::: acc)(t)
    }
  }

  private def fetchTextPerPageR(timeline: ResponseList[Status], cursor: Int = 0, acc: List[String] = Nil): List[String] = {
    if(cursor > 19) {
      acc
    } else {
      fetchTextPerPageR(timeline, cursor + 1, timeline.get(cursor).getText :: acc)
    }
  }
  private implicit val twitter:Twitter = TwitterFactory.getSingleton
}
