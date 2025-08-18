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
package kakkoiichris.hypergame.input

import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.util.math.toVector
import kakkoiichris.hypergame.view.View
import java.awt.Point
import java.awt.event.*

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 1/31/2018, 18:55
 */
class Input internal constructor(private val view: View<*>) : KeyListener,
                                                           MouseListener,
                                                           MouseMotionListener,
                                                           MouseWheelListener {
    private val keys = Key.entries.associate { it.code to Toggle(it) }

    private val buttons = Button.entries.associate { it.code to Toggle(it) }

    var mouse = Vector(); private set

    val x get() = mouse.x.toInt()

    val y get() = mouse.y.toInt()

    var wheel: Int = 0
        private set

    var inWindow = false
        private set

    private val typed = mutableListOf<Char>()

    internal fun poll(time: Time) {
        for ((_, toggle) in keys + buttons) {
            toggle.poll(time)
        }

        wheel = 0
    }

    fun keyDown(key: Key) =
        keys[key.code]!!.isDown

    fun keyHeld(key: Key) =
        keys[key.code]!!.isHeld

    fun keyUp(key: Key) =
        keys[key.code]!!.isUp

    fun anyKeyHeld() =
        keys.any { it.value.isHeld }

    fun anyKeyDown() =
        keys.any { it.value.isDown }

    fun anyKeyUp() =
        keys.any { it.value.isUp }

    fun keyCount(key: Key) =
        keys[key.code]!!.pressCount

    fun getTypedChar() =
        typed.removeFirstOrNull()

    fun buttonDown(button: Button) =
        buttons[button.code]!!.isDown

    fun buttonHeld(button: Button) =
        buttons[button.code]!!.isHeld

    fun buttonUp(button: Button) =
        buttons[button.code]!!.isUp

    fun anyButtonHeld() =
        buttons.any { it.value.isHeld }

    fun anyButtonDown() =
        buttons.any { it.value.isDown }

    fun anyButtonUp() =
        buttons.any { it.value.isUp }

    fun buttonCount(button: Button) =
        buttons[button.code]!!.pressCount

    fun translate(x: Double, y: Double) {
        mouse -= Vector(x, y)
    }

    fun translate(vector: Vector) {
        mouse -= vector
    }

    fun forEach(action: (Toggle) -> Unit) {
        for ((_, toggle) in keys + buttons) {
            action(toggle)
        }
    }

    override fun keyPressed(e: KeyEvent) {
        if (!keys.containsKey(e.keyCode)) return

        val key = keys[e.keyCode]!!

        key.set(true)
    }

    override fun keyReleased(e: KeyEvent) {
        if (!keys.containsKey(e.keyCode)) return

        val key = keys[e.keyCode]!!

        key.set(false)
    }

    override fun keyTyped(e: KeyEvent) {
        typed += e.keyChar
    }

    override fun mousePressed(e: MouseEvent) {
        if (!buttons.containsKey(e.button)) return

        val button = buttons[e.button]!!

        button.set(true)
    }

    override fun mouseReleased(e: MouseEvent) {
        if (!buttons.containsKey(e.button)) return

        val button = buttons[e.button]!!

        button.set(false)
    }

    override fun mouseClicked(e: MouseEvent) =
        e.consume()

    override fun mouseEntered(e: MouseEvent) {
        inWindow = true
    }

    override fun mouseExited(e: MouseEvent) {
        inWindow = false
    }

    private fun setMouse(point: Point) {
        mouse = point.toVector() / view.scale.toDouble()
    }

    override fun mouseMoved(e: MouseEvent) {
        setMouse(e.point)
    }

    override fun mouseDragged(e: MouseEvent) {
        setMouse(e.point)
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        wheel += e.wheelRotation
    }
}