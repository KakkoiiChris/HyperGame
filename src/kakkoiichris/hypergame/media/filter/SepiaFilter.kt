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
            val argb = pixels[i].argbF
            
            val or = min(argb.red * 0.393 + argb.green * 0.769 + argb.blue * 0.189, 1.0)
            val og = min(argb.red * 0.349 + argb.green * 0.686 + argb.blue * 0.168, 1.0)
            val ob = min(argb.red * 0.272 + argb.green * 0.534 + argb.blue * 0.131, 1.0)
            
            pixels[i] = doubleArrayOf(argb.alpha, or, og, ob).toColor()
        }
    }
}