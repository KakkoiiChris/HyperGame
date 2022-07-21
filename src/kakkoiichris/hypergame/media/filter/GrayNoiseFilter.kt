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
import kakkoiichris.hypergame.util.math.randomFloat

/**
 * A dynamic [Filter] which applies a colorless noise to the
 * [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Jan 4, 2016, 10:15:33 PM
 */
class GrayNoiseFilter(intensity: Float) : Filter {
    var intensity: Float = 0F
        set(value) {
            field = value.clamp(0F, 1F)
        }
    
    init {
        this.intensity = intensity
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            val v = randomFloat().clamp(1F - intensity, 1F)
            
            argb.red *= v
            argb.green *= v
            argb.blue *= v
            
            pixels[i] = argb.toColor()
        }
    }
}