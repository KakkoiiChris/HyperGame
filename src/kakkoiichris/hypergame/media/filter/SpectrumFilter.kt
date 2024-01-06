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

import kakkoiichris.hypergame.media.argbF
import kakkoiichris.hypergame.util.Time
import java.awt.Color

/**
 * A dynamic [AdjustFilter] whose values cycle through the rainbow.
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:25:07 AM
 */
class SpectrumFilter(private val speed: Double) : DynamicFilter {
    private var hue = 0.0

    override fun update(time: Time) {
        hue += time.delta * speed

        hue %= 360
    }

    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val (_, r, g, b) = Color.HSBtoRGB(hue.toFloat(), 1F, 1F).argbF

        val adjustFilter = AdjustFilter(r, g, b)

        adjustFilter.apply(width, height, pixels)
    }
}