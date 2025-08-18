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
package kakkoiichris.hypergame.view

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.State
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time

abstract class Sketch(width: Int, height: Int, title: String, frameRate: Double = 60.0) : State {
    private val display = Window<Companion>(width, height, frameRate = frameRate, title = title)

    fun open() {
        manager.push(this)
        display.open(Companion)
    }

    fun close() {
        display.close()
    }

    override fun swapTo(view: View<*>) = Unit
    override fun swapFrom(view: View<*>) = Unit
    override fun halt(view: View<*>) = Unit

    companion object : Game {
        private val manager = StateManager()

        override fun init(view: View<*>) {
            manager.init(view)
        }

        override fun update(
            view: View<*>,
            time: Time,
            input: Input
        ) {
            manager.update(view, time, input)
        }

        override fun render(
            view: View<*>,
            renderer: Renderer
        ) {
            manager.render(view, renderer)
        }

        override fun halt(view: View<*>) {
            manager.halt(view)
        }
    }
}