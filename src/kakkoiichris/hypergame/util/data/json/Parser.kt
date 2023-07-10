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
package kakkoiichris.hypergame.util.data.json

import kakkoiichris.hypergame.util.data.json.Token.Type
import kakkoiichris.hypergame.util.data.json.Token.Type.*

internal class Parser(private val lexer: Lexer) {
    private var currentToken = lexer.next()

    fun parse() =
        parseObject()

    private fun step() {
        currentToken = lexer.next()
    }

    private fun match(type: Type) =
        currentToken.type == type

    private fun skip(type: Type) =
        if (match(type)) {
            step()
            true
        }
        else false

    private fun mustSkip(vararg types: Type) {
        for (type in types) {
            if (match(type)) {
                step()
            }
            else {
                error("Invalid token type '${currentToken.type}'! @ ${currentToken.location}")
            }
        }
    }

    private fun parseObject(): Node.Object {
        val location = currentToken.location

        mustSkip(LeftBrace)

        val members = mutableMapOf<String, Node>()

        if (!skip(RightBrace)) {
            do {
                val name = currentToken.value as? String
                        ?: error("JSON member name '${currentToken.value}' must be a string! @ ${currentToken.location}")

                mustSkip(Value, Colon)

                val node = when {
                    match(LeftBrace)  -> parseObject()

                    match(LeftSquare) -> parseArray()

                    else              -> parseValue()
                }

                members[name] = node
            }
            while (skip(Comma))

            mustSkip(RightBrace)
        }

        return Node.Object(location, members)
    }

    private fun parseArray(): Node.Array {
        val location = currentToken.location

        mustSkip(LeftSquare)

        val elements = mutableListOf<Node>()

        if (!skip(RightSquare)) {
            do {
                val node = when {
                    match(LeftBrace)  -> parseObject()

                    match(LeftSquare) -> parseArray()

                    else              -> parseValue()
                }

                elements.add(node)
            }
            while (skip(Comma))

            mustSkip(RightSquare)
        }

        return Node.Array(location, elements)
    }

    private fun parseValue(): Node.Value {
        val location = currentToken.location
        val value = currentToken.value

        mustSkip(Value)

        return Node.Value(location, value)
    }
}
