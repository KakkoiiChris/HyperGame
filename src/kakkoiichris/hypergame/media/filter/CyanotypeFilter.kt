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

import kakkoiichris.hypergame.media.GrayscaleMode

/**
 * A [Filter] which applies a cyanotype effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since May 18, 2016, 1:30:03 PM
 */
class CyanotypeFilter(mode: GrayscaleMode) : GrayScaleFilter(mode) {
    private val cyanotype = ColorFilter.of(-0xB17511)
    private val bright = BrightnessFilter(0.1)
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        super.apply(width, height, pixels)
        cyanotype.apply(width, height, pixels)
        bright.apply(width, height, pixels)
    }
}