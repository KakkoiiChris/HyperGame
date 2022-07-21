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
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which applies an desaturation or oversaturation effect to
 * the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:56:57 AM
 */
class SaturationFilter(saturation: Float) : Filter {
    var saturation: Float = 0F
        set(value) {
            field = value.clamp(-1F, 1F)
        }
    
    init {
        this.saturation = saturation
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        if (saturation == 0f)
            return
        
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            if (saturation > 0) {
                val rLim = if (argb.red >= 0.5f) 1f else 0f
                val gLim = if (argb.green >= 0.5f) 1f else 0f
                val bLim = if (argb.blue >= 0.5f) 1f else 0f
                
                val rDiff = rLim - argb.red
                val gDiff = gLim - argb.green
                val bDiff = bLim - argb.blue
                
                argb.red += rDiff * saturation
                argb.green += gDiff * saturation
                argb.blue += bDiff * saturation
            }
            else {
                val avg = avg(argb.red, argb.green, argb.blue).toFloat()
                
                val rDiff = avg - argb.red
                val gDiff = avg - argb.green
                val bDiff = avg - argb.blue
                
                argb.red += rDiff * -saturation
                argb.green += gDiff * -saturation
                argb.blue += bDiff * -saturation
            }
            
            pixels[i] = argb.toColor()
        }
    }
}