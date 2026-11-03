import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color
import java.awt.Font
import kotlin.math.cos
import kotlin.math.sin

fun main() {
    DrawLines.open()
}

object DrawLines : Sketch(640, 480, "Draw Lines") {
    private val box = Box(100.0, 100.0, 400.0, 300.0)

    var theta = 0.0
    var alignX = 0.5
    var alignY = 0.5

    override fun update(view: View, time: Time, input: Input) {
        //if(input.mouse in box) {
        //    alignX = (input.mouse.x - box.x) / box.width
        //    alignY = (input.mouse.y - box.y) / box.height
        //}

        theta += time.delta * 0.01

        alignX = (cos(theta)+1)/2
        alignY = (sin(theta*2)+1)/2
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.clearRect(view.bounds)

        renderer.color = Color.white

        renderer.drawRect(box)

        renderer.font = Font("Comic Sans MS", Font.PLAIN, 20)

        renderer.drawStringLines(
            "Figure 8\n Figure 8 \n  Figure 8  \n    Figure 8    \n        Figure 8        \n    Figure 8    \n  Figure 8  \n Figure 8 \nFigure 8",
            box, alignX, alignY
        )
    }
}