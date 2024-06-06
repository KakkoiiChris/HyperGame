import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.BlendComposite
import kakkoiichris.hypergame.media.BlendMode
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color

fun main() {
    Test3.open()
}

class Sphere(private var velocity: Vector, private var hue: Float) : Box(width = 100.0, height = 100.0), Renderable {
    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        position += velocity * time.delta

        if (left <= view.bounds.left || right >= view.bounds.right) {
            velocity.x *= -1
        }

        if (top <= view.bounds.top || bottom >= view.bounds.bottom) {
            velocity.y *= -1
        }

        hue += (time.delta * 0.005).toFloat()
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.color = Color(Color.HSBtoRGB(hue, 1F, 1F))

        renderer.fillOval(this)
    }
}

object Test3 : Sketch(800, 800, "Zen Rainbow Energy Spheres") {
    private const val COUNT = 6

    private val spheres = Array(COUNT) { i ->
        Sphere(Vector(i + 1.0, COUNT.toDouble() - i), i * (1F / COUNT))
    }

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        spheres.forEach {
            it.update(view, manager, time, input)
        }
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.color = Color(0, 0, 0, 16)

        renderer.fillRect(view.bounds)

        val last = renderer.composite

        renderer.composite = BlendComposite(BlendMode.SCREEN)

        spheres.forEach {
            it.render(view, renderer)
        }

        renderer.composite = last
    }
}