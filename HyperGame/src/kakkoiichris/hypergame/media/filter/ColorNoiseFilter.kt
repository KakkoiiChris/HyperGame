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
import kotlin.random.Random

/**
 * A dynamic [Filter] which applies a colored noise to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Jan 4, 2016, 10:11:24 PM
 */
class ColorNoiseFilter(intensity: Double) : Filter {
    var intensity: Double = 0.0
        set(value) {
            field = value.clamp(0.0, 1.0)
        }

    init {
        this.intensity = intensity
    }

    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            var op = ColorOp.of(pixels[i])

            op *= Random.nextDouble().clamp(1.0 - intensity, 1.0)

            pixels[i] = op.value
        }
    }
}