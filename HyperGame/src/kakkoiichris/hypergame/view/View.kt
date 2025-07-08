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

import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.Vector
import java.awt.Canvas
import java.awt.image.BufferedImage

interface View : Runnable {
    companion object {
        const val DEFAULT_WIDTH = 640
        const val DEFAULT_HEIGHT = 480
        const val DEFAULT_SCALE = 1
        const val DEFAULT_FRAME_RATE = 60.0
    }

    val width: Int
    val height: Int
    val scale: Int
    val frameRate: Double

    val image: BufferedImage

    val manager: StateManager
    val input: Input
    val renderer: Renderer

    val updateCount: Int
    val frameCount: Int

    val size get() = Vector(width.toDouble(), height.toDouble())

    val bounds get() = Box(0.0, 0.0, width.toDouble(), height.toDouble())

    val canvas: Canvas

    var preRenderable: Renderable?
    var postRenderable: Renderable?

    fun getScreenshot(): BufferedImage

    fun open()

    fun close()

    fun update(time: Time) {
        preRenderable?.update(this, manager, time, input)

        manager.update(this, time, input)

        postRenderable?.update(this, manager, time, input)

        input.poll(time)
    }

    fun render() {
        preRenderable?.render(this, renderer)

        manager.render(this, renderer)

        postRenderable?.render(this, renderer)

        val buffer = canvas.bufferStrategy

        val graphics = buffer.drawGraphics
        graphics.drawImage(image, 0, 0, canvas.width, canvas.height, null)
        graphics.dispose()

        buffer.show()
    }
}