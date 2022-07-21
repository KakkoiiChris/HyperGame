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

import kakkoiichris.hypergame.media.*
import kakkoiichris.hypergame.util.math.clamp
import java.awt.Color.HSBtoRGB
import java.awt.Color.RGBtoHSB

/**
 * A [Filter] which changes the hue of the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:44:48 AM
 */
class HueFilter(hue: Float) : Filter {
    var hue: Float = 0F
        set(value) {
            field = value.clamp(0F, 1F) * 360F
        }
    
    init {
        this.hue = hue
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argb
            
            val values = RGBtoHSB(argb.red, argb.green, argb.blue, null)
            
            pixels[i] = (argb.alpha shl 24) or HSBtoRGB(values[0] + hue, values[1], values[2])
        }
    }
}