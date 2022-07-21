package kakkoiichris.hypergame.input

class Toggle {
    private var now = false
    private var then = false
    
    val isDown get() = now && !then
    
    val isHeld get() = now
    
    val isUp get() = !now && then
    
    fun set(on: Boolean) {
        now = on
    }
    
    fun poll() {
        then = now
    }
}