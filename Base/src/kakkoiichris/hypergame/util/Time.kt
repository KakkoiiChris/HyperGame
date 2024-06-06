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
            System.currentTimeMillis() / 1E3

        fun microseconds() =
            System.currentTimeMillis() / 1E1

        fun milliseconds() =
            System.currentTimeMillis()

        fun nanoseconds() =
            System.nanoTime()
    }
}