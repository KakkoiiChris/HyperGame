/***************************************************************************
 *   ___ ___                                ________                       *
 *  /   |   \ ___.__.______   ___________  /  _____/_____    _____   ____  *
 * /    ~    <   |  |\____ \_/ __ \_  __ \/   \  ___\__  \  /     \_/ __ \ *
 * \    Y    /\___  ||  |_> >  ___/|  | \/\    \_\  \/ __ \|  Y Y  \  ___/ *
 *  \___|_  / / ____||   __/ \___  >__|    \______  (____  /__|_|  /\___  >*
 *        \/  \/     |__|        \/               \/     \/      \/     \/ *
 *                    Kotlin 2D Game Development Library                   *
 *                     Copyright (C) 2021, KakkoiiChris                    *
 ***************************************************************************/
package kakkoiichris.hypergame.util.math

import kakkoiichris.hypergame.media.Renderer

class QuadTree<E : QuadTree.Element>(val bounds: Box, val capacity: Int = 4) {
    private val elements = mutableListOf<E>()

    private lateinit var quadrants: Array<QuadTree<E>>

    private var divided = false

    fun insert(element: E): Boolean {
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

    fun queryPosition(box: Box): List<E> {
        val list = mutableListOf<E>()

        if (bounds.intersects(box)) {
            list.addAll(elements.filter { it.position in box })

            if (divided) {
                list.addAll(quadrants.flatMap { it.queryPosition(box) })
            }
        }

        return list
    }

    fun queryBounds(box: Box): List<E> {
        val list = mutableListOf<E>()

        if (bounds.intersects(box)) {
            list.addAll(elements.filter { it.bounds.intersects(box) })

            if (divided) {
                list.addAll(quadrants.flatMap { it.queryBounds(box) })
            }
        }

        return list
    }

    private fun divide() {
        val (a, b, c, d) = bounds.divide(2, 2)

        quadrants = arrayOf(
            QuadTree(b, capacity),
            QuadTree(a, capacity),
            QuadTree(c, capacity),
            QuadTree(d, capacity),
        )

        divided = true
    }

    fun render(renderer: Renderer) {
        renderer.drawRect(bounds)

        if (divided) {
            quadrants.forEach {
                it.render(renderer)
            }
        }
    }

    interface Element {
        val position: Vector

        val bounds: Box
    }
}