package kakkoiichris.hypergame.media

import kakkoiichris.hypergame.util.math.*

enum class GrayscaleMode(private val calc: (r: Int, g: Int, b: Int) -> Int) {
    AVERAGE({ r, g, b -> avg(r, g, b).toInt() }),
    RED({ r, _, _ -> r }),
    YELLOW({ r, g, _ -> avg(r, g).toInt() }),
    GREEN({ _, g, _ -> g }),
    CYAN({ _, g, b -> avg(g, b).toInt() }),
    BLUE({ _, _, b -> b }),
    MAGENTA({ r, _, b -> avg(b, r).toInt() }),
    LIGHTNESS({ r, g, b -> avg(min(r, g, b), max(r, g, b)).toInt() }),
    LUMINOSITY({ r, g, b -> ((r * 0.2126) + (g * 0.7152) + (b * 0.0722)).toInt().clamp(0, 0xFF) }),
    MIN({ r, g, b -> min(r, g, b) }),
    MED({ r, g, b -> med(r, g, b).toInt() }),
    MAX({ r, g, b -> max(r, g, b) });
    
    operator fun invoke(r: Int, g: Int, b: Int) = calc(r, g, b)
}