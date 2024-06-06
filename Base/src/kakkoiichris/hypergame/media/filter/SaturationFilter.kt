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
                val rLim = if (op.r >= 0.5) 1.0 else 0.0
                val gLim = if (op.g >= 0.5) 1.0 else 0.0
                val bLim = if (op.b >= 0.5) 1.0 else 0.0

                val rDiff = rLim - op.r
                val gDiff = gLim - op.g
                val bDiff = bLim - op.b

                op.r += rDiff * saturation
                op.g += gDiff * saturation
                op.b += bDiff * saturation
            }
            else {
                val avg = avg(op.r, op.g, op.b)

                val rDiff = avg - op.r
                val gDiff = avg - op.g
                val bDiff = avg - op.b

                op.r += rDiff * -saturation
                op.g += gDiff * -saturation
                op.b += bDiff * -saturation
            }

            pixels[i] = op.value
        }
    }
}