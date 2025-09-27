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
import kakkoiichris.hypergame.util.Queue
import kakkoiichris.hypergame.util.Stack
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:17
 */
class SceneManager<G : Game> {
    private val stack = Stack<Scene<G>>()

    private var swaps = Queue<Swap<G>>()

    fun goto(scene: Scene<G>) {
        swaps += Swap.Goto(scene)
    }

    fun push(scene: Scene<G>) {
        swaps += Swap.Push(scene)
    }

    fun pop() {
        swaps += Swap.Pop()
    }

    fun init(view: View<*>, game: G) {
        stack.peek()?.swapTo(view, game)
    }

    fun update(view: View<*>, game: G, time: Time, input: Input) {
        swap(view, game)

        stack.peek()?.update(view, game, time, input)
    }

    private fun swap(view: View<*>, game: G) {
        while (!swaps.isEmpty()) {
            when (val swap = swaps.remove()) {
                is Swap.Goto -> {
                    stack.pop()?.swapFrom(view, game)

                    stack.push(swap.scene)

                    stack.peek()?.swapTo(view, game)
                }

                is Swap.Push -> {
                    stack.push(swap.scene)

                    stack.peek()?.swapTo(view, game)
                }

                is Swap.Pop  -> {
                    stack.pop()?.swapFrom(view, game)

                    stack.peek()?.swapTo(view, game)
                }
            }
        }
    }

    fun render(view: View<*>, game: G, renderer: Renderer) {
        stack.peek()?.render(view, game, renderer)
    }

    fun halt(view: View<*>, game: G) {
        stack.pop()?.halt(view, game)
    }

    private interface Swap<G : Game> {
        class Goto<G : Game>(val scene: Scene<G>) : Swap<G>

        class Push<G : Game>(val scene: Scene<G>) : Swap<G>

        class Pop<G : Game> : Swap<G>
    }
}