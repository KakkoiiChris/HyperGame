package kakkoiichris.hypergame.ui.layout

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.ui.Module
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

open class Panel : Module() {
    var layout: Layout = Layout.None

    override fun update(view: View, game: Game, time: Time, input: Input) {
        layout(this, children)

        super.update(view, game, time, input)
    }
}