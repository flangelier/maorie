package quebec.lange.maorie

import java.util.Date

import quebec.lange.maorie.hashers.{Hasher, MD5Hasher}
import quebec.lange.maorie.storages.Storage

/**
 * Created by flangelier on 2014-06-08.
 */
object  Launcher {
  def main (args: Array[String]) {

    val password = new PasswordRecord {
      val id = Some(1)
      val password = Some(new StringByte("String"))
      val salt = None
      val numberOfIteration = 2
      val hashingFunctionId = None
      val hasToBeChanged = false
      val lastChange = Some(new Date())
    }

    val sto :Storage = new Storage {
      override def save(passwordRecord: PasswordRecord): Boolean  = { false }
      override def get(id: Option[Any]): Option[PasswordRecord] = Some(id match {
        case Some(id: Int) => password
        case Some(id) => throw new NoSuchElementException
        case None => throw new NotImplementedError
      })
      override def getLatestHashingFunctionId: Int = 1
    }

    val h = new Handler(sto)
    val rawPass = new StringByte("allo12")  //e427a0c7dbe46de89ba2fa6cfdaea536
  // f7473c91e9d57084c639bf5f75a94397
    h.run(Some(1), rawPass)

    //println(r)
  }
}
