import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.play.Level
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View
import kakkoiichris.hypergame.view.Window

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
    val window = Window(320, 240, 4, title = "Level Test")

    window.open(Level)
}

object Level : Game {
    private lateinit var level: Level

    override fun init(view: View) {
        level = Level(view.bounds.resized(-10.0), 30, 30)
    }

    override fun update(view: View, time: Time, input: Input) {
        level.update(view, this, time, input)
    }

    override fun render(view: View, renderer: Renderer) {
        level.render(view, this, renderer)
    }

    override fun halt(view: View) {
    }
}