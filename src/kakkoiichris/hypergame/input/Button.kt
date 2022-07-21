package kakkoiichris.hypergame.input

import java.awt.event.MouseEvent

enum class Button(val code: Int) {
    LEFT(MouseEvent.BUTTON1),
    MIDDLE(MouseEvent.BUTTON2),
    RIGHT(MouseEvent.BUTTON3)
}