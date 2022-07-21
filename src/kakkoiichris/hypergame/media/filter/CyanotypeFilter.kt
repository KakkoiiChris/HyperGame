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

import kakkoiichris.hypergame.media.GrayscaleMode

/**
 * A [Filter] which applies a cyanotype effect to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since May 18, 2016, 1:30:03 PM
 */
class CyanotypeFilter(mode: GrayscaleMode) : GrayScaleFilter(mode) {
    private val cyanotype = AdjustFilter(-0xB17511)
    private val bright = BrightnessFilter(0.1f)
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        super.apply(width, height, pixels)
        cyanotype.apply(width, height, pixels)
        bright.apply(width, height, pixels)
    }
}