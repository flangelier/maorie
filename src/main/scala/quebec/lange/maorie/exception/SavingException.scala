package quebec.lange.maorie.exception

/**
 * Created by flangelier on 2014-09-08.
 */
class SavingException extends Exception {
  def this(message: String) {this} /*{ super("An exception occur while saving." + message) }*/

}

case class PresaveException(message: String) extends SavingException(message)
case class PostsaveException(message: String) extends SavingException(message)
