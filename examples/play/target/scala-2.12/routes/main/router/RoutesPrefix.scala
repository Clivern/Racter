
// @GENERATOR:play-routes-compiler
// @SOURCE:/var/www/racter/examples/play/conf/routes
// @DATE:Wed Oct 18 15:47:25 EET 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
