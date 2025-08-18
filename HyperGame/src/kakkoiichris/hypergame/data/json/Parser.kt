package kakkoiichris.hypergame.data.json

import kakkoiigames.playkid.data.json.nodes.JSONArray
import kakkoiigames.playkid.data.json.nodes.JSONBoolean
import kakkoiigames.playkid.data.json.nodes.JSONNode
import kakkoiigames.playkid.data.json.nodes.JSONNumber
import kakkoiigames.playkid.data.json.nodes.JSONObject
import kakkoiigames.playkid.data.json.nodes.JSONString

internal class Parser(private val lexer: Lexer) {
    private var token = lexer.next()

    fun parse() =
        `object`()

    private fun match(type: Token.Type) =
        token.type == type

    private inline fun <reified T : Token.Type> match() =
        T::class.isInstance(token.type)

    private fun step() {
        if (lexer.hasNext()) {
            token = lexer.next()
        }
    }

    private fun skip(type: Token.Type) =
        if (match(type)) {
            step()
            true
        }
        else false

    private fun mustSkip(type: Token.Type) {
        if (!skip(type)) {
            error("Invalid token type '${token.type}'! ${token.location}")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private inline fun <reified T : Token.Type> get(): Token<T> {
        val fixed = token as Token<T>

        step()

        return fixed
    }

    private fun node() = when {
        match(Token.Type.Delimiter.LEFT_BRACE)  -> `object`()

        match(Token.Type.Delimiter.LEFT_SQUARE) -> array()

        else                                    -> value()
    }

    private fun `object`(): JSONObject {
        mustSkip(Token.Type.Delimiter.LEFT_BRACE)

        val members = mutableMapOf<String, JSONNode>()

        if (!skip(Token.Type.Delimiter.RIGHT_BRACE)) {
            do {
                val key = key()

                mustSkip(Token.Type.Delimiter.COLON)

                val value = node()

                members[key] = value
            }
            while (skip(Token.Type.Delimiter.COMMA))

            mustSkip(Token.Type.Delimiter.RIGHT_BRACE)
        }

        return JSONObject(members)
    }

    private fun key(): String {
        if (match<Token.Type.String>()) {
            val text = get<Token.Type.String>()

            return text.type.value
        }

        TODO()
    }

    private fun array(): JSONArray {
        mustSkip(Token.Type.Delimiter.LEFT_SQUARE)

        val elements = mutableListOf<JSONNode>()

        if (!skip(Token.Type.Delimiter.RIGHT_SQUARE)) {
            do {
                val value = node()

                elements += value
            }
            while (skip(Token.Type.Delimiter.COMMA))

            mustSkip(Token.Type.Delimiter.RIGHT_SQUARE)
        }

        return JSONArray(elements)
    }

    private fun value() = when {
        match<Token.Type.Boolean>() -> JSONBoolean(get<Token.Type.Boolean>().type.value)

        match<Token.Type.Number>()  -> JSONNumber(get<Token.Type.Number>().type.value)

        match<Token.Type.String>()  -> JSONString(get<Token.Type.String>().type.value)

        else                        -> TODO()
    }
}