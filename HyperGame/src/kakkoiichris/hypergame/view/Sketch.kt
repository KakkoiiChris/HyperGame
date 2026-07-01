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

abstract class Sketch(width: Int, height: Int, title: String, frameRate: Double = 60.0) : Game {
    private val window: Window

    init {
        val config = Config(
            width = width,
            height = height,
            title = title,
            frameRate = frameRate
        )

        window = Window(config)
    }

    fun open() {
        window.open(this)
    }

    fun close() {
        window.close()
    }

    override fun init(view: View) = Unit

    override fun halt(view: View) = Unit
}