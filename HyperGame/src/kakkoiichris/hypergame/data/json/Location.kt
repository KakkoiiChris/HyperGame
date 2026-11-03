/**************************************************************
 * ██  ██ ▄▄ ▄▄ ▄▄▄▄  ▄▄▄▄▄ ▄▄▄▄   ▄████   ▄▄▄  ▄▄   ▄▄ ▄▄▄▄▄ *
 * ██████ ▀███▀ ██▄█▀ ██▄▄  ██▄█▄ ██  ▄▄▄ ██▀██ ██▀▄▀██ ██▄▄  *
 * ██  ██   █   ██    ██▄▄▄ ██ ██  ▀███▀  ██▀██ ██   ██ ██▄▄▄ *
 *             Kotlin 2D Game Development Library             *
 *              Copyright (C) 2021, KakkoiiChris              *
 **************************************************************/
package kakkoiichris.hypergame.data.json

data class Location(val row: Int, val col: Int) {
    override fun toString() = "(Row $row, Col $col)"
}