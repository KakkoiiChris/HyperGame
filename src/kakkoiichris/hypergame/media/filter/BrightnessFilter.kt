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
 * A [Filter] which applies a brightness effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 4:18:47 AM
 */
class BrightnessFilter(brightness: Float) : Filter {
    var brightness: Float = 0F
        set(value) {
            field = value.clamp(-1F, 1F)
        }
    
    init {
        this.brightness = brightness
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argb
            
            if (brightness > 0) {
                argb.red += ((255 - argb.red) * brightness).toInt()
                argb.green += ((255 - argb.green) * brightness).toInt()
                argb.blue += ((255 - argb.blue) * brightness).toInt()
            }
            else {
                argb.red -= (argb.red * -brightness).toInt()
                argb.green -= (argb.green * -brightness).toInt()
                argb.blue -= (argb.blue * -brightness).toInt()
            }
            
            pixels[i] = argb.toColor()
        }
    }
}