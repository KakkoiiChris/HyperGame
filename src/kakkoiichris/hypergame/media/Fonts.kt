package kakkoiichris.hypergame.media

import java.awt.Font
import java.awt.GraphicsEnvironment

object Fonts {
    val extensions = arrayOf("ttf", "otf")
    
    fun register(path: String): String {
        val font = Font.createFont(
            Font.TRUETYPE_FONT,
            Fonts.javaClass.getResourceAsStream(path)
        )
        
        GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .registerFont(font)
        
        return font.name
    }
}