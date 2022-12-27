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

import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.media.argbF
import kakkoiichris.hypergame.media.toColor
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which adjusts the percentage of the alpha channel in the
 * [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 5:34:09 AM
 */
class AlphaFilter(alpha: Double) : Filter {
    var alpha: Double = 0.0
        set(value) {
            field = value.clamp(0.0, 1.0)
        }
    
    init {
        this.alpha = alpha
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            argb[0] *= alpha
            
            pixels[i] = argb.toColor()
        }
    }
}