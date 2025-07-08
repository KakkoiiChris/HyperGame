package kakkoiichris.hypergame.media

import java.awt.Composite
import java.awt.CompositeContext
import java.awt.RenderingHints
import java.awt.image.ColorModel
import java.awt.image.DataBuffer
import java.awt.image.Raster
import java.awt.image.WritableRaster
import kotlin.math.min

class BlendComposite(val mode: BlendMode) : Composite, CompositeContext {
    override fun compose(src: Raster, dstIn: Raster, dstOut: WritableRaster) {
        checkRaster(src)
        checkRaster(dstIn)
        checkRaster(dstOut)

        val width = min(src.width.toDouble(), dstIn.width.toDouble()).toInt()
        val height = min(src.height.toDouble(), dstIn.height.toDouble()).toInt()
        val srcPixels = IntArray(width)
        val dstPixels = IntArray(width)

        for (y in 0..<height) {
            src.getDataElements(0, y, width, 1, srcPixels)
            dstIn.getDataElements(0, y, width, 1, dstPixels)

            for (x in 0..<width) {
                dstPixels[x] = mixPixel(srcPixels[x], dstPixels[x])
            }

            dstOut.setDataElements(0, y, width, 1, dstPixels)
        }
    }

    private fun mixPixel(x: Int, y: Int): Int {
        val xb = (x and 0xFF) / 255.0
        val yb = (y and 0xFF) / 255.0
        val b = (mode(xb, yb) * 255).toInt()

        val xg = ((x shr 8) and 0xFF) / 255.0
        val yg = ((y shr 8) and 0xFF) / 255.0
        val g = (mode(xg, yg) * 255).toInt()

        val xr = ((x shr 16) and 0xFF) / 255.0
        val yr = ((y shr 16) and 0xFF) / 255.0
        val r = (mode(xr, yr) * 255).toInt()

        val xa = ((x shr 24) and 0xFF) / 255.0
        val ya = ((y shr 24) and 0xFF) / 255.0
        val a = min(255.0, (xa + ya) * 255).toInt()

        return (b) or (g shl 8) or (r shl 16) or (a shl 24)
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