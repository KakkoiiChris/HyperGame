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
    
    fun swapTo(view: View, passed: List<Any>)
    
    fun swapFrom(view: View)
    
    fun halt(view: View)
    
    override fun update(view: View, manager: StateManager, time: Time, input: Input)
    
    override fun render(view: View, renderer: Renderer)
}
