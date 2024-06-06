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
import kakkoiichris.hypergame.util.Time
import java.awt.Color

/**
 * A dynamic [ColorFilter] whose values cycle through the rainbow.
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 6:25:07 AM
 */
class SpectrumFilter(private val speed: Double) : ColorFilter(1.0, 0.0, 0.0), DynamicFilter {
    private var hue = 0F

    override fun update(time: Time) {
        hue += (time.delta * speed).toFloat()

        hue %= 360

        val (_, r, g, b) = ColorOp.of(Color.HSBtoRGB(hue, 1F, 1F))

        this.r = r
        this.g = g
        this.b = b
    }
}