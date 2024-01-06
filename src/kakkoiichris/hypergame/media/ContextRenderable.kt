package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

interface ContextRenderable<Context> {
    fun update(view: View, context: Context, manager: StateManager, time: Time, input: Input)

    fun render(view: View, context: Context, renderer: Renderer)
}