package quebec.lange.maorie

/**
 * Created by flangelier on 2014-08-17.
 */
object Config {
  //TODO read config from a config json file(V2) or DB(V3)
  protected lazy val config = new DefaultConfig
  def get(key: String): String = {
    config.get(key)
  }
}

class DefaultConfig {
  def get(key: String): String = {
    key match {
      case "default.hasher" => "quebec.lange.maorie.hashers.MD5Hasher"
      case "salt.ttl.ms" => "2592000000" // 1*1000*60*60*24*30
      case otherKey => throw new ClassNotFoundException("Key not found")
    }
  }
}
