/**************************************************************
 * ██  ██ ▄▄ ▄▄ ▄▄▄▄  ▄▄▄▄▄ ▄▄▄▄   ▄████   ▄▄▄  ▄▄   ▄▄ ▄▄▄▄▄ *
 * ██████ ▀███▀ ██▄█▀ ██▄▄  ██▄█▄ ██  ▄▄▄ ██▀██ ██▀▄▀██ ██▄▄  *
 * ██  ██   █   ██    ██▄▄▄ ██ ██  ▀███▀  ██▀██ ██   ██ ██▄▄▄ *
 *             Kotlin 2D Game Development Library             *
 *              Copyright (C) 2021, KakkoiiChris              *
 **************************************************************/
package kakkoiichris.hypergame.util

data class Time(val delta: Double, val seconds: Double, val instant: Double) {
    operator fun times(scale: Double) =
        Time(delta * scale, seconds * scale, instant * scale)

    operator fun times(scale: Int) =
        Time(delta * scale, seconds * scale, instant * scale)

    operator fun div(scale: Double) =
        Time(delta / scale, seconds / scale, instant / scale)

    operator fun div(scale: Int) =
        Time(delta / scale, seconds / scale, instant / scale)

    companion object {
        fun seconds() =
            System.nanoTime() / 1E9

        fun microseconds() =
            System.nanoTime() / 1E7

        fun milliseconds() =
            System.currentTimeMillis()

        fun nanoseconds() =
            System.nanoTime()

        fun count(action: () -> Unit): Double {
            val start = seconds()

            action()

            return seconds() - start
        }
    }
}