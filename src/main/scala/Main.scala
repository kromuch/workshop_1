import model.{Country, CountryRepository, CountryTable, FilmTable, GenreTable, StaffTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Main {
val db =
  Database.forURL("jdbc:postgresql://localhost/film?user=kromuch&password=mypass")
  val countryRepository = new CountryRepository(db)
  def main(args: Array[String]): Unit = {
    init()
  }
  def init():Unit = {
    //Await.result(db.run(CountryTable.table.schema.create),Duration.Inf) //нескінченний час виконання
    //Await.result(db.run(StaffTable.table.schema.create),Duration.Inf)
    //Await.result(db.run(GenreTable.table.schema.create),Duration.Inf)
    //Await.result(db.run(FilmTable.table.schema.create),Duration.Inf)
    databaseFill()
  }
  def databaseFill(): Unit = {
//    for (i <- 1 to 5) {
//        Await.result(countryRepository.create(Country(Some(i),s"Country #$i")),Duration.Inf)
//      }
    
  }
  //f2g f2c f2country
}
