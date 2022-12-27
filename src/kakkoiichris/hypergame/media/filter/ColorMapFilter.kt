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
import kakkoiichris.hypergame.util.math.avg

/**
 * A [Filter] which replaces sequential shades of gray on the
 * [Sprite] with the specified colors.
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:53:27 AM
 */
class ColorMapFilter(vararg val values: Int) : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argb
            
            if (argb.alpha != 0xFF) {
                continue
            }
            
            val v = ((avg(argb.red, argb.green, argb.blue) + 1) / (256 / (values.size - 1))).toInt()
            
            pixels[i] = values[v]
        }
    }
}