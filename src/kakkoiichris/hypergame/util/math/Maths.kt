package kakkoiichris.hypergame.util.math

import java.util.*
import kotlin.math.abs

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 1/31/2018, 19:04
 */
private val random = Random()

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

fun max(vararg values: Double): Double {
    if (values.size == 1)
        return values[0]
    
    if (values.size == 2)
        return kotlin.math.max(values[0], values[1])
    
    return values.drop(1).maxOrNull() ?: values[0]
}

fun max(vararg values: Int): Int {
    if (values.size == 1)
        return values[0]
    
    if (values.size == 2)
        return kotlin.math.max(values[0], values[1])
    
    return values.drop(1).maxOrNull() ?: values[0]
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

fun min(vararg values: Double): Double {
    if (values.size == 1)
        return values[0]
    
    if (values.size == 2)
        return kotlin.math.min(values[0], values[1])
    
    return values.drop(1).minOrNull() ?: values[0]
}

fun min(vararg values: Int): Int {
    if (values.size == 1)
        return values[0]
    
    if (values.size == 2)
        return kotlin.math.min(values[0], values[1])
    
    return values.drop(1).minOrNull() ?: values[0]
}

infix fun Double.sameSignAs(other: Double) =
    this < 0 == other < 0

infix fun Int.sameSignAs(other: Int) =
    this < 0 == other < 0

fun randomBoolean() = random.nextBoolean()

fun randomDouble() = random.nextDouble()

fun randomFloat() = random.nextFloat()

fun randomGaussian() = random.nextGaussian()

fun randomInt() = random.nextInt()

fun randomInt(bound: Int) = random.nextInt(bound)

fun randomInt(min: Int, max: Int): Int = when {
    min <= 0 && max >= 0 -> random.nextInt(abs(min) + abs(max) + 1) + min
    
    min <= 0 && max <= 0 -> -(random.nextInt(-min - -max + 1) + -max)
    
    min >= 0 && max >= 0 -> random.nextInt(max - min + 1) + min
    
    else                 -> randomInt(max, min)
}

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

fun Double.wrap(min: Double, max: Double): Double {
    var v = this
    
    val range = max - min + 1
    
    while (v < min)
        v += range
    
    while (v > max)
        v -= range
    
    return v
}

fun Int.wrap(min: Int, max: Int): Int {
    var v = this
    
    val range = max - min + 1
    
    while (v < min)
        v += range
    
    while (v > max)
        v -= range
    
    return v
}

fun Double.map(fromMin: Double, fromMax: Double, toMin: Double, toMax: Double) =
    (this - fromMin) * (toMax - toMin) / (fromMax - fromMin) + toMin