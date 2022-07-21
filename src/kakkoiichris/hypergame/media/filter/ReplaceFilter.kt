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
 * A [Filter] which replaces one set of colors in a [Sprite] with
 * another set of colors.
 *
 * @author Christian Bryce Alexander
 * @since Jan 6, 2016, 6:08:49 PM
 */
class ReplaceFilter(var map: IntArray, var colors: IntArray) : Filter {
    init {
        if (map.size != colors.size) {
            System.err.println("LIGHT DRIVE ERROR: Mismatching arrays.")
            Thread.dumpStack()
        }
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        for (i in pixels.indices) {
            for (c in map.indices) {
                if (pixels[i] == map[c]) {
                    pixels[i] = colors[c]
                }
            }
        }
    }
}