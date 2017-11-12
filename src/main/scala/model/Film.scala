package model

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future
import scala.concurrent.duration.Duration

case class Film(id:Option[Long],title:String,duration:Duration,
                directorId:Long,rating:Double)
class FilmTable (tag:Tag) extends Table[Film](tag,"films")
{
  val id = column[Long]("id")
  val title = column[String]("title")
  val duration = column[Duration]("duration")
  val directorId = column[Long]("director_id")
  val rating = column[Double]("rating")

  val directorFk = foreignKey("director is fk", directorId, TableQuery[StaffTable])(_.id)

  def * = (id.?,title,duration,directorId,rating) <> (Film.apply _ tupled,Film.unapply)
}


case class FilmToGenre(id:Option[Long],filmId:Long,genreId:Long)
class FilmToGenreTable (tag:Tag) extends Table[FilmToGenre](tag,"film_to_genre")
{
  val id = column[Long]("id")
  val filmId = column[Long]("film_id")
  val genreId = column[Long]("genre_id")

  val filmIdFk = foreignKey("film is fk", filmId, TableQuery[FilmTable])(_.id)
  val genreIdFk = foreignKey("genre is fk",genreId,TableQuery[GenreTable])(_.id)

  def * = (id.?,filmId,genreId) <> (FilmToGenre.apply _ tupled,FilmToGenre.unapply)
}


case class FilmToCast(id:Option[Long],filmId:Long,staffId:Long)
class FilmToCastTable (tag:Tag) extends Table[FilmToCast](tag,"film_to_cast")
{
  val id = column[Long]("id")
  val filmId = column[Long]("film_id")
  val staffId = column[Long]("staff_id")

  val filmIdFk = foreignKey("film is fk", filmId, TableQuery[FilmTable])(_.id)
  val staffIdFk = foreignKey("genre is fk",staffId,TableQuery[StaffTable])(_.id)

  def * = (id.?,filmId,staffId) <> (FilmToCast.apply _ tupled,FilmToCast.unapply)
}


case class FilmToCountry(id:Option[Long],filmId:Long,countryId:Long)
class FilmToCountryTable (tag:Tag) extends Table[FilmToCountry](tag,"film_to_country")
{
  val id = column[Long]("id")
  val filmId = column[Long]("film_id")
  val countryId = column[Long]("country_id")

  val filmIdFk = foreignKey("film is fk", filmId, TableQuery[FilmTable])(_.id)
  val countryIdFk = foreignKey("genre is fk",countryId,TableQuery[CountryTable])(_.id)

  def * = (id.?,filmId,countryId) <> (FilmToCountry.apply _ tupled,FilmToCountry.unapply)
}
object FilmTable{
  val table = TableQuery[FilmTable]
}
class FilmRepository(db:Database) {
  val filmTableQuery = TableQuery[FilmTable]

  def create(film:Film) : Future[Film] = db.run(filmTableQuery returning filmTableQuery += film)

  def update(film: Film) : Future[Int] =
    db.run(filmTableQuery.filter(_.id === film.id).update(film)) //Int у фьючері - кількість записів, які були змінені

  def delete (filmId: Long) =
    db.run(filmTableQuery.filter(_.id===filmId).delete)

  def getById(filmId:Long) : Future[Option[Film]] =
    db.run(filmTableQuery.filter(_.id === filmId).result.headOption)

}
object FilmToGenreTable{
  val table = TableQuery[FilmToGenreTable]
}
class FilmToGenreRepository(db:Database) {
  val filmTableQuery = TableQuery[FilmTable]

  def create(film:Film) : Future[Film] = db.run(filmTableQuery returning filmTableQuery += film)

  def update(film: Film) : Future[Int] =
    db.run(filmTableQuery.filter(_.id === film.id).update(film)) //Int у фьючері - кількість записів, які були змінені

  def delete (filmId: Long) =
    db.run(filmTableQuery.filter(_.id===filmId).delete)

  def getById(filmId:Long) : Future[Option[Film]] =
    db.run(filmTableQuery.filter(_.id === filmId).result.headOption)

}