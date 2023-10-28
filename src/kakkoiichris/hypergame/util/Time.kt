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

data class Time(val delta: Double, val seconds: Double) {
    companion object {
        fun seconds() =
            System.currentTimeMillis() / 1000.0

        fun milliseconds() =
            System.currentTimeMillis()

        fun nanoseconds() =
            System.nanoTime()
    }

    operator fun times(scale: Double) =
        Time(delta * scale, seconds * scale)

    operator fun times(scale: Int) =
        Time(delta * scale, seconds * scale)

    operator fun div(scale: Double) =
        Time(delta / scale, seconds / scale)

    operator fun div(scale: Int) =
        Time(delta / scale, seconds / scale)
}