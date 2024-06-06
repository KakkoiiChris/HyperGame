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

import kakkoiichris.hypergame.media.ColorOp
import kakkoiichris.hypergame.media.Sprite
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
            var op = ColorOp.of(pixels[i])

            val values = RGBtoHSB((op.r * 255).toInt(), (op.g * 255).toInt(), (op.b * 255).toInt(), null)

            op = ColorOp.of(HSBtoRGB((values[0] + hue).toFloat(), values[1], values[2]))

            pixels[i] = op.value
        }
    }
}