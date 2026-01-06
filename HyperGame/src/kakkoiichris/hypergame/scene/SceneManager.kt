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
package kakkoiichris.hypergame.scene

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
class SceneManager {
    private val stack = Stack<Scene>()

    private var swaps = Queue<Swap>()

    fun goto(scene: Scene) {
        swaps += Swap.Goto(scene)
    }

    fun push(scene: Scene) {
        swaps += Swap.Push(scene)
    }

    fun pop() {
        swaps += Swap.Pop()
    }

    fun init(view: View, game: Game) {
        stack.peek()?.swapTo(view, game)
    }

    fun update(view: View, game: Game, time: Time, input: Input) {
        swap(view, game)

        stack.peek()?.update(view, game, time, input)
    }

    private fun swap(view: View, game: Game) {
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

    fun render(view: View, game: Game, renderer: Renderer) {
        stack.peek()?.render(view, game, renderer)
    }

    fun halt(view: View, game: Game) {
        stack.pop()?.halt(view, game)
    }

    private interface Swap {
        class Goto(val scene: Scene) : Swap

        class Push(val scene: Scene) : Swap

        class Pop : Swap
    }
}