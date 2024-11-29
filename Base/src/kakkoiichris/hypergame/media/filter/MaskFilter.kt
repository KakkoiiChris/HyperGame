package kakkoiichris.hypergame.media.filter

import kakkoiichris.hypergame.media.ColorOp
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.util.math.min

class MaskFilter(var mask: Sprite, var lowLimit: Double, var highLimit: Double, var hard: Boolean) : Filter {
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val minWidth = min(width, mask.width)
        val minHeight = min(height, mask.height)

        val range = highLimit - lowLimit

        for (y in 0..<minHeight) {
            for (x in 0..<minWidth) {
                val i = x + y * width
                val imageOp = ColorOp.of(pixels[i])

                val j = x + y * mask.width
                val maskOp = ColorOp.of(mask.pixels[j])

                val newAlpha = if (hard) {
                    if (maskOp.average < lowLimit || maskOp.average > highLimit) {
                        0.0
                    }
                    else {
                        1.0
                    }
                }
                else {
                    1.0
                }

                imageOp.alpha = newAlpha

                pixels[i] = imageOp.value
            }
        }
    }
}