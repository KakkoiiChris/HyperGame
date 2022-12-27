/***************************************************************************
 *   ___ ___                                ________                       *
 *  /   |   \ ___.__.______   ___________  /  _____/_____    _____   ____  *
 * /    ~    <   |  |\____ \_/ __ \_  __ \/   \  ___\__  \  /     \_/ __ \ *
 * \    Y    /\___  ||  |_> >  ___/|  | \/\    \_\  \/ __ \|  Y Y  \  ___/ *
 *  \___|_  / / ____||   __/ \___  >__|    \______  (____  /__|_|  /\___  >*
 *        \/  \/     |__|        \/               \/     \/      \/     \/ *
 *                    Kotlin 2D Game Development Library                   *
 *                     Copyright (C) 2021, KakkoiiChris                    *
 ***************************************************************************/
package kakkoiichris.hypergame.state

import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:17
 */
class StateManager {
    private val states = mutableMapOf<String, State>()
    
    private var currentState: State? = null
    
    private var swapRequest: SwapRequest? = null
    
    operator fun plusAssign(state: State) {
        states[state.name] = state
        
        currentState = currentState ?: state
    }
    
    fun goto(name: String, vararg passed: Any) {
        swapRequest = SwapRequest(name, listOf(*passed))
    }
    
    internal fun init(view: View) {
        currentState?.swapTo(view, emptyList())
    }
    
    internal fun swap(view: View) {
        if (swapRequest != null) {
            currentState?.swapFrom(view)
            
            currentState = states[swapRequest!!.name]
            
            currentState?.swapTo(view, swapRequest!!.args)
            
            swapRequest = null
        }
    }
    
    internal fun update(view: View, time: Time, input: Input) {
        currentState?.update(view, this, time, input)
    }
    
    internal fun render(view: View, renderer: Renderer) {
        currentState?.render(view, renderer)
    }
    
    internal fun halt(view: View) {
        states.forEach { (_, state) -> state.halt(view) }
    }
    
    data class SwapRequest(val name: String, val args: List<Any>)
}
