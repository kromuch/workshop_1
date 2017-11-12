package model

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._



import scala.concurrent.duration.Duration

case class Film(id:Option[Long],title:String,duration:Duration,
                directorID:Long,rating:Double)
class FilmTable (tag:Tag) extends Table[Film](tag,"films")
{
  val id = column[Option[Long]]("id")
  val title = column[String]("title")
  val duration = column[Duration]("duration")
  val directorID = column[Long]("director_id")
  val rating = column[Double]("rating")
  def * = (id,title,duration,directorID,rating) <> (Film.apply _ tupled,Film.unapply)
}