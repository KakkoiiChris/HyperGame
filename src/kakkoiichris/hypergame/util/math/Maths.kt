package kakkoiichris.hypergame.util.math

import java.util.*
import kotlin.math.abs

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 1/31/2018, 19:04
 */
fun avg(vararg values: Double) = values.average()

fun avg(vararg values: Float) = values.average()

fun avg(vararg values: Int) = values.average()

fun Double.clamp(min: Double, max: Double) = when {
    this < min -> min

    this > max -> max

    else       -> this
}

fun Float.clamp(min: Float, max: Float) = when {
    this < min -> min

    this > max -> max

    else       -> this
}

fun Int.clamp(min: Int, max: Int) = when {
    this < min -> min

    this > max -> max

    else       -> this
}

fun max(vararg values: Double) = when (values.size) {
    1    -> values[0]
    
    2    -> kotlin.math.max(values[0], values[1])
    
    else -> values.drop(1).maxOrNull() ?: values[0]
}

fun max(vararg values: Int) = when (values.size) {
    1    -> values[0]
    
    2    -> kotlin.math.max(values[0], values[1])
    
    else -> values.drop(1).maxOrNull() ?: values[0]
}

fun med(vararg values: Double): Double {
    values.sort()
    
    return if (values.size % 2 == 0)
        (values[values.size / 2 - 1] + values[values.size / 2]) / 2.0
    else
        values[values.size / 2]
}

fun med(vararg values: Int): Double {
    values.sort()
    
    return if (values.size % 2 == 0)
        (values[values.size / 2 - 1] + values[values.size / 2]) / 2.0
    else
        values[values.size / 2].toDouble()
}

fun min(vararg values: Double) = when (values.size) {
    1    -> values[0]
    
    2    -> kotlin.math.min(values[0], values[1])
    
    else -> values.drop(1).minOrNull() ?: values[0]
}

fun min(vararg values: Int) = when (values.size) {
    1    -> values[0]
    
    2    -> kotlin.math.min(values[0], values[1])
    
    else -> values.drop(1).minOrNull() ?: values[0]
}

infix fun Double.sameSignAs(other: Double) =
    this < 0 == other < 0

infix fun Int.sameSignAs(other: Int) =
    this < 0 == other < 0

fun range(vararg values: Double) =
    max(*values) - min(*values)

fun range(vararg values: Int) =
    max(*values) - min(*values)

fun Double.tween(target: Double, amount: Double) =
    this + (target - this) * amount

fun Double.tween(target: Double, amount: Double, threshold: Double) =
    if (abs(target - this) < threshold) {
        target
    }
    else {
        this + (target - this) * amount
    }

fun Double.wrap(min: Double, max: Double) =
    (((this - min) + (max - min + 1)) % (max - min + 1)) + min

fun Int.wrap(min: Int, max: Int) =
    (((this - min) + (max - min + 1)) % (max - min + 1)) + min

fun Double.map(fromMin: Double, fromMax: Double, toMin: Double, toMax: Double) =
    (this - fromMin) * (toMax - toMin) / (fromMax - fromMin) + toMin