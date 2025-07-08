package kakkoiichris.hypergame.util.data.json

// Christian Alexander, 7/10/2023
data class Location(val row: Int, val col: Int) {
    companion object {
        val none get() = Location(0, 0)
    }
}
