package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

interface ContextRenderable<Context> {
    fun update(view: View, game: Game, context: Context, time: Time, input: Input)

    fun render(view: View, game: Game, context: Context, renderer: Renderer)
}