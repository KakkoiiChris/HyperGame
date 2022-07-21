package kakkoiichris.hypergame.state

import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:17
 */
interface State : Renderable {
    val name: String
    
    fun swap(view: View, passed: List<Any>)
    
    override fun update(view: View, manager: StateManager, time: Time, input: Input)
    
    override fun render(view: View, renderer: Renderer)
    
    fun halt(view: View)
}
