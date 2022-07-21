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
import kakkoiichris.hypergame.util.math.avg

/**
 * A [Filter] which replaces sequential shades of gray on the
 * [Sprite] with the specified colors.
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:53:27 AM
 */
class ColorMapFilter(vararg val values: Int) : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argb
            
            if (argb.alpha != 0xFF) {
                continue
            }
            
            val v = ((avg(argb.red, argb.green, argb.blue) + 1) / (256 / (values.size - 1))).toInt()
            
            pixels[i] = values[v]
        }
    }
}