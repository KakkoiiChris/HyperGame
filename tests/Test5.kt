import kakkoiichris.hypergame.util.filesystem.ResourceManager
import kakkoiichris.hypergame.util.math.wrap

fun main() {
    //val manager = ResourceManager("/kakkoiichris/hypergame")
    
    for (i in 0 until 100) {
        println(i.wrap(5, 23))
    }
}