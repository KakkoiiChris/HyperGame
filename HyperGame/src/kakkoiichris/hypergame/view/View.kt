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
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.Vector
import java.awt.Canvas
import java.awt.image.BufferedImage

interface View {
    val config: Config

    val width get()= config.width
    val height get()= config.height
    val scale get() = config.scale
    val isResizable get() = config.isResizable
    val frameRate get() = config.frameRate

    val image: BufferedImage

    val input: Input
    val renderer: Renderer

    val updateCount: Int
    val frameCount: Int

    val size get() = Vector(width.toDouble(), height.toDouble())

    val bounds get() = Box(0.0, 0.0, width.toDouble(), height.toDouble())

    val canvas: Canvas

    fun getScreenshot(): BufferedImage

    fun open(game: Game)

    fun close()

    fun update(game: Game, time: Time) {
        game.update(this, time, input)

        input.poll(time)
    }

    fun render(game: Game) {
        game.render(this, renderer)

        val buffer = canvas.bufferStrategy

        val graphics = buffer.drawGraphics
        graphics.drawImage(image, 0, 0, canvas.width, canvas.height, null)
        graphics.dispose()

        buffer.show()
    }
}