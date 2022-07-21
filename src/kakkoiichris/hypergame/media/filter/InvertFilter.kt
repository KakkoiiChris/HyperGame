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

import kakkoiichris.hypergame.media.argb
import kakkoiichris.hypergame.media.toColor

/**
 * A [Filter] which inverts all three RGB channels in the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:31:51 AM
 */
class InvertFilter : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argb
            
            for (c in argb.indices.drop(1)) {
                argb[c] = 0xFF - argb[c]
            }
            
            pixels[i] = argb.toColor()
        }
    }
}