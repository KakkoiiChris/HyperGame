package kakkoiichris.hypergame.media.filter

import kakkoiichris.hypergame.media.BlendMode
import kakkoiichris.hypergame.media.ColorOp
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.util.math.min

class BlendFilter(val source: Sprite, val mode: BlendMode) : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val minWidth = min(width, source.width)
        val minHeight = min(height, source.height)

        for (y in 0..<minHeight) {
            for (x in 0..<minWidth) {
                val i = x + y * width
                val imageOp = ColorOp.of(pixels[i])

                val j = x + y * source.width
                val sourceOp = ColorOp.of(source.pixels[j])

                imageOp.mapWith(sourceOp, mode::invoke)

                pixels[i] = imageOp.value
            }
        }
    }
}