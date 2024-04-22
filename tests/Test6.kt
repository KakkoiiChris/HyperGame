import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.input.Key
import kakkoiichris.hypergame.media.BlendComposite
import kakkoiichris.hypergame.media.BlendMode
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View

fun main() {
    Test6.open()
}

object Test6 : Sketch(600, 600, "Blend Modes") {
    val icon = Sprite.load("/kakkoiichris/hypergame/img/icon.png")

    var show = false

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        show = input.keyHeld(Key.SPACE)
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.clearRect(view.bounds)

        renderer.drawImage(icon, 0,0)

        if (show) {
            val composite = renderer.composite

            renderer.composite = BlendComposite(BlendMode.SOFT_LIGHT)

            renderer.drawImage(icon, 50,50)

            renderer.composite = composite
        }
    }
}