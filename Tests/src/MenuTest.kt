import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.ui.SuperMenu
import kakkoiichris.hypergame.ui.form.Button
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View

fun main() {
    MenuTest.open()
}

object MenuTest: Sketch(500, 500, "Menu") {
    private val menu = SuperMenu()

    init {
        menu.setBounds(150.0, 150.0, 200.0, 200.0)
        menu.add(Button("New Game"))
        menu.add(Button("Options"))
        menu.add(Button("Quit"))
    }

    override fun update(view: View, game: Game, time: Time, input: Input) {
        menu.update(view, game, time, input)
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        menu.render(view, game, renderer)
    }
}