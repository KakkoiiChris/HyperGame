package kakkoiichris.hypergame.util

data class Time(val delta: Double, val seconds: Double) {
    companion object {
        fun milliseconds() =
            System.currentTimeMillis()
        
        fun nanoseconds() =
            System.nanoTime()
    }
    
    operator fun times(scale: Double) =
        Time(delta * scale, seconds * scale)
}