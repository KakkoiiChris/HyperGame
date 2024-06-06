package kakkoiichris.hypergame.ui.form

import kakkoiichris.hypergame.input.Button
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.ui.Module
import kakkoiichris.hypergame.ui.form.Button.Event
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View
import java.awt.BasicStroke
import java.awt.Font

class TextBox(var text: String = "") : Module() {
    val chars = text.toCharArray().toMutableList()

    var font = Font("Monospaced", Font.PLAIN, 15)

    var onChange: (Event) -> Unit = {}

    var prompt = "Text Here..."

    private var hover = false
    private var pressed = false

    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        super.update(view, manager, time, input)

        if (!enabled) {
            hover = false
            focused = false

            return
        }

        hover = input.mouse in this

        val lastPressed = pressed

        if (hover && input.buttonDown(Button.LEFT)) {
            pressed = true
        }

        if (!hover || input.buttonUp(Button.LEFT)) {
            pressed = false
        }

        if (pressed && !lastPressed) {
            getRoot().defocusTree()

            focused = true
        }

        if (focused && input.anyKeyDown()) {
            when (val char = input.getTypedChar()) {
                '\b' -> text = text.substring(0, text.length - 1)

                '\n' -> focused = false

                else -> text += char ?: return
            }
        }
    }

    override fun render(view: View, renderer: Renderer) {
        super.render(view, renderer)

        renderer.color = when {
            pressed          -> background.darker()

            hover || focused -> background.brighter()

            else             -> background
        }

        renderer.fillRoundRect(this, cornerRadius, cornerRadius)

        renderer.color = accent
        renderer.stroke = stroke

        renderer.drawRoundRect(this, cornerRadius, cornerRadius)

        renderer.stroke = BasicStroke(1F)

        val (width,height) = renderer.getStringSize(text)

        renderer.push()
        renderer.translate((x + paddingLeft.toInt()).toInt(), (y + ((view.height - height) / 2)).toInt())

        renderer.drawString(text, 0, 0)
        renderer.drawLine(width.toInt(), 0, width.toInt(), -height.toInt())

        renderer.pop()

        renderer.color = if (text.isEmpty()) foreground.brighter() else foreground
        renderer.font = font

        val displayText = text.takeIf { it.isNotEmpty() } ?: prompt

        renderer.drawString(displayText, this, xAlign = 0.0)
    }
}