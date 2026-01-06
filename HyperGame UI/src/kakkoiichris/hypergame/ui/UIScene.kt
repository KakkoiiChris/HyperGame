package kakkoiichris.hypergame.ui

import kakkoiichris.hypergame.scene.Scene
import kakkoiichris.hypergame.ui.layout.Panel
import kakkoiichris.hypergame.view.View

abstract class UIScene(view: View) : Scene, Panel() {
    init {
        setBounds(view.bounds)
    }
}