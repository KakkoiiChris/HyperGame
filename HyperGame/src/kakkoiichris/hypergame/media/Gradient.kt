package kakkoiichris.hypergame.media

import kotlin.math.floor

class Gradient(vararg val stops: Int) {
    private val stopLength = 1.0 / stops.lastIndex

    fun sample(value: Double): Int {
        val valueRatio = value / stopLength

        val stopIndex = floor(valueRatio).toInt()

        if (stopIndex == stops.lastIndex) {
            return stops.last()
        }

        val stopFraction = valueRatio % 1

        return lerp(stops[stopIndex], stops[stopIndex + 1], stopFraction)
    }

    private fun lerp(pointA: Int, pointB: Int, normalValue: Double): Int {
        val colorA = ColorOp.of(pointA)
        val colorB = ColorOp.of(pointB)

        return (colorA + (colorB - colorA) * normalValue).value
    }
}