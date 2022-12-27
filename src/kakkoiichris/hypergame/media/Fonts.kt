/***************************************************************************
 *   ___ ___                                ________                       *
 *  /   |   \ ___.__.______   ___________  /  _____/_____    _____   ____  *
 * /    ~    <   |  |\____ \_/ __ \_  __ \/   \  ___\__  \  /     \_/ __ \ *
 * \    Y    /\___  ||  |_> >  ___/|  | \/\    \_\  \/ __ \|  Y Y  \  ___/ *
 *  \___|_  / / ____||   __/ \___  >__|    \______  (____  /__|_|  /\___  >*
 *        \/  \/     |__|        \/               \/     \/      \/     \/ *
 *                    Kotlin 2D Game Development Library                   *
 *                     Copyright (C) 2021, KakkoiiChris                    *
 ***************************************************************************/
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