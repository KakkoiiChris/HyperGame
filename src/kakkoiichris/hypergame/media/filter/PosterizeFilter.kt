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

/**
 * A [Filter] which applies a posterized effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:49:43 AM
 */
class PosterizeFilter(levels: Int) : Filter {
    var levels: Int = 0
        set(value) {
            field = (value - 1).clamp(1, 0xFF)
        }

    init {
        this.levels = levels
    }

    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val div = 0xFF / levels

        for (i in pixels.indices) {
            val (a, r, g, b) = ColorOp.of(pixels[i])

            var ir = (r * 255).toInt()
            var ig = (g * 255).toInt()
            var ib = (b * 255).toInt()

            ir /= div
            ir *= div

            ig /= div
            ig *= div

            ib /= div
            ib *= div

            val resOp = ColorOp(a, ir / 255.0, ig / 255.0, ib / 255.0)

            pixels[i] = resOp.value
        }
    }
}