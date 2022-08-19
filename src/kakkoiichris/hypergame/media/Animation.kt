package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.util.Time
import kotlin.random.Random

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 1/31/2018, 19:13
 */
class Animation(private val frames: Array<Sprite>, var speed: Double, var style: Style) {
    enum class Style {
        BOUNCE,
        LOOP,
        ONCE,
        RANDOM
    }
    
    var timer = 0.0
    var index = 0
    var forward = true
    var running = true
    var elapsed = false
    
    val frame get() = frames[index]
    
    operator fun get(index: Int) =
        frames[index]
    
    fun reset() {
        timer = 0.0
        index = if (forward) 0 else frames.lastIndex
    }
    
    fun step() {
        when (style) {
            Style.BOUNCE -> {
                index += if (forward) 1 else -1
                
                if (index == 0 || index == frames.size - 1) {
                    forward = !forward
                    
                    if (forward) {
                        elapsed = true
                    }
                }
            }
            
            Style.LOOP   -> {
                index += if (forward) 1 else -1
                
                if (index == -1) {
                    index = frames.size - 1
                    elapsed = true
                }
                else if (index == frames.size) {
                    index = 0
                    elapsed = true
                }
            }
            
            Style.ONCE   -> {
                index += if (forward) 1 else -1
                
                if (index == -1) {
                    index = 0
                    elapsed = true
                }
                else if (index == frames.size) {
                    index = frames.size - 1
                    elapsed = true
                }
            }
            
            Style.RANDOM -> index = Random.nextInt(frames.size)
        }
    }
    
    fun update(time: Time) {
        if (running) {
            timer += time.seconds
            
            if (timer >= speed) {
                timer -= speed
                
                step()
            }
        }
    }
    
    fun copy(): Animation {
        val copy = Animation(frames, speed, style)
        
        copy.timer = timer
        copy.index = index
        copy.forward = forward
        copy.running = running
        copy.elapsed = elapsed
        
        return copy
    }
}