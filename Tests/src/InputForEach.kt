import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Colors
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View

fun main() {
    InputForEach.open()
}

object InputForEach : Sketch(500, 500, "Input Test") {
    private var down = false

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        down = false

        input.forEach {
            if (it.isDown) {
                println(it.id)

                down = true
            }
        }
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.clearRect(view.bounds)

        if (!down) return

        renderer.color = Colors.CSS.dodgerBlue
        renderer.fillRect(view.bounds)
    }
}