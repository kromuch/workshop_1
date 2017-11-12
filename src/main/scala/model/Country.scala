package model
import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._


case class Country(id:Option[Long],title:String)

class CountryTable(tag: Tag) extends Table[Country](tag, "countries") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val title = column[String]("name")
  // замість def можна val
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, title) <> (Country.apply _ tupled, Country.unapply)
}
