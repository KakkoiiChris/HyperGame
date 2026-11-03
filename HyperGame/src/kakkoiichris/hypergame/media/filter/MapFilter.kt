/**************************************************************
 * ██  ██ ▄▄ ▄▄ ▄▄▄▄  ▄▄▄▄▄ ▄▄▄▄   ▄████   ▄▄▄  ▄▄   ▄▄ ▄▄▄▄▄ *
 * ██████ ▀███▀ ██▄█▀ ██▄▄  ██▄█▄ ██  ▄▄▄ ██▀██ ██▀▄▀██ ██▄▄  *
 * ██  ██   █   ██    ██▄▄▄ ██ ██  ▀███▀  ██▀██ ██   ██ ██▄▄▄ *
 *             Kotlin 2D Game Development Library             *
 *              Copyright (C) 2021, KakkoiiChris              *
 **************************************************************/
package kakkoiichris.hypergame.media.filter

/**
 * A [Filter] which replaces one set of colors in a [Sprite] with
 * another set of colors.
 *
 * @author Christian Bryce Alexander
 * @since Jan 6, 2016, 6:08:49 PM
 */
class MapFilter(var map: Map<Int, Int>) : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            pixels[i] = map[pixels[i]] ?: pixels[i]
        }
    }
}