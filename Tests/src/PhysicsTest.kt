import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.input.Key
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.physics.World
import kakkoiichris.hypergame.physics.createBoxBody
import kakkoiichris.hypergame.physics.createCircleBody
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color
import kotlin.random.Random

fun main() {
    PhysicsTest.open()
}

object PhysicsTest : Sketch(800, 600, "Physics") {
    private val world = World()

    private var zoom = 20.0

    override fun swapTo(view: View) {
        world.clear()

        val padding = 20.0

        repeat(10) {
            val isCircle = Random.nextBoolean()

            val position = (Vector.random() - 0.5) * 8

            val body = if (isCircle) {
                createCircleBody(3.0, position, 2.0, 0.5, false) ?: error("BAD CIRCLE")
            }
            else {
                createBoxBody(3.0, 3.0, position, 2.0, 0.5, false) ?: error("BAD BOX")
            }

            world.addBody(body)
        }
    }

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        if (input.keyDown(Key.UP)) {
            zoom *= 2.0
        }

        if (input.keyDown(Key.DOWN)) {
            zoom /= 2.0
        }
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.withState {
            color = Color.BLACK
            renderer.fillRect(view.bounds)

            translate(view.bounds.center)
            scale(1.0, -1.0)
            scale(zoom)

            world.render(view, renderer)
        }
    }
}