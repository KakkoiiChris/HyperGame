package kakkoiichris.hypergame.data.json

data class Location(val row: Int, val col: Int) {
    override fun toString() = "(Row $row, Col $col)"
}