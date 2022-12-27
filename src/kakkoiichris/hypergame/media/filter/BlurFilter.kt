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

import kakkoiichris.hypergame.media.*
import kakkoiichris.hypergame.util.math.clamp

/**
 * A [Filter] which applies a simple 3x3 box blur to the [Sprite].
 *
 * @author Christian Bryce Alexander
 * @since Apr 23, 2016, 9:12:14 PM
 */
class BlurFilter(private val kernel: Array<DoubleArray>) : Filter {
    companion object {
        fun getKernel(size: Int): Array<DoubleArray> {
            if (size % 2 == 0) {
                return getKernel(size + 1)
            }
            
            val v = 1F / (size * size).toDouble()
            
            return Array(size) { DoubleArray(size) { v } }
        }
    }
    
    override fun apply(width: Int, height: Int, pixels: IntArray) {
        val newPixels = IntArray(pixels.size)
        
        for (y in 0 until pixels.size / width) {
            for (x in 0 until width) {
                var rt = 0.0
                var gt = 0.0
                var bt = 0.0
                
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
                
                rt = rt.clamp(0.0, 1.0)
                gt = gt.clamp(0.0, 1.0)
                bt = bt.clamp(0.0, 1.0)
                
                newPixels[x + y * width] = doubleArrayOf(1.0, rt, gt, bt).toColor()
            }
        }
    
        System.arraycopy(newPixels, 0, pixels, 0, pixels.size)
    }
}