/**************************************************************
 * ██  ██ ▄▄ ▄▄ ▄▄▄▄  ▄▄▄▄▄ ▄▄▄▄   ▄████   ▄▄▄  ▄▄   ▄▄ ▄▄▄▄▄ *
 * ██████ ▀███▀ ██▄█▀ ██▄▄  ██▄█▄ ██  ▄▄▄ ██▀██ ██▀▄▀██ ██▄▄  *
 * ██  ██   █   ██    ██▄▄▄ ██ ██  ▀███▀  ██▀██ ██   ██ ██▄▄▄ *
 *             Kotlin 2D Game Development Library             *
 *              Copyright (C) 2021, KakkoiiChris              *
 **************************************************************/
package kakkoiichris.hypergame.media.filter

import kakkoiichris.hypergame.media.ColorOp
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which adjusts the percentage of the alpha channel in the
 * [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:34:09 AM
 */
class AlphaFilter(alpha: Double) : Filter {
    var alpha: Double = 0.0
        set(value) {
            field = value.clamp(0.0, 1.0)
        }

    init {
        this.alpha = alpha
    }

    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val op = ColorOp.of(pixels[i])

            op.alpha *= alpha

            pixels[i] = op.value
        }
    }
}