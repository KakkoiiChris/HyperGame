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

import kakkoiichris.hypergame.media.*

/**
 * A [Filter] which adjusts the percentage of all three RGB channels in
 * the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 3:59:04 AM
 */
open class AdjustFilter(var red: Float, var green: Float, var blue: Float) : Filter {
    /**
     * Creates a new [AdjustFilter] with its percentages based off of an
     * integer color value.
     *
     * @param color The color to imitate
     */
    constructor(color: Int) : this(color.redF, color.greenF, color.blueF)
    
    /**
     * Sets the RGB channel percentages based on the specified integer color
     * value.
     *
     * @param color The color to imitate
     */
    fun set(color: Int) {
        red = color.redF
        green = color.greenF
        blue = color.blueF
    }
    
    /**
     * Sets the RGB channel percentages with the specified percentages.
     *
     * @param r The percentage of red
     * @param g The percentage of green
     * @param b The percentage of blue
     */
    fun set(red: Float, green: Float, blue: Float) {
        this.red = red
        this.green = green
        this.blue = blue
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            val argb = pixels[i].argbF
            
            argb[1] *= red
            argb[2] *= green
            argb[3] *= blue
            
            pixels[i] = argb.toColor()
        }
    }
}