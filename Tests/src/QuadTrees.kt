import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Colors
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.QuadTree
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import kotlin.random.Random

fun main() {
    Test4.open()
}

object Test4 : Sketch(640, 480, "QuadTree Test") {
    private val entities = mutableListOf<Entity>()

    private lateinit var tree: QuadTree<Entity>

    private val box = Box(0.0, 0.0, 100.0, 100.0)

    private var timer = 0.0

    override fun swapTo(view: View) {
        tree = QuadTree(view.bounds)
    }

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        box.center = input.mouse

        timer += time.seconds

        entities += Entity()

        tree = QuadTree(view.bounds)

        entities.forEach {
            it.highlight = false
            tree.insert(it)
        }

        val highlighted = tree.queryPosition(box)
        highlighted.forEach { it.highlight = true }
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.color = Colors.black

        renderer.fillRect(view.bounds)

        renderer.color = Colors.gray

        tree.render(renderer)

        renderer.color = Colors.rose

        renderer.drawRect(box)

        entities.forEach { it.render(view, renderer) }
    }
}

class Entity : QuadTree.Element, Renderable {
    companion object {
        const val RADIUS = 3
    }

    override val position = Vector.random(Random(32))
    override val bounds = Box(position.x, position.y, 1.0)

    var highlight = false

    override fun update(view: View, manager: StateManager, time: Time, input: Input) = Unit

    override fun render(view: View, renderer: Renderer) {
        renderer.color = if (highlight) Colors.aqua else Colors.white

        renderer.fillOval(position.x.toInt() - RADIUS, position.y.toInt() - RADIUS, RADIUS * 2, RADIUS * 2)
    }
}