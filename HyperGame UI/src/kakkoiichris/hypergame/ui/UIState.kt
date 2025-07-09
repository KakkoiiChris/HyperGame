package kakkoiichris.hypergame.ui

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.state.State
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.ui.layout.Panel
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.View

abstract class UIState(view: View) : State, Panel() {
    init {
        setBounds(view.bounds)
    }
}