package quebec.lange.maorie.hashers

import java.security.MessageDigest

import quebec.lange.maorie.{StringByte, PasswordRecord}

/**
 * Created by flangelier on 2014-06-08.
 */
class MD5Hasher extends Hasher {

  def hashingFunction(arrayToHash : Array[Byte]) : Array[Byte] = {

  //TODO: To redo, this implementation is just for testing purpose!
  //TODO: Not sure at 100% that this is the correct way to do it, but it's working... Worst case scenario, someone trying to generate with their implementation to hard won't be able to find the correct hash...
    var thedigest : Array[Byte] = arrayToHash
    val md: MessageDigest = MessageDigest.getInstance("MD5")
    md.update(arrayToHash)
    md.digest()

    /*val md2 : MessageDigest = MessageDigest.getInstance("MD5")
    md2.reset
    md2.update(thedigest)
    thedigest = md2.digest()

    thedigest*/
  }
}
