package kakkoiichris.hypergame.view

import java.awt.Image
import javax.imageio.ImageIO

data class Config(
    val width      : Int     = DEFAULT_WIDTH,
    val height     : Int     = DEFAULT_HEIGHT,
    val scale      : Int     = DEFAULT_SCALE,
    val isResizable: Boolean = DEFAULT_IS_RESIZABLE,
    val frameRate  : Double  = DEFAULT_FRAME_RATE,
    val title      : String  = DEFAULT_TITLE,
    val icon       : Image   = loadDefaultIcon(),
) {
    companion object {
        const val DEFAULT_WIDTH        = 640
        const val DEFAULT_HEIGHT       = 480
        const val DEFAULT_SCALE        = 1
        const val DEFAULT_IS_RESIZABLE = false
        const val DEFAULT_FRAME_RATE   = 60.0
        const val DEFAULT_TITLE        = "HyperGame"

        private fun loadDefaultIcon() =
            ImageIO.read(Window::class.java.getResourceAsStream("/kakkoiichris/hypergame/img/icon.png"))
    }
}