package kakkoiichris.hypergame.util

class Queue<E> {
    private val elements = ArrayDeque<E>()

    fun isEmpty() =
        elements.isEmpty()

    operator fun plusAssign(element: E) {
        elements.addLast(element)
    }

    fun remove() =
        elements.removeFirstOrNull()
}