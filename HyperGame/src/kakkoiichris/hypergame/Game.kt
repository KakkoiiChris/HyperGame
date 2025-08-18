package kakkoiichris.hypergame

import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

interface Game {
    fun init(view: View<*>)

    fun update(view: View<*>, time: Time, input: Input)

    fun render(view: View<*>, renderer: Renderer)

    fun halt(view: View<*>)
}