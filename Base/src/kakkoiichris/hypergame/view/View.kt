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
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.Vector
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

    val manager: StateManager
    val input: Input
    val renderer: Renderer

    val updateCount: Int
    val frameCount: Int

    val size get() = Vector(width.toDouble(), height.toDouble())

    val bounds get() = Box(0.0, 0.0, width.toDouble(), height.toDouble())

    fun getScreenshot(): BufferedImage

    fun open()

    fun close()
}