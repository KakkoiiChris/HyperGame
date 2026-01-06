import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.BlurComposite
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color

fun main() {
    Blur.open()
}

object Blur : Sketch(300, 300, "Blur") {
    private val icon = Sprite.load("/kakkoiichris/hypergame/img/font16.png")

    override fun update(view: View, time: Time, input: Input) {
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.clearRect(view.bounds)

        renderer.drawImage(icon, 0, 0)

        val composite = renderer.composite

        renderer.composite = BlurComposite(3)

        renderer.color = Color(0, 0, 255, 64)

        renderer.fillRoundRect(view.input.x - 50, view.input.y - 50, 100, 100, 20, 20)

        renderer.composite = composite

        renderer.fillRoundRect(view.input.x - 50, view.input.y - 50, 100, 100, 20, 20)
    }
}