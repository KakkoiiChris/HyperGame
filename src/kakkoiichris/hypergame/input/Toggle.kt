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
package kakkoiichris.hypergame.input

import kakkoiichris.hypergame.util.Time

class Toggle(val id: ID) {
    private var now = false
    private var then = false

    private var multiPressTimer = 0.0

    var pressCount = 0; private set

    val isDown get() = now && !then

    val isHeld get() = now

    val isUp get() = !now && then

    fun set(on: Boolean) {
        now = on

        if (on) {
            pressCount++
        }

        multiPressTimer = 0.0
    }

    fun poll(time: Time) {
        then = now

        multiPressTimer += time.seconds

        if (multiPressTimer >= MULTI_PRESS_THRESHOLD_SECONDS) {
            pressCount = 0
        }
    }

    companion object {
        private const val MULTI_PRESS_THRESHOLD_SECONDS = 1.0 / 8.0
    }

    interface ID {
        val code: Int
    }
}