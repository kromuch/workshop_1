package model

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class Genre(id:Option[Long],title:String,description:Option[String])

class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val title = column[String]("title")
  val description = column[Option[String]]("description")
  // замість def можна val
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, title, description) <> (Genre.apply _ tupled, Genre.unapply)
}

