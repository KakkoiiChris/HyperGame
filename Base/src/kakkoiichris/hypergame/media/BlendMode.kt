package kakkoiichris.hypergame.media

import java.lang.Math.clamp
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round

enum class BlendMode(private val operation: (src: Double, dst: Double) -> Double) {
    /**
     * No blending occurs. Blend color is written directly to image.
     */
    NORMAL({ src, _ -> src }),

    /**
     * The Photoshop Darken algorithm.
     */
    DARKEN(::min),

    /**
     * The Photoshop Multiply algorithm.
     */
    MULTIPLY({ src, dst -> src * dst }),

    /**
     * The Photoshop Color Burn algorithm.
     */
    COLOR_BURN({ src, dst -> clamp(1.0 - (1.0 - dst) / src, 0.0, 1.0) }),

    /**
     * The Photoshop Linear Burn algorithm.
     */
    LINEAR_BURN({ src, dst -> clamp(dst + src - 1.0, 0.0, 1.0) }),

    /**
     * The Photoshop Lighten algorithm.
     */
    LIGHTEN(::max),

    /**
     * The Photoshop Screen algorithm.
     */
    SCREEN({ src, dst -> 1.0 - (1.0 - dst) * (1.0 - src) }),

    /**
     * The Photoshop Color Dodge algorithm.
     */
    COLOR_DODGE({ src, dst -> clamp(dst / (1.0 - src), 0.0, 1.0) }),

    /**
     * The Photoshop Linear Dodge algorithm.
     */
    LINEAR_DODGE({ src, dst -> clamp(dst + src, 0.0, 1.0) }),

    /**
     * The Photoshop Overlay algorithm.
     */
    OVERLAY({ src, dst -> round(dst) * (1.0 - (1.0 - 2.0 * (dst - 0.5)) * (1.0 - src)) + round(dst) * (2.0 * dst * src) }),

    /**
     * The Photoshop Soft Light algorithm.
     */
    SOFT_LIGHT({ src, dst -> round(src) * (1.0 - (1.0 - dst) * (1.0 - (src - 0.5))) + round(src) * (dst * (src + 0.5)) }),

    /**
     * The Photoshop Hard Light algorithm.
     */
    HARD_LIGHT({ src, dst -> round(src) * (1.0 - (1.0 - dst) * (1.0 - 2.0 * (src - 0.5))) + round(src) * (dst * (2.0 * src)) }),

    /**
     * The Photoshop Vivid Light algorithm.
     */
    VIVID_LIGHT({ src, dst -> round(src) * (1.0 - (1.0 - dst) / (2.0 * (src - 0.5))) + round(src) * (dst / (1.0 - 2.0 * src)) }),

    /**
     * The Photoshop Linear Light algorithm.
     */
    LINEAR_LIGHT({ src, dst ->
                     clamp(
                         round(src) * (dst + 2.0 * (src - 0.5)) + round(src) * (dst + 2.0 * src - 1.0),
                         0.0,
                         1.0
                     )
                 }),

    /**
     * The Photoshop Pin Light algorithm.
     */
    PIN_LIGHT({ src, dst -> round(src) * max(dst, 2.0 * (src - 0.5)) + round(src) * min(dst, 2.0 * src) }),

    /**
     * The Photoshop Difference algorithm.
     */
    DIFFERENCE({ src, dst -> abs(dst - src) }),

    /**
     * The Photoshop Exclusion algorithm.
     */
    EXCLUSION({ src, dst -> 0.5 - 2.0 * (dst - 0.5) * (src - 0.5) });

    operator fun invoke(src: Double, dst: Double) =
        operation(src, dst)
}