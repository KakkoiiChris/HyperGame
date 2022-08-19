package kakkoiichris.hypergame.media.filter

import kakkoiichris.hypergame.util.Time

interface DynamicFilter : Filter {
    fun update(time: Time)
}