package kakkoiichris.hypergame.physics

import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.util.math.clamp
import java.awt.Color
import kotlin.math.PI

class Body(
    private var position: Vector,
    private var linearVelocity: Vector,
    private var rotation: Double,
    private var rotationalVelocity: Double,

    private var mass: Double,
    private var density: Double,
    private var restitution: Double,
    private var area: Double,

    private var isStatic: Boolean,

    private var shape: Shape
) {
    fun render(renderer: Renderer) {
        renderer.withState {
            translate(position)

            shape.render(renderer)
        }
    }

    sealed interface Shape {
        fun render(renderer: Renderer)

        data class Circle(val radius: Double) : Shape {
            override fun render(renderer: Renderer) {
                with(renderer) {
                    color = Color.WHITE

                    val r = radius.toInt()

                    drawOval(-r, -r, r * 2, r * 2)
                }
            }
        }

        data class Box(val width: Double, val height: Double) : Shape {
            override fun render(renderer: Renderer) {
                with(renderer) {
                    color = Color.WHITE

                    val w = (width / 2).toInt()
                    val h = (height / 2).toInt()

                    drawRect(-w, -h, w * 2, h * 2)
                }
            }
        }
    }
}

fun createCircleBody(
    radius: Double,
    position: Vector,
    density: Double,
    restitution: Double,
    isStatic: Boolean
): Body? {
    val area = radius * radius * PI

    if (area !in World.MIN_BODY_SIZE..World.MAX_BODY_SIZE) return null

    if (density !in World.MIN_DENSITY..World.MAX_DENSITY) return null

    val restitutionReal = restitution.clamp(0.0, 1.0)

    val mass = area * density

    return Body(
        position,
        Vector(),
        0.0,
        0.0,
        mass,
        density,
        restitutionReal,
        area,
        isStatic,
        Body.Shape.Circle(radius)
    )
}

fun createBoxBody(
    width: Double,
    height: Double,
    position: Vector,
    density: Double,
    restitution: Double,
    isStatic: Boolean
): Body? {
    val area = width * height

    if (area !in World.MIN_BODY_SIZE..World.MAX_BODY_SIZE) return null

    if (density !in World.MIN_DENSITY..World.MAX_DENSITY) return null

    val restitutionReal = restitution.clamp(0.0, 1.0)

    val mass = area * density

    return Body(
        position,
        Vector(),
        0.0,
        0.0,
        mass,
        density,
        restitutionReal,
        area,
        isStatic,
        Body.Shape.Box(width, height)
    )
}