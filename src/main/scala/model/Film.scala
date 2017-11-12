package model

import scala.concurrent.duration.Duration

case class Film(id:Option[Long],title:String,duration:Duration,
                directorID:Long,rating:Double) {

}
