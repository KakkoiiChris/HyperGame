/***************************************************************************
 *   ___ ___                                ________                       *
 *  /   |   \ ___.__.______   ___________  /  _____/_____    _____   ____  *
 * /    ~    <   |  |\____ \_/ __ \_  __ \/   \  ___\__  \  /     \_/ __ \ *
 * \    Y    /\___  ||  |_> >  ___/|  | \/\    \_\  \/ __ \|  Y Y  \  ___/ *
 *  \___|_  / / ____||   __/ \___  >__|    \______  (____  /__|_|  /\___  >*
 *        \/  \/     |__|        \/               \/     \/      \/     \/ *
 *                    Kotlin 2D Game Development Library                   *
 *                     Copyright (C) 2021, KakkoiiChris                    *
 ***************************************************************************/
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
class SaturationFilter(saturation: Double) : Filter {
    var saturation: Double = 0.0
        set(value) {
            field = value.clamp(-1.0, 1.0)
        }
    
    init {
        this.saturation = saturation
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        if (saturation == 0.0) return
        
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            if (saturation > 0) {
                val rLim = if (argb.red >= 0.5) 1.0 else 0.0
                val gLim = if (argb.green >= 0.5) 1.0 else 0.0
                val bLim = if (argb.blue >= 0.5) 1.0 else 0.0
                
                val rDiff = rLim - argb.red
                val gDiff = gLim - argb.green
                val bDiff = bLim - argb.blue
                
                argb.red += rDiff * saturation
                argb.green += gDiff * saturation
                argb.blue += bDiff * saturation
            }
            else {
                val avg = avg(argb.red, argb.green, argb.blue)
                
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