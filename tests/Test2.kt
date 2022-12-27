import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import kotlin.math.floor

const val TILE_SIZE = 16.0
const val GRID_SIZE = 8.0

fun main() {
    TileCollisions.open()
}

object TileCollisions : Sketch(128, 128, "Tile Collisions") {
    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
    }
    
    override fun render(view: View, renderer: Renderer) {
    }
}

object Area {
    val columns = 8
    
    val map = intArrayOf(
        0, 0, 0, 0, 0, 0, 3, 0,
        0, 0, 5, 5, 0, 0, 0, 4,
        0, 7, 0, 0, 6, 2, 0, 4,
        0, 1, 1, 1, 1, 1, 1, 1,
        7, 0, 0, 0, 0, 0, 0, 0,
        0, 11, 12, 0, 9, 10, 0, 7,
        0, 1, 1, 1, 1, 1, 1, 1,
        12, 0, 14, 14, 14, 0, 8, 0
    )
}

object Player {
    var jumping = true
    var height = TILE_SIZE - 4
    var width = TILE_SIZE - 4
    var x = TILE_SIZE * 4 - TILE_SIZE * 0.5 + 2
    var xOld = TILE_SIZE * 4 - TILE_SIZE * 0.5 + 2
    var xVelocity = 0.0
    var y = TILE_SIZE * 4
    var yOld = TILE_SIZE * 8
    var yVelocity = 0.0
}

enum class Collider(val f: (column: Double, row: Double) -> Unit) {
    NONE({ _, _ -> Unit }),
    FLAT_TOP({ _, row -> collideTop(row) }),
    FLAT_RIGHT({ column, _ -> collideRight(column) }),
    FLAT_BOTTOM({ _, row -> collideBottom(row) }),
    FLAT_LEFT({ column, _ -> collideLeft(column) }),
    BLOCK(fun(column: Double, row: Double) {
        if (collideTop(row)) return
        if (collideLeft(column)) return
        if (collideRight(column)) return
        collideBottom(row)
    }),
    HALF_BLOCK(fun(column: Double, row: Double) {
        if (collideTop(row, TILE_SIZE * 0.5)) return
        
        if (Player.y + Player.height > row * TILE_SIZE + TILE_SIZE * 0.5) {
            if (collideLeft(column)) return
            if (collideRight(column)) return
        }
        
        collideBottom(row)
    }),
    HALF_PLATFORM({ _, row -> collideTop(row, TILE_SIZE * 0.5) }),
    UP_SLOPE_BLOCK(fun(column: Double, row: Double) {
        val currentX = Player.x + Player.width - column * TILE_SIZE
        
        val top = -1 * currentX + TILE_SIZE + row * TILE_SIZE
        
        if (currentX > TILE_SIZE) {
            Player.jumping = false
            Player.yVelocity = 0.0
            Player.y = row * TILE_SIZE - Player.height - offset
        }
        else if (Player.y + Player.height > top) {
            Player.jumping = false
            Player.yVelocity = 0.0
            Player.y = top - Player.height - offset
        }
    }),
    UP_SLOPE_PLATFORM({ column, row -> collideSlopeTop(column, row, -1.0, TILE_SIZE) }),
    DOWN_SLOPE_PLATFORM({ column, row -> collideSlopeTop(column, row, 1.0, 0.0) }),
    ;
    
    operator fun invoke(column: Double, row: Double) = f(column, row)
    
    companion object {
        val offset = 0.001
        
        fun collideBottom(row: Double, yOffset: Double = TILE_SIZE): Boolean {
            val bottom = row * TILE_SIZE + yOffset
            
            if (Player.y < bottom && Player.yOld >= bottom) {
                Player.yVelocity = 0.0
                Player.y = bottom + offset
                
                return true
            }
            
            return false
        }
        
        fun collideLeft(column: Double): Boolean {
            val left = column * TILE_SIZE
            
            if (Player.x + Player.width > left && Player.xOld + Player.width <= left) {
                Player.xVelocity = 0.0
                Player.x = left - Player.width - offset
                return true
            }
            
            return false
        }
        
        fun collideRight(column: Double): Boolean {
            val right = column * TILE_SIZE + TILE_SIZE
            
            if (Player.x < right && Player.xOld >= right) {
                Player.xVelocity = 0.0
                Player.x = right
                return true
            }
            
            return false
        }
        
        fun collideTop(row: Double, yOffset: Double = 0.0): Boolean {
            val top = row * TILE_SIZE + yOffset
            
            if (Player.y + Player.height > top && Player.yOld + Player.height <= top) {
                Player.jumping = false
                Player.yVelocity = 0.0
                Player.y = top - Player.height - offset
                return true
            }
            
            return false
        }
        
        fun collideSlopeTop(column: Double, row: Double, slope: Double, yOffset: Double): Boolean {
            val originX = column * TILE_SIZE
            val originY = row * TILE_SIZE + yOffset
            val currentX = if (slope < 0) Player.x + Player.width - originX else Player.x - originX
            val currentY = Player.y + Player.height - originY
            val oldX = if (slope < 0) Player.xOld + Player.width - originX else Player.xOld - originX
            val oldY = Player.yOld + Player.height - originY
            val currentCrossProduct = currentX * slope - currentY
            val oldCrossProduct = oldX * slope - oldY
            val top = if (slope < 0) row * TILE_SIZE + TILE_SIZE + yOffset * slope else row * TILE_SIZE + yOffset
            
            if ((currentX < 0 || currentX > TILE_SIZE) && (Player.y + Player.height > top && Player.yOld + Player.height <= top || currentCrossProduct < 1 && oldCrossProduct > -1)) {
                Player.jumping = false
                Player.yVelocity = 0.0
                Player.y = top - Player.height - offset
                return true
            }
            else if (currentCrossProduct < 1 && oldCrossProduct > -1) {
                Player.jumping = false
                Player.yVelocity = 0.0
                Player.y = row * TILE_SIZE + slope * currentX + yOffset - Player.height - offset
                return true
            }
            
            return false
        }
        
        fun handleCollision() {
            /* TEST TOP */
            
            var column = floor(Player.x / TILE_SIZE)// The column under the left side of the Player:
            var row = floor(Player.y / TILE_SIZE)// The row under the top side of the Player:
            var value = Area.map[(row * Area.columns + column).toInt()]// We get the tile value under the top left corner of the Player:
            
            if (value != 0) values()[value](column, row)// If it's not a walkable tile, we do narrow phase collision.
            
            column = floor((Player.x + Player.width) / TILE_SIZE)// The column under the right side of the Player:
            value = Area.map[(row * Area.columns + column).toInt()]// Value under the top right corner of the Player.
            
            if (value != 0) values()[value](column, row)
            
            /* TEST BOTTOM */
            
            column = floor(Player.x / TILE_SIZE)// The column under the left side of the Player:
            row = floor((Player.y + Player.height) / TILE_SIZE)// The row under the bottom side of the Player:
            value = Area.map[(row * Area.columns + column).toInt()]
            
            if (value != 0) values()[value](column, row)
            
            column = floor((Player.x + Player.width) / TILE_SIZE)// The column under the right side of the Player:
            value = Area.map[(row * Area.columns + column).toInt()]
            
            if (value != 0) values()[value](column, row)
            
            /* TEST LEFT */
            
            column = floor(Player.x / TILE_SIZE)// The column under the left side of the Player:
            row = floor(Player.y / TILE_SIZE)// Top side row:
            value = Area.map[(row * Area.columns + column).toInt()]
            
            if (value != 0) values()[value](column, row)
            
            row = floor((Player.y + Player.height) / TILE_SIZE)// Bottom side row:
            value = Area.map[(row * Area.columns + column).toInt()]
            
            if (value != 0) values()[value](column, row)
            
            /* TEST RIGHT */
            
            column = floor((Player.x + Player.width) / TILE_SIZE)// The column under the right side of the Player:
            row = floor(Player.y / TILE_SIZE)// Top side row:
            value = Area.map[(row * Area.columns + column).toInt()]
            
            if (value != 0) values()[value](column, row)
            
            row = floor((Player.y + Player.height) / TILE_SIZE)// Bottom side row:
            value = Area.map[(row * Area.columns + column).toInt()]
            
            if (value != 0) values()[value](column, row)
        }
    }
}