package main.scala.com.ensor_soleil.maorie

/**
 * Created by flangelier on 2014-06-15.
 */
class StringByte {
  def this (value: Array[Byte]) = {
    this()
    this.set = value
  }

  def this (value: String) = {
    this()
    this.set = value
  }

  private var _valueAsByteArray: Array[Byte] = Array.empty[Byte]
  private var _valueAsString: String = ""

  def getAsString: String = _valueAsString
  def getAsByteArray: Array[Byte] = _valueAsByteArray
  def getAsStringOfHex: String = _valueAsByteArray.map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}

  def set_= (value: Array[Byte]) : Unit = {
    _valueAsString = new String(value)
    _valueAsByteArray = value
  }

  def set_= (value: String) : Unit = {
    _valueAsByteArray = value.getBytes
    _valueAsString = value
  }

  // Scala want a "basic" setter for my setters to work, let's give a dummy one!
  def set(implicit no: Nothing) = _valueAsString
}
