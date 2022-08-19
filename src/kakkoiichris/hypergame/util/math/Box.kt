package kakkoiichris.hypergame.util.math

import java.awt.Rectangle
import java.awt.geom.Ellipse2D
import java.awt.geom.Rectangle2D

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 1/31/2018, 20:25
 */
open class Box(var x: Double = 0.0, var y: Double = 0.0, var width: Double = 0.0, var height: Double = 0.0) {
    var position: Vector
        get() = Vector(x, y)
        set(value) {
            x = value.x
            y = value.y
        }

    var size: Vector
        get() = Vector(width, height)
        set(value) {
            width = value.x
            height = value.y
        }

    var centerX: Double
        get() = x + width / 2
        set(value) {
            x = value - width / 2
        }

    var centerY: Double
        get() = y + height / 2
        set(value) {
            y = value - height / 2
        }

    var center: Vector
        get() = Vector(centerX, centerY)
        set(value) {
            centerX = value.x
            centerY = value.y
        }

    var top: Double
        get() = y
        set(value) {
            y = value
        }

    var bottom: Double
        get() = y + height
        set(value) {
            y = value - height
        }

    var left: Double
        get() = x
        set(value) {
            x = value
        }

    var right: Double
        get() = x + width
        set(value) {
            x = value - width
        }

    val rectangle
        get() = Rectangle(x.toInt(), y.toInt(), width.toInt(), height.toInt())

    val rectangle2D
        get() = Rectangle2D.Double(x, y, width, height)

    val ellipse2D
        get() = Ellipse2D.Double(x, y, width, height)

    fun setBounds(x: Double, y: Double, width: Double, height: Double) {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }

    fun resize(amountX: Double, amountY: Double) {
        x -= amountX / 2
        y -= amountY / 2
        width += amountX
        height += amountY
    }

    fun resize(amount: Double) =
        resize(amount, amount)

    fun resize(amount: Vector) =
        resize(amount.x, amount.y)

    operator fun contains(vector: Vector) =
        left < vector.x && vector.x < right && top < vector.y && vector.y < bottom

    operator fun contains(box: Box) =
        left < box.left && box.right < right && top < box.top && box.bottom < bottom

    operator fun component1() = x

    operator fun component2() = y

    operator fun component3() = width

    operator fun component4() = height

    fun intersects(box: Box): Boolean {
        if (left == right || top == bottom || box.left == box.right || box.top == box.right) {
            return false
        }

        if (left >= box.right || box.left >= right || top >= box.bottom || box.top >= bottom) {
            return false
        }

        return true
    }

    fun intersectionOf(box: Box): Box? {
        if (!intersects(box)) return null

        val newLeft = max(left, box.left)
        val newRight = min(right, box.right)

        val newWidth = newRight - newLeft

        val newTop = max(top, box.top)
        val newBottom = min(bottom, box.bottom)

        val newHeight = newBottom - newTop

        return Box(newTop, newLeft, newWidth, newHeight)
    }

    fun copy(x: Double = this.x, y: Double = this.y, width: Double = this.width, height: Double = this.height) =
        Box(x, y, width, height)

    override fun toString() =
        """"${javaClass.simpleName}" : {
            |  "x":      $x,
            |  "y":      $y,
            |  "width":  $width,
            |  "height": $height
            |}""".trimMargin()
}