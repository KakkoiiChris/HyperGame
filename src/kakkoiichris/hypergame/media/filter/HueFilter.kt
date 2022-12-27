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
import kakkoiichris.hypergame.util.math.clamp
import java.awt.Color.HSBtoRGB
import java.awt.Color.RGBtoHSB

/**
 * A [Filter] which changes the hue of the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:44:48 AM
 */
class HueFilter(hue: Double) : Filter {
    var hue: Double = 0.0
        set(value) {
            field = value.clamp(0.0, 1.0) * 360.0
        }
    
    init {
        this.hue = hue
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argb
            
            val values = RGBtoHSB(argb.red, argb.green, argb.blue, null)
            
            pixels[i] = (argb.alpha shl 24) or HSBtoRGB((values[0] + hue).toFloat(), values[1], values[2])
        }
    }
}