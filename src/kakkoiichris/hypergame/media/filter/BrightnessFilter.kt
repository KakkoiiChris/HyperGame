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
            val op = ColorOp.of(pixels[i])
            
            if (brightness > 0) {
                op.r += ((255 - op.r) * brightness).toInt()
                op.g += ((255 - op.g) * brightness).toInt()
                op.b += ((255 - op.b) * brightness).toInt()
            }
            else {
                op.r -= (op.r * -brightness).toInt()
                op.g -= (op.g * -brightness).toInt()
                op.b -= (op.b * -brightness).toInt()
            }
            
            pixels[i] = op.value
        }
    }
}