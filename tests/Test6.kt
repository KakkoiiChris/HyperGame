import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.BlurComposite
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.state.State
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Display
import kakkoiichris.hypergame.view.View
import java.awt.Color

fun main() {
    val display = Display(300, 300, 3)

    display.manager.push(Test6)

    display.open()
}

object Test6 : State {
    val icon = Sprite.load("/kakkoiichris/hypergame/img/font16.png")

    override fun swapTo(view: View) {
    }

    override fun swapFrom(view: View) {
    }

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.clearRect(view.bounds)

        renderer.drawImage(icon, 0, 0)

        val composite = renderer.composite

        renderer.composite = BlurComposite(3)

        renderer.color = Color(0, 0, 255, 64)

        renderer.fillRoundRect(view.input.x - 50, view.input.y - 50, 100, 100,20,20)

        renderer.composite = composite

        renderer.fillRoundRect(view.input.x - 50, view.input.y - 50, 100, 100, 20, 20)
    }

    override fun halt(view: View) {
    }
}