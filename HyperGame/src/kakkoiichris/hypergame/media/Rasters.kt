package kakkoiichris.hypergame.media

import java.awt.image.DataBuffer
import java.awt.image.Raster

fun checkRaster(r: Raster) {
    check(r.sampleModel.dataType == DataBuffer.TYPE_INT) { "Expected integer sample type" }
}