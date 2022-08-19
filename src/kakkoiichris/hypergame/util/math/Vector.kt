package kakkoiichris.hypergame.util.math

import java.awt.Point
import java.awt.geom.Point2D
import java.util.Random
import kotlin.math.*

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 1/31/2018, 20:10
 */
open class Vector(var x: Double = 0.0, var y: Double = 0.0) {
    companion object {
        fun random(random: Random = Random()) =
            random.nextDouble(PI * 2).let {
                Vector(cos(it), sin(it))
            }
    }

    var magnitude
        get() = sqrt(x.pow(2.0) + y.pow(2.0))
        set(value) {
            normalize()
            times(value)
        }

    var angle
        get() = atan2(y, x)
        set(value) {
            val m = magnitude

            x = m * cos(value)
            y = m * sin(value)
        }

    val tangent
        get() = Vector(-y, x)

    val point
        get() = Point(x.toInt(), y.toInt())

    val point2D
        get() = Point2D.Double(x, y)

    fun zero() {
        x = 0.0
        y = 0.0
    }

    fun normalize() =
        div(magnitude)

    fun normalTo(v: Vector) =
        (v - this).div(distanceTo(v))

    fun distanceTo(v: Vector) =
        sqrt((x - v.x) * (x - v.x) + (y - v.y) * (y - v.y))

    fun mapX(transform: (Double) -> Double) =
        Vector(transform(x), y)

    fun mapY(transform: (Double) -> Double) =
        Vector(x, transform(y))

    fun mapAll(transform: (Double) -> Double) =
        Vector(transform(x), transform(y))

    fun map(transform: (Vector) -> Vector) =
        transform(this)

    infix fun cross(v: Vector) =
        (x * v.y) - (y * v.x)

    infix fun dot(v: Vector) =
        (x * v.x) + (y * v.y)

    operator fun unaryMinus() =
        Vector(-x, -y)

    operator fun plus(s: Double) =
        Vector(x + s, y + s)

    operator fun plus(v: Vector) =
        Vector(x + v.x, y + v.y)

    operator fun minus(s: Double) =
        Vector(x - s, y - s)

    operator fun minus(v: Vector) =
        Vector(x - v.x, y - v.y)

    operator fun times(s: Double) =
        Vector(x * s, y * s)

    operator fun times(v: Vector) =
        Vector(x * v.x, y * v.y)

    operator fun div(s: Double) =
        Vector(x / s, y / s)

    operator fun div(v: Vector) =
        Vector(x / v.x, y / v.y)

    operator fun rem(s: Double) =
        Vector(x % s, y % s)

    operator fun rem(v: Vector) =
        Vector(x % v.x, y % v.y)

    operator fun component1() = x

    operator fun component2() = y

    fun copy(x: Double = this.x, y: Double = this.y) =
        Vector(x, y)

    override fun toString() =
        """"${javaClass.simpleName}" : {
            |  "x" : $x,
            |  "y" : $y
            |}""".trimMargin()
}