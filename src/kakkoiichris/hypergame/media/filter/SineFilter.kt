/*  _     _       _       _   ____        _
 * | |   |_|     | |     | | |  _ \      |_|
 * | |    _  ___ | |__  _| |_| | | | ____ _ _   _  ___
 * | |   | |/ _ \|  _ \|_   _| | | |/ ___| | \ / |/ _ \
 * | |___| | |_| | | | | | | | |_| | |   | |\ V /|  ___|
 * |_____|_|\__  |_| |_| |_| |____/|_|   |_| \_/  \___|
 *  _____   ___| |  ___________________________________
 * |_____| |____/  |_________JAVA_GAME_LIBRARY_________|
 *
 * COPYRIGHT (C) 2015, CHRISTIAN BRYCE ALEXANDER
 */
package kakkoiichris.hypergame.media.filter

import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.wrap
import kotlin.math.sin

/**
 * A dynamic [Filter] which applies a vertically sinuous effect to the
 * [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:21:23 AM
 */
class SineFilter(private val period: Double, private val magnitude: Double) : DynamicFilter {
    private var t = 0.0
    
    override fun update(time: Time) {
        t += time.delta
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val original = pixels.clone()
        
        for (y in 0 until pixels.size / width) {
            for (x in 0 until width) {
                val s = (sin((y + t) / period) * magnitude).toInt()
                
                pixels[x + y * width] = original[((x + s).wrap(0, width - 1) + y * width) % pixels.size]
            }
        }
    }
}