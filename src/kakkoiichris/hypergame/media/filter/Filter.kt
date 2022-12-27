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