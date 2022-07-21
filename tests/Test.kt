import kakkoiichris.hypergame.input.Button.LEFT
import kakkoiichris.hypergame.input.Button.RIGHT
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.QuadTree
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.util.math.randomDouble
import kakkoiichris.hypergame.view.Sketch
import kakkoiichris.hypergame.view.View
import java.awt.Color
import java.awt.RenderingHints

fun main() {
    CircleCollisions.open()
}

class Ball(
    override var position: Vector,
    val radius: Double,
    val mass: Double,
    val color: Int,
) : Renderable, QuadTree.Element {
    override val bounds: Box
        get() = Box(position.x - radius, position.y - radius, radius * 2, radius * 2)
    
    var velocity = Vector()
    var acceleration = Vector()
    
    var collided = false
    
    fun distanceTo(other: Ball) =
        position.distanceTo(other.position)
    
    fun reset() {
        velocity.zero()
        acceleration.zero()
    }
    
    operator fun contains(vector: Vector) =
        position.distanceTo(vector) <= radius
    
    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        velocity += acceleration * time.delta
        position += velocity * time.delta
        
        if (position.x < 0) position.x += view.width
        if (position.x >= view.width) position.x -= view.width
        if (position.y < 0) position.y += view.height
        if (position.y >= view.height) position.y -= view.height
        
        if (velocity.magnitude < .01) velocity.zero()
        
        acceleration = -velocity * .01
    }
    
    override fun render(view: View, renderer: Renderer) {
        renderer.push()
        
        renderer.color = Color(color)
        
        renderer.translate(position.x, position.y)
        
        renderer.fillOval(-radius.toInt(), -radius.toInt(), (radius * 2).toInt(), (radius * 2).toInt())
        
        renderer.pop()
    }
}

typealias Collision = Pair<Ball, Ball>

object CircleCollisions : Sketch(900, 900, "Circle Collisions", 144.0) {
    private lateinit var balls: List<Ball>
    
    private val collisions = mutableListOf<Collision>()
    
    private var held: Ball? = null
    
    private var mouse = Vector()
    
    override fun swap(view: View, passed: List<Any>) {
        balls = List(650) {
            val x = randomDouble() * view.width
            val y = randomDouble() * view.height
            val radius = (randomDouble() * 18F) + 2F
            val mass = (randomDouble() * 25.0) + 5.0
            val color = (randomDouble() * 0xFFFFFF).toInt()
            
            Ball(Vector(x, y), radius, mass, color)
        }
        
        view.renderer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    }
    
    override fun update(view: View, manager: StateManager, time: Time, input: Input) {
        if (input.buttonDown(LEFT) || input.buttonDown(RIGHT)) {
            held = null
            
            balls.firstOrNull { input.mousePoint in it }?.apply { reset(); held = this }
        }
        
        if (input.buttonUp(LEFT)) {
            held = null
        }
        
        if (input.buttonHeld(LEFT)) {
            held?.position?.x = input.x.toDouble()
            held?.position?.y = input.y.toDouble()
        }
        
        if (input.buttonUp(RIGHT)) {
            if (held != null) {
                held!!.velocity.x = (held!!.position.x - input.x) * .1F
                held!!.velocity.y = (held!!.position.y - input.y) * .1F
                held = null
            }
        }
        
        mouse.zero()
        mouse += input.mousePoint
        
        val tree = QuadTree<Ball>(view.bounds)
        
        balls.forEach {
            it.update(view, manager, time, input)
            
            tree.insert(it)
        }
        
        val collide = { a: Ball, b: Ball ->
            (a.position.x - b.position.x) * (a.position.x - b.position.x) + (a.position.y - b.position.y) * (a.position.y - b.position.y) <= (a.radius + b.radius) * (a.radius + b.radius)
        }
        
        collisions.clear()
        
        for (a in balls) {
            val near = tree.queryPosition(a.bounds.copy().apply { resize(a.radius * 2) })
            
            for (b in near) {
                if (a === b) continue
                
                if (collide(a, b)) {
                    collisions += a to b
                    
                    val distance = a.distanceTo(b)
                    val overlap = (distance - a.radius - b.radius) * .5F
                    val diff = (a.position - b.position) * overlap / distance
                    
                    a.position -= diff
                    b.position += diff
                }
            }
        }

//        var ia = 0
//
//        while (ia < balls.size - 1) {
//            val a = balls[ia]
//
//            var ib = ia + 1
//
//            while (ib < balls.size) {
//                val b = balls[ib]
//
//                if (collide(a, b)) {
//                    collisions += a to b
//
//                    val distance = a.distanceTo(b)
//                    val overlap = (distance - a.radius - b.radius) * .5F
//                    val diff = (a.position - b.position) * overlap / distance
//
//                    a.position -= diff
//                    b.position += diff
//                }
//
//                ib++
//            }
//
//            ia++
//        }
        
        collisions.forEach { (a, b) ->
            val normal = a.position.normalTo(b.position)
            val tangent = normal.tangent
            
            val dna = a.velocity dot normal
            val dnb = b.velocity dot normal
            val dta = a.velocity dot tangent
            val dtb = b.velocity dot tangent
            
            val ma = (dna * (a.mass - b.mass) + 2F * b.mass * dnb) / (a.mass + b.mass)
            val mb = (dnb * (b.mass - a.mass) + 2F * a.mass * dna) / (a.mass + b.mass)
            
            a.velocity = tangent * dta + normal * ma
            b.velocity = tangent * dtb + normal * mb
            
            a.collided = true
            b.collided = true
        }
        
        balls.filter { it.collided }
            .forEach {
//                bursts += Burst(it.position.copy(), it.radius.toFloat(), it.color)
                
                it.collided = false
            }

//        bursts.forEach { it.update(view, manager, time, input) }

//        bursts.removeIf { it.isDead }
    }
    
    override fun render(view: View, renderer: Renderer) {
        renderer.clearRect(0, 0, view.width, view.height)

//        bursts.forEach { it.render(view, renderer) }
        
        balls.forEach { it.render(view, renderer) }
    }
}