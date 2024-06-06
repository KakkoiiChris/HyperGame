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
 * A [Filter] which applies a simple 3x3 box blur to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 9:12:14 PM
 */
class BlurFilter(var kernel: Kernel) : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val newPixels = IntArray(pixels.size)

        for (y in 0 until pixels.size / width) {
            for (x in 0 until width) {
                val resOp = ColorOp()

                for (ky in kernel.indices) {
                    for (kx in kernel.indices) {
                        val sx = (x + kx - kernel.half).clamp(0, width - 1)
                        val sy = (y + ky - kernel.half).clamp(0, height - 1)

                        val i = sx + sy * width

                        val srcOp = ColorOp.of(pixels[i])

                        resOp += srcOp * kernel[kx, ky]
                    }
                }

                resOp.clamp()

                newPixels[x + y * width] = resOp.value
            }
        }

        System.arraycopy(newPixels, 0, pixels, 0, pixels.size)
    }

    class Kernel(val size: Int, val values: DoubleArray) {
        val indices = 0..<size

        val half = size / 2

        operator fun get(x: Int, y: Int) =
            values[x + y * size]

        companion object {
            fun create(size: Int): Kernel {
                if (size % 2 == 0) {
                    return create(size + 1)
                }

                val value = 1.0 / (size * size)

                return Kernel(size, DoubleArray(size * size) { value })
            }
        }
    }
}