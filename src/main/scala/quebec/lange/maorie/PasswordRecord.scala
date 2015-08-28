package quebec.lange.maorie

import java.util.Date

import scala.util.Random

/**
 * Created by flangelier on 2014-06-15.
 */

object PasswordRecord {
  def empty: PasswordRecord = new PasswordRecord {
    override val hashingFunctionId: Option[Int] = None
    override val numberOfIteration: Int = 1000
    override val salt: Option[StringByte] = Some({
      val byteArray = new Array[Byte](16)
      new Random().nextBytes(byteArray)
      new StringByte(byteArray)
    })
    override val hasToBeChanged: Boolean = true
    override val password: Option[StringByte] = None
    override val id: Option[Int] = None
    override val lastChange: Option[Date] = None
  }
}
trait PasswordRecord {
  val id: Option[Int]
  val password: Option[StringByte]
  val salt: Option[StringByte]
  val numberOfIteration: Int
  val hashingFunctionId: Option[Int]
  val hasToBeChanged: Boolean
  val lastChange: Option[Date]
}
class password(val pass: Option[StringByte])
case class newPassword(passq: Option[StringByte]) extends password(passq)
case class wrongPassword(passq: Option[StringByte]) extends password(passq)
case class notInStoragePassword(passq: Option[StringByte]) extends password(passq)
