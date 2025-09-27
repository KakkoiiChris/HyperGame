package kakkoiichris.hypergame.util

class Stack<E> {
    private val elements = ArrayDeque<E>()

    fun isEmpty() =
        elements.isEmpty()

    fun peek() =
        elements.lastOrNull()

    fun push(element: E) {
        elements.addLast(element)
    }

    fun pop() =
        elements.removeLastOrNull()
}