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
package kakkoiichris.hypergame.data.csv

import kakkoiichris.hypergame.data.DataFile
import kakkoiichris.hypergame.data.Source

class CSV(override val source: Source, val separator: Char = ',') : DataFile, Iterable<CSV.Row> {
    private val rows = mutableListOf<Row>()

    override fun read() {
        rows.clear()

        val lines = source.readLines()

        lines.forEach { rows += parseLine(it) }
    }

    private fun parseLine(line: String): Row {
        val data = mutableListOf<String>()
        var inQuotes = false

        val token = StringBuilder()

        for (char in line) {
            if (char == '"') {
                inQuotes = !inQuotes
            }
            else if (char == separator && !inQuotes) {
                data.add(token.toString())

                token.clear()
            }
            else {
                token.append(char)
            }
        }

        data.add(token.toString())

        return Row(data)
    }

    override fun write() {
        if (isResource) {
            return
        }

        val string = rows.joinToString(System.lineSeparator())

        source.write(string)
    }

    override fun iterator() =
        rows.iterator()

    operator fun get(index: Int) =
        rows[index]

    class Row(val data: MutableList<String>) : MutableList<String> by data {
        val header get() = data[0]

        val dataWithoutHeader get() = data.drop(1)

        fun withHeader() =
            header to dataWithoutHeader

        override fun toString(): String {
            return data.joinToString(separator = ",") { datum ->
                val escaped = datum
                    .replace("\r", "\\r")
                    .replace("\n", "\\n")

                val hasComma = ',' in escaped
                val hasSingleQuote = '\'' in escaped
                val hasDoubleQuote = '"' in escaped

                when {
                    hasComma && hasSingleQuote && hasDoubleQuote -> "'${escaped.replace("'", "\\'")}'"

                    hasComma && hasSingleQuote                   -> "\"$escaped\""

                    hasComma && hasDoubleQuote                   -> "'$escaped'"

                    hasComma                                     -> "'$escaped'"

                    else                                         -> escaped
                }
            }
        }
    }

    companion object : DataFile.Extension {
        override fun isExtension(extension: String) =
            extension.matches("csv".toRegex())
    }
}