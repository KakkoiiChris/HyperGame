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

/**
 * A [Filter] which applies a posterized effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:49:43 AM
 */
class PosterizeFilter(levels: Int) : Filter {
    var levels: Int = 0
        set(value) {
            field = (value - 1).clamp(1, 0xFF)
        }
    
    init {
        this.levels = levels
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argb
            
            argb.red /= 0xFF / levels
            argb.red *= 0xFF / levels
            
            argb.green /= 0xFF / levels
            argb.green *= 0xFF / levels
            
            argb.blue /= 0xFF / levels
            argb.blue *= 0xFF / levels
            
            pixels[i] = argb.toColor()
        }
    }
}