package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

interface ContextRenderable<G : Game, Context> {
    fun update(view: View<*>, game: G, context: Context, manager: StateManager, time: Time, input: Input)

    fun render(view: View<*>, game: G, context: Context, renderer: Renderer)
}