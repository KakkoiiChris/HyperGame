package kakkoiichris.hypergame.view

import kakkoiichris.hypergame.state.State

abstract class Sketch(width: Int, height: Int, title: String, frameRate:Double=60.0) : State {
    private val display = Display(width, height, frameRate = frameRate, title = title)
    
    fun open() {
        display.manager += this
        
        display.open()
    }
    
    fun close() =
        display.close()
    
    override val name get() = display.title
    
    override fun swap(view: View, passed: List<Any>) = Unit
    
    override fun halt(view: View) = Unit
}