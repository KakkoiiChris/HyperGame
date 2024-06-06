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
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which changes the contrast of the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since May 18, 2016, 1:47:11 PM
 */
class ContrastFilter(contrast: Double) : Filter {
    var contrast: Double = 0.0
        set(value) {
            field = value.clamp(0.0, 1.0)
        }

    init {
        this.contrast = contrast
    }

    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val op = ColorOp.of(pixels[i])

            op.map { (contrast * (it - 0.5) + 0.5).clamp(0.0, 1.0) }

            pixels[i] = op.value
        }
    }
}