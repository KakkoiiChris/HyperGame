package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.media.filter.Filter
import java.awt.image.BufferedImage
import java.awt.image.DataBufferInt
import javax.imageio.ImageIO

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:15
 */


class Sprite : BufferedImage {
    companion object {
        val extensions = arrayOf("bmp", "gif", "jpg", "jpeg", "png", "webmp")
        
        fun load(path: String): Sprite {
            val image = ImageIO.read(Sprite::class.java.getResourceAsStream(path))
            
            val width = image.width
            val height = image.height
            val rgb = image.getRGB(0, 0, width, height, null, 0, width)
            
            image.flush()
            
            return Sprite(width, height, rgb)
        }
    }
    
    val pixels: IntArray
    
    constructor(width: Int, height: Int, rgb: IntArray) : super(width, height, BufferedImage.TYPE_INT_ARGB) {
        pixels = (raster.dataBuffer as DataBufferInt).data
        
        rgb.forEachIndexed { i, c -> pixels[i] = c }
    }
    
    constructor(width: Int, height: Int) : super(width, height, BufferedImage.TYPE_INT_ARGB) {
        pixels = (raster.dataBuffer as DataBufferInt).data
        
        pixels.fill(0xFF000000.toInt())
    }
    
    fun clear() =
        pixels.fill(0)
    
    fun clear(color: Int) =
        pixels.fill(color)
    
    fun crop(x: Int, y: Int, width: Int, height: Int): Sprite {
        val cropped = IntArray(width * height)
        
        var i = 0
        
        for (yy in y until y + height) {
            for (xx in x until x + width) {
                cropped[i++] = pixels[xx + yy * this.width]
            }
        }
        
        return Sprite(width, height, cropped)
    }
    
    fun copy() =
        Sprite(width, height, pixels.copyOf())
    
    fun filter(vararg filters: Filter) {
        for (filter in filters) {
            filter.apply(width, height, pixels)
        }
    }
    
    operator fun get(i: Int) =
        pixels[i]
    
    operator fun set(i: Int, c: Int) {
        pixels[i] = c
    }
    
    operator fun get(x: Int, y: Int) =
        pixels[x + y * width]
    
    operator fun set(x: Int, y: Int, c: Int) {
        pixels[x + y * width] = c
    }
}

fun BufferedImage.toSprite() =
    Sprite(width, height, (raster.dataBuffer as DataBufferInt).data)