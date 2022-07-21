/*  _     _       _       _   ____        _
 * | |   |_|     | |     | | |  _ \      |_|
 * | |    _  ___ | |__  _| |_| | | | ____ _ _   _  ___
 * | |   | |/ _ \|  _ \|_   _| | | |/ ___| | \ / |/ _ \
 * | |___| | |_| | | | | | | | |_| | |   | |\ V /|  ___|
 * |_____|_|\__  |_| |_| |_| |____/|_|   |_| \_/  \___|
 *  _____   ___| |  ___________________________________
 * |_____| |____/  |_________JAVA_GAME_LIBRARY_________|
 *
 * COPYRIGHT (C) 2015, CHRISTIAN BRYCE ALEXANDER
 */
package kakkoiichris.hypergame.media.filter

import kakkoiichris.hypergame.media.argbF
import kakkoiichris.hypergame.media.blue
import kakkoiichris.hypergame.media.green
import kakkoiichris.hypergame.media.red
import java.awt.Color

/**
 * A dynamic [AdjustFilter] whose values cycle through the rainbow.
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:25:07 AM
 */
class SpectrumFilter(private val dh: Float) : AdjustFilter(1F, 0F, 0F) {
    private var hue = 0F
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val argb = Color.HSBtoRGB(hue, 1F, 1F).argbF
        
        red = argb.red
        green = argb.green
        blue = argb.blue
        
        super.apply(width, height, pixels)
        
        hue += dh
    }
}