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

import kakkoiichris.hypergame.media.*

/**
 * A [Filter] which applies a gray-scale effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:26:09 AM
 */
open class GrayScaleFilter(var mode: GrayscaleMode) : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val op = ColorOp.of(pixels[i])
            
            val v = mode(op.r, op.g, op.b)

            op.map { v }
            
            pixels[i] = op.value
        }
    }
}