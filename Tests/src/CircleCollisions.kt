import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Button.LEFT
import kakkoiichris.hypergame.input.Button.RIGHT
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.QuadTree
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color
import java.awt.RenderingHints
import kotlin.random.Random

typealias Collision = Pair<Ball, Ball>

fun main() {
    CircleCollisions.open()
}

object CircleCollisions : Sketch(900, 900, "Circle Collisions", 144.0) {
    private lateinit var balls: List<Ball>

    private val collisions = mutableListOf<Collision>()

    private var held: Ball? = null

    private var mouse = Vector()

    override fun swapTo(view: View) {
        val random = Random(0xCBA)

        balls = List(600) {
            val position = Vector.random(random) * view.size
            val radius = (random.nextDouble() * 18) + 2
            val mass = (random.nextDouble() * 25) + 5
            val color = Color(random.nextInt(0x1000000))

            Ball(position, radius, mass, color)
        }

        view.renderer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    }

    override fun update(view: View, game: Game, time: Time, input: Input) {
        if (input.buttonDown(LEFT) || input.buttonDown(RIGHT)) {
            held = null

            balls.firstOrNull {
                input.mouse in it
            }?.apply {
                reset()

                held = this
            }
        }

        if (input.buttonUp(LEFT)) {
            held = null
        }

        if (input.buttonHeld(LEFT)) {
            held?.position = input.mouse
        }

        if (input.buttonUp(RIGHT)) {
            if (held != null) {
                held!!.velocity.x = (held!!.position.x - input.x) * .1
                held!!.velocity.y = (held!!.position.y - input.y) * .1
                held = null
            }
        }

        mouse.zero()
        mouse += input.mouse

        val tree = QuadTree<Ball>(view.bounds)

        balls.forEach {
            it.update(view, game, time, input)

            tree.insert(it)
        }

        val collide = { a: Ball, b: Ball ->
            (a.position.x - b.position.x) * (a.position.x - b.position.x) + (a.position.y - b.position.y) * (a.position.y - b.position.y) <= (a.radius + b.radius) * (a.radius + b.radius)
        }

        collisions.clear()

        for (a in balls) {
            val near = tree.queryPosition(a.bounds.copy().apply { resize(a.radius * 2) })

            for (b in near) {
                if (a === b) continue

                if (collide(a, b)) {
                    collisions += a to b

                    val distance = a.distanceTo(b)
                    val overlap = (distance - a.radius - b.radius) * .5
                    val diff = (a.position - b.position) * overlap / distance

                    a.position -= diff
                    b.position += diff
                }
            }
        }

        collisions.forEach { (a, b) ->
            val normal = a.position.normalTo(b.position)
            val tangent = normal.tangent

            val dna = a.velocity dot normal
            val dnb = b.velocity dot normal
            val dta = a.velocity dot tangent
            val dtb = b.velocity dot tangent

            val ma = (dna * (a.mass - b.mass) + 2 * b.mass * dnb) / (a.mass + b.mass)
            val mb = (dnb * (b.mass - a.mass) + 2 * a.mass * dna) / (a.mass + b.mass)

            a.velocity = (tangent * dta + normal * ma) * 0.9
            b.velocity = (tangent * dtb + normal * mb) * 0.9
        }
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        renderer.clearRect(0, 0, view.width, view.height)

        balls.forEach { it.render(view, game, renderer) }
    }
}

class Ball(
    override var position: Vector,
    val radius: Double,
    val mass: Double,
    private val color: Color,
) : Renderable, QuadTree.Element {
    override val bounds: Box
        get() = Box(position.x - radius, position.y - radius, radius * 2, radius * 2)

    var velocity = Vector()

    private var acceleration = Vector()

    fun distanceTo(other: Ball) =
        position.distanceTo(other.position)

    fun reset() {
        velocity.zero()
        acceleration.zero()
    }

    operator fun contains(vector: Vector) =
        position.distanceTo(vector) <= radius

    override fun update(view: View, game: Game, time: Time, input: Input) {
        velocity += acceleration * time.delta
        position += velocity

        if (position.x < radius) {
            position.x = radius
            velocity.x *= -1
        }

        if (position.x >= view.width - radius) {
            position.x = view.width - radius
            velocity.x *= -1
        }

        if (position.y < radius) {
            position.y = radius
            velocity.y *= -1
        }

        if (position.y >= view.height - radius) {
            position.y = view.height - radius
            velocity.y *= -1
        }

        if (velocity.magnitude < .01) velocity.zero()

        acceleration = -velocity * .01 + Vector(0.0, 0.1)
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        renderer.push()

        renderer.color = color

        renderer.translate(position.x, position.y)

        renderer.fillOval(-radius.toInt(), -radius.toInt(), (radius * 2).toInt(), (radius * 2).toInt())

        renderer.pop()
    }
}