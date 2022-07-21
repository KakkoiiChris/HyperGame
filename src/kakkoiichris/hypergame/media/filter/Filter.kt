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

/**
 * A [FunctionalInterface] which is used to filter a [Sprite], and
 * defines the rule(s) for determining the values for each pixel.
 *
 * @author Christian Bryce Alexander
 * @since Dec 14, 2015, 12:29:10 AM
 */
interface Filter {
    /**
     * Applies this [Filter]'s algorithm with the specified values.
     *
     * @param width
     * The width of the filtered image
     * @param height
     * The height of the filtered image
     * @param pixels
     * The pixel data of the [Sprite] to be filtered
     */
    fun apply(width: Int, height: Int, pixels: IntArray)
}