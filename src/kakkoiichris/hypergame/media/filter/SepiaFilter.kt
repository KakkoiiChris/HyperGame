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
import kotlin.math.min

/**
 * A [Filter] which applies a sepia-tone effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:13:01 AM
 */
class SepiaFilter : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            val or = min(argb.red * 0.393f + argb.green * 0.769f + argb.blue * 0.189f, 1f)
            val og = min(argb.red * 0.349f + argb.green * 0.686f + argb.blue * 0.168f, 1f)
            val ob = min(argb.red * 0.272f + argb.green * 0.534f + argb.blue * 0.131f, 1f)
            
            pixels[i] = floatArrayOf(argb.alpha, or, og, ob).toColor()
        }
    }
}