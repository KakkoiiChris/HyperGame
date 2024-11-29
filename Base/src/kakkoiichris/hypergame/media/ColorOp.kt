package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.util.math.clamp

data class ColorOp(
    var alpha: Double = 0.0,
    var red: Double = 0.0,
    var green: Double = 0.0,
    var blue: Double = 0.0
) {
    val value: Int
        get() = ((alpha * 255).toInt() shl 24) or ((red * 255).toInt() shl 16) or ((green * 255).toInt() shl 8) or (blue * 255).toInt()

    val average = (red + green + blue) / 3.0

    fun blend(src: ColorOp) {
        val (sa, sr, sg, sb) = src

        red = (sr * sa) + (red * (1.0 - sa))
        green = (sg * sa) + (green * (1.0 - sa))
        blue = (sb * sa) + (blue * (1.0 - sa))
    }

    fun clamp(min: Double = 0.0, max: Double = 1.0) {
        alpha = alpha.clamp(min, max)
        red = red.clamp(min, max)
        green = green.clamp(min, max)
        blue = blue.clamp(min, max)
    }

    fun map(transform: (Double) -> Double) {
        red = transform(red)
        green = transform(green)
        blue = transform(blue)
    }

    fun mapWith(other: ColorOp, transform: (Double, Double) -> Double) {
        red = transform(red, other.red)
        green = transform(green, other.green)
        blue = transform(blue, other.blue)
    }

    operator fun times(that: Double) =
        ColorOp(alpha * that, red * that, green * that, blue * that)

    operator fun times(that: ColorOp) =
        ColorOp(alpha * that.alpha, red * that.red, green * that.green, blue * that.blue)

    operator fun plusAssign(that: ColorOp) {
        alpha += that.alpha
        red += that.red
        green += that.green
        blue += that.blue
    }

    operator fun divAssign(s: Int) {
        alpha /= s
        red /= s
        green /= s
        blue /= s
    }

    companion object {
        fun of(argb: Int): ColorOp {
            val a = ((argb shr 24) and 0xFF) / 255.0
            val r = ((argb shr 16) and 0xFF) / 255.0
            val g = ((argb shr 8) and 0xFF) / 255.0
            val b = (argb and 0xFF) / 255.0

            return ColorOp(a, r, g, b)
        }
    }
}