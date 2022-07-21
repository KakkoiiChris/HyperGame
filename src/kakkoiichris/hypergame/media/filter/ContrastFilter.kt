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
import kakkoiichris.hypergame.media.toColor
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which changes the contrast of the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since May 18, 2016, 1:47:11 PM
 */
class ContrastFilter(contrast: Float) : Filter {
    var contrast: Float = 0F
        set(value) {
            field = value.clamp(0F, 1F)
        }
    
    init {
        this.contrast = contrast
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            for (c in argb.indices.drop(1)) {
                argb[c] = (contrast * (argb[c] - 0.5f) + 0.5f).clamp(0F, 1F)
            }
            
            pixels[i] = argb.toColor()
        }
    }
}