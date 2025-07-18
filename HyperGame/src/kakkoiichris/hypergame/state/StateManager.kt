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

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View
import java.util.*

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:17
 */
class StateManager {
    private val stack = Stack<State>()

    private var swaps = mutableListOf<Swap>()

    internal fun init(view: View) {
        if (stack.isNotEmpty()) {
            stack.peek().swapTo(view)
        }
    }

    internal fun swap(view: View) {
        if (swaps.isEmpty()) return

        for (swap in swaps) {
            when (swap) {
                is Swap.Goto -> {
                    if (stack.isNotEmpty()) {
                        stack.pop().swapFrom(view)
                    }

                    stack.push(swap.state).swapTo(view)
                }

                is Swap.Push -> {
                    if (stack.isNotEmpty()) {
                        stack.peek().swapFrom(view)
                    }

                    stack.push(swap.state).swapTo(view)
                }

                Swap.Pop -> {
                    if (stack.isNotEmpty()) {
                        stack.pop().swapFrom(view)
                    }

                    if (stack.isNotEmpty()) {
                        stack.peek().swapTo(view)
                    }
                }
            }
        }

        swaps.clear()
    }

    fun goto(state: State) {
        swaps += Swap.Goto(state)
    }

    fun push(state: State) {
        swaps += Swap.Push(state)
    }

    fun pop() {
        swaps += Swap.Pop
    }

    internal fun update(view: View, game: Game, time: Time, input: Input) {
        if (stack.isNotEmpty()) {
            stack.peek().update(view, game, time, input)
        }
    }

    internal fun render(view: View, game: Game, renderer: Renderer) {
        if (stack.isNotEmpty()) {
            stack.peek().render(view, game, renderer)
        }
    }

    internal fun halt(view: View) {
        while (stack.isNotEmpty()) {
            stack.pop().halt(view)
        }
    }

    private interface Swap {
        class Goto(val state: State) : Swap

        class Push(val state: State) : Swap

        object Pop : Swap
    }
}
