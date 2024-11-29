import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.BlendMode
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.media.filter.BlendFilter
import kakkoiichris.hypergame.media.filter.MaskFilter
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color
import kotlin.math.PI
import kotlin.math.sin

fun main() {
    MaskFilterTest.open()
}

object MaskFilterTest : Sketch(512, 512, "Cloud Mask") {
    val maskSprite = Sprite.load("/cubeMask.png")

    val mask = MaskFilter(maskSprite, 0.0, 1.0, true)
    val blend = BlendFilter(maskSprite, BlendMode.MULTIPLY)

    val sprite = Sprite.load("/furina.png")
    val back = Sprite.load("/bricks.png")

    lateinit var draw: Sprite

    var theta = PI/2.0

    init {
        back.filter(blend)
    }

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        draw = sprite.copy()
        draw.filter(mask)

        mask.highLimit = (sin(theta) + 1.01) / 2.0

        theta += 0.005 * time.delta
    }

    override fun render(view: View, renderer: Renderer) {
        renderer.color= Color(0,0,255,20)
        renderer.fillRect(view.bounds)
        renderer.drawImage(back, view.bounds)
        renderer.drawImage(draw, view.bounds)
    }
}