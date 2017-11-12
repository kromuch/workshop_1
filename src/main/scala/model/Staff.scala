package model

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class Staff(id:Option[Long],name:String,rate:Double,age:Int)

class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val name = column[String]("name")
  val rate = column[Double]("rate")
  val age = column[Int]("age")
  // замість def можна val
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id, name,rate,age) <> (Staff.apply _ tupled, Staff.unapply)
}
