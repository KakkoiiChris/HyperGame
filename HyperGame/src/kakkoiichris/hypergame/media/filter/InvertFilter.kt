/**************************************************************
 * ██  ██ ▄▄ ▄▄ ▄▄▄▄  ▄▄▄▄▄ ▄▄▄▄   ▄████   ▄▄▄  ▄▄   ▄▄ ▄▄▄▄▄ *
 * ██████ ▀███▀ ██▄█▀ ██▄▄  ██▄█▄ ██  ▄▄▄ ██▀██ ██▀▄▀██ ██▄▄  *
 * ██  ██   █   ██    ██▄▄▄ ██ ██  ▀███▀  ██▀██ ██   ██ ██▄▄▄ *
 *             Kotlin 2D Game Development Library             *
 *              Copyright (C) 2021, KakkoiiChris              *
 **************************************************************/
package kakkoiichris.hypergame.media.filter

import kakkoiichris.hypergame.media.ColorOp

/**
 * A [Filter] which inverts all three RGB channels in the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:31:51 AM
 */
class InvertFilter : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val op = ColorOp.of(pixels[i])

            op.map { 1.0 - it }

            pixels[i] = op.value
        }
    }
}