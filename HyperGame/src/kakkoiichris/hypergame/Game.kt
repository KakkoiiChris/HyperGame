package kakkoiichris.hypergame

import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

abstract class Game {
    val stateManager = StateManager()

    abstract fun init(view: View)

    internal fun swap(view: View) {
        stateManager.swap(view)
    }

    internal fun update(view: View, time: Time, input: Input) {

           stateManager.update(view, this, time, input)
    }

    internal fun render(view: View, renderer: Renderer) {
        stateManager.render(view, this, renderer)
    }

    internal fun halt(view: View) {
        stateManager.halt(view)
    }
}