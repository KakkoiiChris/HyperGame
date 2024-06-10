package kakkoiichris.hypergame.play

import java.awt.Rectangle
import java.awt.Shape

/**
 * HyperGame Play
 *
 * Copyright (C) 2023, KakkoiiChris
 *
 * File:    BlockType.kt
 *
 * Created: Monday, June 05, 2023, 22:46:51
 *
 * @author Christian Bryce Alexander
 */
interface Block {
    val spriteIndex: Int
    
    val collider: Collider
    
    operator fun component1() = spriteIndex
    
    operator fun component2() = collider
    
    object Empty : Block {
        override val spriteIndex = -1
        
        override val collider = Collider.NONE
    }
    
    enum class Collider {
        NONE {
            override fun shape(x: Int, y: Int, size: Int) = null
        },
        
        BOX {
            override fun shape(x: Int, y: Int, size: Int) = Rectangle(x, y, size, size)
        };
        
        abstract fun shape(x: Int, y: Int, size: Int): Shape?
    }
}