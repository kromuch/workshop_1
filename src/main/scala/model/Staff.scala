package model

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Staff(id:Option[Long],name:String,rate:Double,age:Int)

class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  val id = column[Long]("id", O.PrimaryKey)
  val name = column[String]("name")
  val rate = column[Double]("rate")
  val age = column[Int]("age")
  // замість def можна val
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id.?, name,rate,age) <> (Staff.apply _ tupled, Staff.unapply)
}
object StaffTable{
  val table = TableQuery[StaffTable]
}
class StaffRepository(db:Database) {
  val staffTableQuery = TableQuery[StaffTable]

  def create(staff:Staff) : Future[Staff] = db.run(staffTableQuery returning staffTableQuery += staff)

  def update(staff:Staff) : Future[Int] =
    db.run(staffTableQuery.filter(_.id === staff.id).update(staff)) //Int у фьючері - кількість записів, які були змінені

  def delete (staffId: Long) =
    db.run(staffTableQuery.filter(_.id===staffId).delete)

  def getById(staffId:Long) : Future[Option[Staff]] =
    db.run(staffTableQuery.filter(_.id === staffId).result.headOption)

}