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
package kakkoiichris.hypergame.util.math.easing

import kakkoiichris.hypergame.util.math.Vector
import kotlin.math.min

/**
 * Robert Penner's Easing Equations in Kotlin
 *
 * TERMS OF USE - EASING EQUATIONS
 *
 * Open source under the [BSD License](http://www.opensource.org/licenses/bsd-license.php).
 *
 * Copyright © 2001 Robert Penner
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * either the name of the author nor the names of contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
class Path(val ex: Equation, val ey: Equation, var start: Vector, var end: Vector, val duration: Double) {
    enum class Equation(private val calc: (t: Double, b: Double, c: Double, d: Double) -> Double) {
        BACK_IN(Back::easeIn),
        BACK_OUT(Back::easeOut),
        BACK_BOTH(Back::easeInOut),
        BOUNCE_IN(Bounce::easeIn),
        BOUNCE_OUT(Bounce::easeOut),
        BOUNCE_BOTH(Bounce::easeInOut),
        CIRCLE_IN(Circle::easeIn),
        CIRCLE_OUT(Circle::easeOut),
        CIRCLE_BOTH(Circle::easeInOut),
        CUBIC_IN(Cubic::easeIn),
        CUBIC_OUT(Cubic::easeOut),
        CUBIC_BOTH(Cubic::easeInOut),
        ELASTIC_IN(Elastic::easeIn),
        ELASTIC_OUT(Elastic::easeOut),
        ELASTIC_BOTH(Elastic::easeInOut),
        EXPONENTIAL_IN(Expo::easeIn),
        EXPONENTIAL_OUT(Expo::easeOut),
        EXPONENTIAL_BOTH(Expo::easeInOut),
        LINEAR_IN(Linear::easeIn),
        LINEAR_OUT(Linear::easeOut),
        LINEAR_BOTH(Linear::easeInOut),
        QUADRATIC_IN(Quad::easeIn),
        QUADRATIC_OUT(Quad::easeOut),
        QUADRATIC_BOTH(Quad::easeInOut),
        QUARTIC_IN(Quart::easeIn),
        QUARTIC_OUT(Quart::easeOut),
        QUARTIC_BOTH(Quart::easeInOut),
        QUINTIC_IN(Quint::easeIn),
        QUINTIC_OUT(Quint::easeOut),
        QUINTIC_BOTH(Quint::easeInOut),
        SINE_IN(Sine::easeIn),
        SINE_OUT(Sine::easeOut),
        SINE_BOTH(Sine::easeInOut);
        
        operator fun invoke(t: Double, b: Double, c: Double, d: Double) = calc(t, b, c, d)
    }
    
    private var elapsed = 0.0
        get() = min(field, duration)
    
    val hasElapsed get() = elapsed >= duration
    
    fun swapEnds() {
        val temp = start
        start = end
        end = temp
    }
    
    fun reset() {
        elapsed = 0.0
    }
    
    fun update(delta: Double) {
        elapsed += delta
    }
    
    fun getPoint() = Vector(
        ex(elapsed, start.x, end.x - start.x, duration),
        ey(elapsed, start.y, end.y - start.y, duration)
    )
}