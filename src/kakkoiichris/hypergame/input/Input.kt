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
class Input internal constructor(private val view: View) : KeyListener, MouseListener, MouseMotionListener,
    MouseWheelListener {
    private val keys = Key.entries.map { Toggle(it) }.toTypedArray()

    private val buttons = Button.entries.map { Toggle(it) }.toTypedArray()

    var mouse = Vector(); private set

    val x get() = mouse.x.toInt()

    val y get() = mouse.y.toInt()

    var wheel: Int = 0
        private set

    var inWindow = false
        private set

    internal fun poll() {
        for (toggle in keys + buttons) {
            toggle.poll()
        }

        wheel = 0
    }

    fun keyDown(key: Key) =
        keys[key.code].isDown

    fun keyHeld(key: Key) =
        keys[key.code].isHeld

    fun keyUp(key: Key) =
        keys[key.code].isUp

    fun keyCount(key: Key) =
        keys[key.code].pressCount

    fun buttonDown(button: Button) =
        buttons[button.code].isDown

    fun buttonHeld(button: Button) =
        buttons[button.code].isHeld

    fun buttonUp(button: Button) =
        buttons[button.code].isUp

    fun keyCount(button: Button) =
        buttons[button.code].pressCount

    fun forEach(action: (Toggle) -> Unit) {
        for (toggle in keys + buttons) {
            action(toggle)
        }
    }

    fun translate(x: Double, y: Double) {
        mouse -= Vector(x, y)
    }

    fun translate(vector: Vector) {
        mouse -= vector
    }

    override fun keyPressed(e: KeyEvent) {
        if (e.keyCode in keys.indices) {
            val key = keys[e.keyCode]

            key.set(true)
        }
    }

    override fun keyReleased(e: KeyEvent) {
        if (e.keyCode in keys.indices) {
            val key = keys[e.keyCode]

            key.set(false)
        }
    }

    override fun keyTyped(e: KeyEvent) =
        e.consume()

    override fun mousePressed(e: MouseEvent) {
        if (e.button - 1 in buttons.indices) {
            val button = buttons[e.button - 1]

            button.set(true)
        }
    }

    override fun mouseReleased(e: MouseEvent) {
        if (e.button - 1 in buttons.indices) {
            val button = buttons[e.button - 1]

            button.set(false)
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