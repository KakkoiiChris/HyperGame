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

import kakkoiichris.hypergame.media.*
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which applies a simple 3x3 box blur to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 9:12:14 PM
 */
class BlurFilter(private val kernel: Array<FloatArray>) : Filter {
    companion object {
        fun getKernel(size: Int): Array<FloatArray> {
            if (size % 2 == 0) {
                return getKernel(size + 1)
            }
            
            val v = 1F / (size * size).toFloat()
            
            return Array(size) { FloatArray(size) { v } }
        }
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val newPixels = IntArray(pixels.size)
        
        for (y in 0 until pixels.size / width) {
            for (x in 0 until width) {
                var rt = 0f
                var gt = 0f
                var bt = 0f
                
                for (i in kernel.indices) {
                    for (j in kernel.indices) {
                        val xloc = x + i - kernel.size / 2
                        val yloc = y + j - kernel.size / 2
                        
                        val loc = (xloc + yloc * width).clamp(0, pixels.size - 1)
                        
                        val argb = pixels[loc].argbF
                        
                        rt += argb.red * kernel[i][j]
                        gt += argb.green * kernel[i][j]
                        bt += argb.blue * kernel[i][j]
                    }
                }
                
                rt = rt.clamp(0f, 1f)
                gt = gt.clamp(0f, 1f)
                bt = bt.clamp(0f, 1f)
                
                newPixels[x + y * width] = floatArrayOf(1F, rt, gt, bt).toColor()
            }
        }
    
        System.arraycopy(newPixels, 0, pixels, 0, pixels.size)
    }
}