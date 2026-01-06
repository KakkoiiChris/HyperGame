package kakkoiichris.hypergame.data.json

import kakkoiichris.hypergame.data.DataFile
import kakkoiichris.hypergame.data.Source
import kakkoiichris.hypergame.data.json.nodes.JSONObject

class JSON(override val source: Source) : DataFile {
    var root = JSONObject(); private set

    override fun read() {
        val src = source.read()
        val lexer = Lexer(src)
        val parser = Parser(lexer)

        root = parser.parse()
    }

    override fun write() {
        if (isResource) {
            return
        }

        val string = root.toString()

        source.write(string)
    }

    companion object : DataFile.Extension {
        override fun isExtension(extension: String) =
            extension.matches("json".toRegex())

        fun parse(src: String): JSONObject {
            val lexer = Lexer(src)
            val parser = Parser(lexer)

            return parser.parse()
        }

        fun createObject(action: JSONObject.() -> Unit): JSONObject {
            val obj = JSONObject()

            obj.action()

            return obj
        }
    }
}