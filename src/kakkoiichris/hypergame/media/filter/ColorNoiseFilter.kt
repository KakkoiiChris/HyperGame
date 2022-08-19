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
import kotlin.random.Random

/**
 * A dynamic [Filter] which applies a colored noise to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Jan 4, 2016, 10:11:24 PM
 */
class ColorNoiseFilter(intensity: Float) : Filter {
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
            
            argb.red *= Random.nextFloat().clamp(1F - intensity, 1F)
            argb.green *= Random.nextFloat().clamp(1F - intensity, 1F)
            argb.blue *= Random.nextFloat().clamp(1F - intensity, 1F)
            
            pixels[i] = argb.toColor()
        }
    }
}