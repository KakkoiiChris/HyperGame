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

import kakkoiichris.hypergame.media.Sprite

/**
 * A dynamic [Filter] which applies a trippy, repetitive effect to the
 * [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 4:13:30 AM
 */
class AcidFilter : Filter {
    private var n = 0L
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            pixels[i] += pixels[((i + n) % pixels.size).toInt()] + i
        }
        
        n += (width - 1).toLong()
    }
}