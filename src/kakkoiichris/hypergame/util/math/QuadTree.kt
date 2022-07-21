package kakkoiichris.hypergame.util.math

import kakkoiichris.hypergame.media.Renderer

class QuadTree<X : QuadTree.Element>(val bounds: Box, val capacity: Int = 4) {
    private val elements = mutableListOf<X>()
    
    private lateinit var quadrants: Array<QuadTree<X>>
    
    private var divided = false
    
    fun insert(element: X): Boolean {
        if (element.position !in bounds) {
            return false
        }
        
        if (elements.size < capacity) {
            elements += element
            
            return true
        }
        
        if (!divided) divide()
        
        quadrants.firstOrNull { it.insert(element) }
        
        return true
    }
    
    fun queryPosition(box: Box): List<X> {
        val list = mutableListOf<X>()
        
        if (bounds.intersects(box)) {
            list.addAll(elements.filter { it.position in box })
            
            if (divided) {
                list.addAll(quadrants.flatMap { it.queryPosition(box) })
            }
        }
        
        return list
    }
    
    fun queryBounds(box: Box): List<X> {
        val list = mutableListOf<X>()
        
        if (bounds.intersects(box)) {
            list.addAll(elements.filter { it.bounds.intersects(box) })
            
            if (divided) {
                list.addAll(quadrants.flatMap { it.queryBounds(box) })
            }
        }
        
        return list
    }
    
    private fun divide() {
        quadrants = arrayOf(
            QuadTree(Box(bounds.centerX, bounds.y, bounds.width / 2, bounds.height / 2), capacity),
            QuadTree(Box(bounds.x, bounds.y, bounds.width / 2, bounds.height / 2), capacity),
            QuadTree(Box(bounds.x, bounds.centerY, bounds.width / 2, bounds.height / 2), capacity),
            QuadTree(Box(bounds.centerX, bounds.centerY, bounds.width / 2, bounds.height / 2), capacity),
        )
        
        divided = true
    }
    
    fun render(renderer: Renderer) {
        renderer.drawRect(bounds)
        
        if (divided) {
            quadrants.forEach { it.render(renderer) }
        }
    }
    
    interface Element {
        val position: Vector
        
        val bounds: Box
    }
}