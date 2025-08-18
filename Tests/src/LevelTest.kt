import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.play.Level
import kakkoiichris.hypergame.state.State
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Display
import kakkoiichris.hypergame.view.View

/**
 * HyperGame Play
 *
 * Copyright (C) 2023, KakkoiiChris
 *
 * File:    LevelTest.kt
 *
 * Created: Monday, June 05, 2023, 21:43:20
 *
 * @author Christian Bryce Alexander
 */
fun main() {
    val display = Display(320, 240, 4, title = "Level Test")

    display.open(MyGame)
}

object MyGame : Game() {
    override fun init(view: View) {
        stateManager.push(LevelState)
    }
}

object LevelState : State {
    private lateinit var level: Level

    override fun swapTo(view: View, game: Game) {
        level = Level(view.bounds.resized(-10.0), 30, 30)
    }

    override fun swapFrom(view: View, game: Game) {
    }

    override fun update(view: View, game: Game, time: Time, input: Input) {
        level.update(view, game, time, input)
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        level.render(view, game, renderer)
    }

    override fun halt(view: View, game: Game) {
    }
}