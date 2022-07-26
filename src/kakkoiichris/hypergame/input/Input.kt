package kakkoiichris.hypergame.input

import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.view.View
import java.awt.event.*

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 1/31/2018, 18:55
 */
class Input internal constructor(private val view: View) : KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private val keys = Array(256) { Toggle() }
    
    private val buttons = Array(4) { Toggle() }
    
    private val pollBuffer = mutableListOf<Toggle>()
    
    var x: Int = 0
        private set
    
    var y: Int = 0
        private set
    
    var wheel: Int = 0
        private set
    
    var inWindow = false
        private set
    
    val mousePoint: Vector
        get() = Vector(x.toDouble(), y.toDouble())
    
    internal fun poll() {
        if (pollBuffer.isNotEmpty()) {
            pollBuffer.forEach(Toggle::poll)
            
            pollBuffer.clear()
        }
        
        wheel = 0
    }
    
    fun keyDown(key: Key) =
        keys[key.code].isDown
    
    fun keyHeld(key: Key) =
        keys[key.code].isHeld
    
    fun keyUp(key: Key) =
        keys[key.code].isUp
    
    fun buttonDown(button: Button) =
        buttons[button.code].isDown
    
    fun buttonHeld(button: Button) =
        buttons[button.code].isHeld
    
    fun buttonUp(button: Button) =
        buttons[button.code].isUp
    
    override fun keyPressed(e: KeyEvent) {
        if (e.keyCode in keys.indices) {
            val key = keys[e.keyCode]
            
            key.set(true)
            
            pollBuffer.add(key)
        }
    }
    
    override fun keyReleased(e: KeyEvent) {
        if (e.keyCode in keys.indices) {
            val key = keys[e.keyCode]
            
            key.set(false)
            
            pollBuffer.add(key)
        }
    }
    
    override fun keyTyped(e: KeyEvent) =
        e.consume()
    
    override fun mousePressed(e: MouseEvent) {
        if (e.button in buttons.indices) {
            val button = buttons[e.button]
            
            button.set(true)
            
            pollBuffer.add(button)
        }
    }
    
    override fun mouseReleased(e: MouseEvent) {
        if (e.button in buttons.indices) {
            val button = buttons[e.button]
            
            button.set(false)
            
            pollBuffer.add(button)
        }
    }
    
    override fun mouseClicked(e: MouseEvent) =
        e.consume()
    
    override fun mouseEntered(e: MouseEvent) {
        inWindow = true
    }
    
    override fun mouseExited(e: MouseEvent) {
        inWindow = false
    }
    
    override fun mouseMoved(e: MouseEvent) {
        x = e.x / view.scale
        y = e.y / view.scale
    }
    
    override fun mouseDragged(e: MouseEvent) {
        x = e.x / view.scale
        y = e.y / view.scale
    }
    
    override fun mouseWheelMoved(e: MouseWheelEvent) {
        wheel += e.wheelRotation
    }
}