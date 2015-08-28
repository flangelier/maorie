package quebec.lange.maorie.hashers

import quebec.lange.maorie.{PasswordRecord, StringByte}

/**
 * Created by flangelier on 2014-06-15.
 */
//TODO: Option of passwordRecord setting a new one with default values
//TODO: Check if password null of empty, return error/ None
abstract class Hasher {

  def runHashing(rawPassword: StringByte, passwordRecord: PasswordRecord): Option[StringByte] = Some({
    passwordRecord.salt match {
      case Some(salt) => rec(rawPassword, salt, passwordRecord.numberOfIteration)
      case None => rec(rawPassword, passwordRecord.numberOfIteration)
    }
  })
  
  protected def hashingFunction(arrayToHash : Array[Byte]) : Array[Byte]
  //TODO: Change the salting function to be hold in the "salter" instead of the hasher
  protected def saltingFunction(password: StringByte, salt: StringByte) : Array[Byte] = Array.concat[Byte](password.getAsByteArray, salt.getAsByteArray)
  
  protected def rec(password : StringByte, salt : StringByte, nbLeft : Int) : StringByte = {
    if (nbLeft > 0) {
      password.set = hashingFunction(saltingFunction(password, salt))
      rec(password, salt, nbLeft - 1)
    }
    password
  }

  protected def rec(password : StringByte, nbLeft : Int) : StringByte = {
    if (nbLeft > 0) {
      password.set = hashingFunction(password.getAsByteArray)
      rec(password, nbLeft - 1)
    }
    password
  }
}
