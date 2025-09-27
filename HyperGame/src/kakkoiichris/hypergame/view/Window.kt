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
import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:12
 */
class Window<G: Game>(
    override val width: Int = View.DEFAULT_WIDTH,
    override val height: Int = View.DEFAULT_HEIGHT,
    override val scale: Int = View.DEFAULT_SCALE,
    override val frameRate: Double = View.DEFAULT_FRAME_RATE,
    val title: String = DEFAULT_TITLE,
    icon: Image = loadDefaultIcon(),
) : View<G> {
    override val input = Input(this)

    override val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    override val renderer = Renderer(image.graphics as Graphics2D)

    override var updateCount = 0; private set
    override var frameCount = 0; private set

    val frame = Frame(title)
    override val canvas = Canvas()

    private var running = false

    init {
        frame.iconImage = icon
        frame.layout = BorderLayout()
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                running = false
            }
        })

        val size = Dimension(width * scale, height * scale)

        canvas.minimumSize = size
        canvas.preferredSize = size

        canvas.addKeyListener(input)
        canvas.addMouseListener(input)
        canvas.addMouseMotionListener(input)
        canvas.addMouseWheelListener(input)

        frame.add(canvas, BorderLayout.CENTER)
        frame.pack()
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.isFocusable = false

        canvas.createBufferStrategy(2)
    }

    override fun getScreenshot(): BufferedImage {
        val screenshot = BufferedImage(canvas.width, canvas.height, BufferedImage.TYPE_INT_RGB)

        val graphics = screenshot.createGraphics()
        graphics.drawImage(image, 0, 0, null)
        graphics.dispose()

        return screenshot
    }

    override fun open(game:G) {
        frame.isVisible = true

        canvas.requestFocus()

        running = true

        game.init(this)

        run(game)
    }

    override fun close() {
        running = false
    }

    private fun run(game:G) {
        val npu = 1E9 / frameRate

        var then = Time.nanoseconds()
        var delta = 0.0

        var timer = 0.0
        var updates = 0
        var frames = 0

        while (running) {
            val now = Time.nanoseconds()
            val elapsed = (now - then) / npu
            then = now

            delta += elapsed
            timer += elapsed

            var updated = false

            while (delta >= 1) {
                val time = Time(delta, delta / frameRate, Time.seconds())

                update(game, time)

                delta--
                updates++

                updated = true
            }

            if (updated) {
                render(game)

                frames++
            }

            if (timer >= frameRate) {
                updateCount = updates
                frameCount = frames

                updates = 0
                frames = 0

                timer -= frameRate

                frame.title = "$title ($updateCount U, $frameCount F)"
            }
        }

        game.halt(this)

        frame.dispose()
    }

    override fun toString() = """"${javaClass.simpleName}" : {
            |  "width":     $width,
            |  "height":    $height,
            |  "scale":     $scale,
            |  "frameRate": $frameRate,
            |  "title":     $title
            |}""".trimMargin()

    companion object {
        const val DEFAULT_TITLE = "HyperGame"

        private fun loadDefaultIcon() =
            ImageIO.read(Window::class.java.getResourceAsStream("/kakkoiichris/hypergame/img/icon.png"))
    }
}