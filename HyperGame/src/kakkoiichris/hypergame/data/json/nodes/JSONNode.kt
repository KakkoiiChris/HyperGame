package kakkoiigames.playkid.data.json.nodes

import kotlin.math.floor

sealed interface JSONNode {
    fun printNode(indent: Int = 0, name: String = "") = buildString {
        repeat(indent) { append("  ") }

        if (name.isNotEmpty()) {
            append("\"$name\" : ")
        }
    }
}

class JSONBoolean(var value: Boolean) : JSONNode {
    override fun printNode(indent: Int, name: String) = buildString {
        append(super.printNode(indent, name))

        append(value.toString())
    }

    override fun toString() = printNode()
}

class JSONNumber(var value: Double) : JSONNode {
    override fun printNode(indent: Int, name: String) = buildString {
        append(super.printNode(indent, name))

        append(truncate())
    }

    private fun truncate() =
        (if (value == floor(value)) value.toInt() else value).toString()

    override fun toString() = printNode()
}

class JSONString(var value: String) : JSONNode {
    override fun printNode(indent: Int, name: String) = buildString {
        append(super.printNode(indent, name))

        val rep = value
            .replace("\"", "\\\"")
            .replace("\r", "\\r")
            .replace("\n", "\\n")

        append("\"$rep\"")
    }

    override fun toString() = printNode()
}