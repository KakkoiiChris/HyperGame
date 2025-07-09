package kakkoiichris.hypergame.physics

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

class World : Renderable {
    private val bodies = mutableListOf<Body>()

    fun clear() {
        bodies.clear()
    }

    fun addBody(body: Body) {
        bodies += body
    }

    override fun update(view: View, game: Game, time: Time, input: Input) {
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        bodies.forEach { it.render(renderer) }
    }

    companion object {
        const val MIN_BODY_SIZE = 0.01 * 0.01
        const val MAX_BODY_SIZE = 64.0 * 64.0

        const val MIN_DENSITY = 0.2
        const val MAX_DENSITY = 21.4
    }
}