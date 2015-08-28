package quebec.lange.maorie.storages

import quebec.lange.maorie.exception.PresaveException
import quebec.lange.maorie.{Config, PasswordRecord}
import quebec.lange.maorie.hashers._

/**
 * Created by flangelier on 2014-08-13.
 */
abstract class Storage {
  //TODO: V3? Should be able to save the password nor the config in the storage... maybe using interface?
  //TODO: Throw exception when we cannont reach the storage
  final def saveToStorage(passwordRecord: PasswordRecord): Boolean = {
    //TODO: refaire le saving, je sais pas si je devrait ou non utiliser des exception ou non....
    if (!presave(passwordRecord)) throw new PresaveException("Error while presaving.")
    if (!save(passwordRecord)) throw new PresaveException("Error while presaving.")
    if (!postsave(passwordRecord)) throw new PresaveException("Error while presaving.")
    true
  }
  def get(id: Option[Any]): Option[PasswordRecord]
  def getLatestHashingFunctionId: Int

  def getHashingFunction(hashingFunctionId: Option[Int]): Hasher = {
    (hashingFunctionId match {
      case Some(id: Int) => Class.forName("com.ensor_soleil.maorie.hashers.MD5Hasher")
      case None => Class.forName(Config.get("default.hasher"))
    }).newInstance.asInstanceOf[Hasher]
  }
  
  protected def presave(passwordRecord: PasswordRecord): Boolean = {
    //TODO: Code the presave storage (validation, reaching db,  ...
    true
  }
  protected def save(passwordRecord: PasswordRecord): Boolean
  protected def postsave(passwordRecord: PasswordRecord): Boolean = {
    true
  }
}
