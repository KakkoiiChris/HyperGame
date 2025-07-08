package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.util.math.clamp
import java.awt.Composite
import java.awt.CompositeContext
import java.awt.RenderingHints
import java.awt.image.ColorModel
import java.awt.image.DataBuffer
import java.awt.image.Raster
import java.awt.image.WritableRaster
import kotlin.math.min

class BlurComposite(val size: Int = 1) : Composite, CompositeContext {
    private val square = ((size * 2) + 1) * ((size * 2) + 1)

    override fun compose(src: Raster, dstIn: Raster, dstOut: WritableRaster) {
        checkRaster(src)
        checkRaster(dstIn)
        checkRaster(dstOut)

        val width = min(src.width, dstIn.width)
        val height = min(src.height, dstIn.height)

        val srcPixels = IntArray(width * height)
        val dstPixels = IntArray(width * height)

        src.getDataElements(0, 0, width, height, srcPixels)
        dstIn.getDataElements(0, 0, width, height, dstPixels)

        val outPixels = IntArray(width * height)

        for (y in 0..<height) {
            for (x in 0..<width) {
                val srcOp = ColorOp.of(srcPixels[x + y * width])
                val resOp = ColorOp()

                for (oy in -size..size) {
                    val ry = (y + oy).clamp(0, height - 1)

                    for (ox in -size..size) {
                        val rx = (x + ox).clamp(0, width - 1)

                        val i = rx + ry * width

                        resOp += ColorOp.of(dstPixels[i])
                    }
                }

                resOp /= square

                //srcOp.blend(resOp)

                outPixels[x + y * width] = resOp.value
            }
        }

        dstOut.setDataElements(0, 0, width, height, outPixels)
    }

    override fun createContext(
        srcColorModel: ColorModel,
        dstColorModel: ColorModel,
        hints: RenderingHints,
    ): CompositeContext {
        return this
    }

    override fun dispose() {
    }
}