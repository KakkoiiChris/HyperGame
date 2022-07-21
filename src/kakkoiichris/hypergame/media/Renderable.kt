package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:16
 */
interface Renderable {
    fun update(view: View, manager: StateManager, time: Time, input: Input)
    
    fun render(view: View, renderer: Renderer)
}
