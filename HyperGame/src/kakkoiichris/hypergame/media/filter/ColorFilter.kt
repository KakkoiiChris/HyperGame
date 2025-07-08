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

/**
 * A [Filter] which adjusts the percentage of all three RGB channels in
 * the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 3:59:04 AM
 */
open class ColorFilter(var r: Double, var g: Double, var b: Double) : Filter {
    /**
     * Sets the RGB channel percentages based on the specified integer color
     * value.
     *
     * @param color The color to imitate
     */
    fun set(color: Int) {
        val (_, r, g, b) = ColorOp.of(color)

        this.r = r
        this.g = g
        this.b = b
    }

    /**
     * Sets the RGB channel percentages with the specified percentages.
     *
     * @param r The percentage of red
     * @param g The percentage of green
     * @param b The percentage of blue
     */
    fun set(r: Double, g: Double, b: Double) {
        this.r = r
        this.g = g
        this.b = b
    }

    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val op = ColorOp.of(pixels[i])

            op.red *= r
            op.green *= g
            op.blue *= b

            pixels[i] = op.value
        }
    }

    companion object {
        /**
         * Creates a new [ColorFilter] with its percentages based off of an
         * integer color value.
         *
         * @param color The color to imitate
         */
        fun of(color: Int): ColorFilter {
            val (_, r, g, b) = ColorOp.of(color)

            return ColorFilter(r, g, b)
        }
    }
}