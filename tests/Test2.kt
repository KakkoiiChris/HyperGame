import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.input.Key
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color
import kotlin.math.floor

const val TILE_SIZE = 16
const val GRID_SIZE = 8

fun main() {
    Collisions.open()
}

object Collisions : Sketch(GRID_SIZE * TILE_SIZE, GRID_SIZE * TILE_SIZE, "Tile Based Collision") {
    const val columns = GRID_SIZE
    
    val map = intArrayOf(
        0, 0, 0, 0, 0, 0, 3, 0,
        0, 0, 5, 0, 6, 0, 0, 4,
        0, 7, 0, 0, 0, 2, 0, 4,
        0, 1, 1, 1, 1, 1, 1, 1,
        7, 0, 0, 0, 0, 0, 13, 0,
        0, 11, 12, 9, 10, 0, 0, 7,
        0, 1, 1, 1, 1, 1, 1, 1,
        12, 0, 14, 14, 14, 0, 8, 0
    )
    
    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        if (input.keyDown(Key.W) && !Player.jumping) {
            Player.jumping = true
            Player.y_velocity = -10.0
        }
        
        if (input.keyHeld(Key.A)) {
            Player.x_velocity -= 0.2
        }
        
        if (input.keyHeld(Key.D)) {
            Player.x_velocity += 0.2
        }
        
        Player.x_old = Player.x
        Player.y_old = Player.y
        
        Player.y_velocity += 1
        
        Player.x += Player.x_velocity * time.delta
        Player.y += Player.y_velocity * time.delta
        
        Player.x_velocity *= 0.9
        Player.y_velocity *= 0.9
        
        if (Player.y < 0) {
            Player.y_velocity = 0.0
            Player.y = 0.0
        }
        else if (Player.y + Player.height > view.height) {
            Player.jumping = false
            Player.y_velocity = 0.0
            Player.y = view.height - Player.height - 0.001
        }
        
        if (Player.x < 0) {
            Player.x_velocity = 0.0
            Player.x = 0.0
        }
        else if (Player.x + Player.width > view.width) {
            Player.x_velocity = 0.0
            Player.x = view.width - Player.width - 0.001
        }
        
        Collider.handleCollision()
    }
    
    override fun render(view: View, renderer: Renderer) {
        renderer.clearRect(0, 0, view.width, view.height)
        renderer.color = Color.WHITE
        renderer.fillRect(Player.x.toInt(), Player.y.toInt(), Player.width, Player.height)
    }
    
    override fun halt(view: View) {
    }
}

object Player {
    var jumping = true
    var height = TILE_SIZE - 4
    var width = TILE_SIZE - 4
    var x = TILE_SIZE * 4 - TILE_SIZE * 0.5 + 2
    var x_old = TILE_SIZE * 4 - TILE_SIZE * 0.5 + 2
    var x_velocity = 0.0
    var y = TILE_SIZE * 4.0
    var y_old = TILE_SIZE * 8.0
    var y_velocity = 0.0
}

enum class Collider(val f: (c: Int, r: Int) -> Unit) {
    NONE({ c, r -> Unit });//,
//    FLAT_TOP({ o, c, r -> collideTop(o, r) }),
//    FLAT_RIGHT({ o, c, r -> collideRight(o, c) }),
//    FLAT_BOTTOM({ o, c, r -> collideBottom(o, r) }),
//    FLAT_LEFT({ o, c, r -> collideLeft(o, c) }),
//    BLOCK(fun(o: Any, c: Int, r: Int) {
//        if (collideTop(o, r)) return
//        if (collideRight(o, c)) return
//        if (collideBottom(o, r)) return
//        collideLeft(o, c)
//    }),
//    HALF_BLOCK({ o, c, r -> Unit }),
//    HALF_FLAT({ o, c, r -> collideTop(o, r, TILE_SIZE * 0.5) });
    
    operator fun invoke(col: Int, row: Int) = f(col, row)
    
    companion object {
        var offset = 0.001
        
        fun handleCollision() {
            var col = floor(Player.x / TILE_SIZE).toInt()
            var row = floor(Player.y / TILE_SIZE).toInt()
            var value = Collisions.map[row * Collisions.columns + col]
            
            if (value != 0) values()[value](col, row)
            
            col = floor((Player.x + Player.width) / TILE_SIZE).toInt()
            value = Collisions.map[row * Collisions.columns + col]
            
            if (value != 0) values()[value](col, row)
            
            col = floor(Player.x / TILE_SIZE).toInt()
            row = floor((Player.y + Player.height) / TILE_SIZE).toInt()
            value = Collisions.map[row * Collisions.columns + col]
            
            if (value != 0) values()[value](col, row)
            
            col = floor((Player.x + Player.width) / TILE_SIZE).toInt()
            value = Collisions.map[row * Collisions.columns + col]
            
            if (value != 0) values()[value](col, row)
        }
    }
}