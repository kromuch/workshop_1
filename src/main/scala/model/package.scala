import slick.ast.BaseTypedType
import slick.jdbc.JdbcType
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration._

package object model {
  implicit val durationToLongMapper: JdbcType[Duration] with BaseTypedType[Duration] = MappedColumnType.base[Duration, Long](_.toSeconds, _.seconds)
}
