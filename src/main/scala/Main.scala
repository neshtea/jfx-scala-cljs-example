import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.concurrent.Worker.State
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javax.swing.SwingUtilities
import netscape.javascript.JSObject

import scala.swing

/**
 * Created by marco on 19/10/15.
 */
object Main {
  def main(args: Array[String]): Unit = {
    SwingUtilities.invokeLater(new Runnable() {
      override def run() = initPanel()
    })
  }

  def initPanel() = {
    val frame = new scala.swing.Frame()
    val panel = new JFXPanel()
    frame.peer.add(panel)
    frame.visible = true
    frame.peer.setSize(800, 600)

    initView(panel)
  }

  def initView(p: JFXPanel) = {
    javafx.application.Platform.runLater(new Runnable() {
      override def run(): Unit = {
        val webView = new javafx.scene.web.WebView()
        val webEngine = webView.getEngine
        // this tries to find the file in src/main/resources
        val mainUrl = getClass.getResource("javafxtest.html")
        webEngine.load(mainUrl.toExternalForm)
        webEngine.getLoadWorker.stateProperty.addListener(new ChangeListener[State] {
          override def changed(p1: ObservableValue[_ <: State], p2: State, p3: State): Unit = {
            val namespace = "jfxtest.simple"
            webEngine.executeScript("goog.require(\"" + namespace + "\");")
            val jsjfx = webEngine.executeScript(namespace).asInstanceOf[JSObject]
            jsjfx.call("simple_app", Array({() => System.exit(0)}))
          }
        })

        p.setScene(new Scene(webView))
      }
    })
  }
}
