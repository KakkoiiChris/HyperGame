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
import kakkoiichris.hypergame.util.math.clamp
import kotlin.random.Random

/**
 * A dynamic [Filter] which applies a colorless noise to the
 * [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Jan 4, 2016, 10:15:33 PM
 */
class GrayNoiseFilter(intensity: Double) : Filter {
    var intensity: Double = 0.0
        set(value) {
            field = value.clamp(0.0, 1.0)
        }
    
    init {
        this.intensity = intensity
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            val v = Random.nextDouble().clamp(1.0 - intensity, 1.0)
            
            argb.red *= v
            argb.green *= v
            argb.blue *= v
            
            pixels[i] = argb.toColor()
        }
    }
}