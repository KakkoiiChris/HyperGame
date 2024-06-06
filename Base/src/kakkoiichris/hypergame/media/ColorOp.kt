package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.util.math.clamp

data class ColorOp(var a: Double = 0.0, var r: Double = 0.0, var g: Double = 0.0, var b: Double = 0.0) {
    val value: Int
        get() = ((a * 255).toInt() shl 24) or ((r * 255).toInt() shl 16) or ((g * 255).toInt() shl 8) or (b * 255).toInt()

    fun blend(src: ColorOp) {
        val (sa, sr, sg, sb) = src

        r = (sr * sa) + (r * (1.0 - sa))
        g = (sg * sa) + (g * (1.0 - sa))
        b = (sb * sa) + (b * (1.0 - sa))
    }

    fun clamp() {
        a = a.clamp(0.0, 1.0)
        r = r.clamp(0.0, 1.0)
        g = g.clamp(0.0, 1.0)
        b = b.clamp(0.0, 1.0)
    }

    fun map(transform: (Double) -> Double) {
        r = transform(r)
        g = transform(g)
        b = transform(b)
    }

    operator fun times(that: Double) =
        ColorOp(a * that, r * that, g * that, b * that)

    operator fun times(that: ColorOp) =
        ColorOp(a * that.a, r * that.r, g * that.g, b * that.b)

    operator fun plusAssign(that: ColorOp) {
        a += that.a
        r += that.r
        g += that.g
        b += that.b
    }

    operator fun divAssign(s: Int) {
        a /= s
        r /= s
        g /= s
        b /= s
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