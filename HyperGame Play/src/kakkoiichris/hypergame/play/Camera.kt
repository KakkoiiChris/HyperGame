package kakkoiichris.hypergame.play

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.media.Renderer
import kakkoiichris.hypergame.state.StateManager
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.Vector
import kakkoiichris.hypergame.util.math.clamp
import kakkoiichris.hypergame.view.View

/**
 * HyperGame Play
 *
 * Copyright (C) 2023, KakkoiiChris
 *
 * File:    Camera.kt
 *
 * Created: Tuesday, June 06, 2023, 20:33:11
 *
 * @author Christian Bryce Alexander
 */
open class Camera(private val level: Level, bounds: Box) : Box(bounds), Renderable {
    var target: Entity? = null
    
    var velocity: Vector? = Vector(1.0, 0.0)
    
    var followHorizontal = true
    var followVertical = true
    var followStrength = 0.1
        set(value) {
            field = value.clamp(0.0, 1.0)
        }
    
    var follow: Boolean
        get() = followHorizontal && followVertical
        set(value) {
            followHorizontal = value
            followVertical = value
        }
    
    var lockHorizontal = false
    var lockVertical = false
    var lockToEdges = true
    
    var lock: Boolean
        get() = lockHorizontal && lockVertical
        set(value) {
            lockHorizontal = value
            lockVertical = value
        }
    
    override fun update(view: View, game: Game, time: Time, input: Input) {
        val original = center
        
        if (target != null) {
            center += (target!!.center - center) * followStrength * time.delta
            
            if (lockHorizontal) {
                centerX = original.x
            }
            
            if (lockVertical) {
                centerY = original.y
            }
        }
        
        if (velocity != null) {
            center += velocity!! * time.delta
        }
        
        if (lockToEdges) {
            x = x.clamp(0.0, level.width - width)
            y = y.clamp(0.0, level.height - height)
        }
    }
    
    override fun render(view: View, game: Game, renderer: Renderer) {
    }
}