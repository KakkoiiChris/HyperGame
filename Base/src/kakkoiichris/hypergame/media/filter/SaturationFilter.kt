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
            val op = ColorOp.of(pixels[i])

            if (saturation > 0) {
                val rLim = if (op.red >= 0.5) 1.0 else 0.0
                val gLim = if (op.green >= 0.5) 1.0 else 0.0
                val bLim = if (op.blue >= 0.5) 1.0 else 0.0

                val rDiff = rLim - op.red
                val gDiff = gLim - op.green
                val bDiff = bLim - op.blue

                op.red += rDiff * saturation
                op.green += gDiff * saturation
                op.blue += bDiff * saturation
            }
            else {
                val avg = avg(op.red, op.green, op.blue)

                val rDiff = avg - op.red
                val gDiff = avg - op.green
                val bDiff = avg - op.blue

                op.red += rDiff * -saturation
                op.green += gDiff * -saturation
                op.blue += bDiff * -saturation
            }

            pixels[i] = op.value
        }
    }
}