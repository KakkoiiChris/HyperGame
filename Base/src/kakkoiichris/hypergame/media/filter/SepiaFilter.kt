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
import kotlin.math.min

/**
 * A [Filter] which applies a sepia-tone effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:13:01 AM
 */
class SepiaFilter : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val op = ColorOp.of(pixels[i])

            op.r = min(op.r * 0.393 + op.g * 0.769 + op.b * 0.189, 1.0)
            op.g = min(op.r * 0.349 + op.g * 0.686 + op.b * 0.168, 1.0)
            op.b = min(op.r * 0.272 + op.g * 0.534 + op.b * 0.131, 1.0)

            pixels[i] = op.value
        }
    }
}