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

/**
 * A [Filter] which applies a brightness effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 4:18:47 AM
 */
class BrightnessFilter(brightness: Double) : Filter {
    var brightness: Double = 0.0
        set(value) {
            field = value.clamp(-1.0, 1.0)
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