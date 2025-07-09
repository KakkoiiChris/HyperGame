package kakkoiichris.hypergame.ui

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.tween
import kakkoiichris.hypergame.view.View

class SuperMenu : Module() {
    lateinit var selectedMenu: Menu
    var open = false
    var targetHeight = height

    override fun add(module: Module, option: UInt) {
        super.add(module, option)

        children.forEach { it.height = height / children.size }
    }

    override fun update(view: View, game: Game, time: Time, input: Input) {
        super.update(view, game, time, input)

        for (child in children) {
            if (input.mouse in child && child is Menu) {
                selectedMenu = child

                open = true
            }
        }

        targetHeight = if (open) {
            selectedMenu.height + selectedMenu.children.sumOf { it.height }
        } else {
            children.sumOf { it.height }
        }

        height = height.tween(targetHeight, 0.9, 0.001)
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        super.render(view, game, renderer)
    }

    class Menu : Module() {

    }
}