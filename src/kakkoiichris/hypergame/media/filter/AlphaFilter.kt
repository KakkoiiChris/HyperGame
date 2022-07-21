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
import kakkoiichris.hypergame.media.argbF
import kakkoiichris.hypergame.media.toColor
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which adjusts the percentage of the alpha channel in the
 * [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:34:09 AM
 */
class AlphaFilter(alpha: Float) : Filter {
    var alpha: Float = 0F
        set(value) {
            field = value.clamp(0F, 1F)
        }
    
    init {
        this.alpha = alpha
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            argb[0] *= alpha
            
            pixels[i] = argb.toColor()
        }
    }
}