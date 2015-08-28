package quebec.lange.maorie

import quebec.lange.maorie.hashers.{Hasher}
import quebec.lange.maorie.storages.Storage

/**
 * Created by flangelier on 2014-08-13.
 */
class Handler (storage: Storage) {
  //TODO: Add logging everywhere!!!
  def run(id: Option[Any], rawPassword: StringByte) = {
    val passwordRecord = storage.get(id) match {
      case Some(pr) => pr
      case None => PasswordRecord.empty //TODO porter ca ailleur pour pouvoir overrider les default values/config file
    }

    val hasher = storage.getHashingFunction(passwordRecord.hashingFunctionId)

    val hashedPasswordRecord: StringByte = hasher.runHashing(rawPassword, passwordRecord) match {
      case Some(hashedPasswordRecord) => {
        passwordRecord.password match {
          case Some(registedPassword) => {
            if (registedPassword.getAsByteArray.equals(hashedPasswordRecord.getAsByteArray))
            {
              //Good password
              if (hasToBeUpdateInStorage(passwordRecord)) {
                //TODO: Update the registered password to be up to date
                //TODO Change the salt and regenerate the hash after x times
              }
              true
            }
            false
          }
          case None => {
          //No password in DB
          //TODO: Update the registered password? We have to be sure that this is not due to a connection problem...
            false // We ll probably change it to true
          }
        }


        //Retourne le password hasher... verifier avec le passwordRecord se quon doit faire, valider password, overrider dans la bd, etc...
        /*if (hashedPasswordRecord.hasToBeChanged)
          storage.save(hashedPasswordRecord)*/
        hashedPasswordRecord
      }
      case None => null// Not expected... throw error?
    }

    val va2 = hashedPasswordRecord.getAsStringOfHex
val v4 = va2
    //val algorr : Hashing = new HashingMD5("password", password)

  }

  protected def hasToBeUpdateInStorage(record: PasswordRecord): Boolean = {
    //TODO: Check all field if we need to update the record in storage
    if (record.id == None) true
    if (record.password == None) true
    if (record.hashingFunctionId == None) true
    if (record.lastChange == None) true

    record.hasToBeChanged
  }

}
