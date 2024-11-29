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

import kakkoiichris.hypergame.util.data.json.Token.Type.*

internal class Lexer(private val source: String) {
    companion object {
        private const val NUL = '\u0000'
    }

    private var pos = 0
    private var row = 1
    private var col = 1

    fun next(): Token {
        while (!match(NUL)) {
            if (match { isWhitespace() }) {
                skipWhitespace()
            }

            return when {
                match { isLetter() }              -> literal()

                match('-') || match { isDigit() } -> number()

                match { this in "'\"" }           -> string()

                else                              -> symbol()
            }
        }

        return Token(here(), END_OF_FILE)
    }

    private fun peek() =
        if (pos in source.indices)
            source[pos]
        else
            NUL

    private fun here() =
        Location(row, col)

    private fun step() {
        if (peek() == '\n') {
            row++
            col = 1
        }
        else {
            col++
        }

        pos++
    }

    private fun match(char: Char) =
        peek() == char

    private fun match(predicate: Char.() -> Boolean) =
        peek().predicate()

    private fun skip(char: Char): Boolean {
        if (match(char)) {
            step()

            return true
        }

        return false
    }

    private fun skip(predicate: Char.() -> Boolean): Boolean {
        if (match(predicate)) {
            step()

            return true
        }

        return false
    }

    private fun mustSkip(char: Char) {
        val location = here()

        if (!skip(char)) {
            error("Expected character '$char'; encountered character '${peek()}'! @ $location")
        }
    }

    private fun isEOF() =
        match(NUL)

    private fun skipWhitespace() {
        while (!isEOF() && skip { isWhitespace() }) Unit
    }

    private fun StringBuilder.take() {
        append(peek())

        step()
    }

    private fun number(): Token {
        val location = here()

        val result = buildString {
            do {
                take()
            }
            while (!isEOF() && match { isDigit() })

            if (match('.')) {
                do {
                    take()
                }
                while (!isEOF() && match { isDigit() })
            }
        }

        return Token(location, VALUE, result.toDouble())
    }

    private fun string(): Token {
        val location = here()

        val delimiter = peek()

        mustSkip(delimiter)

        val result = buildString {
            while (!isEOF() && !skip(delimiter)) {
                if (skip('\\')) {
                    append(
                        when {
                            skip('\\')      -> '\\'

                            skip(delimiter) -> delimiter

                            skip('b')       -> '\b'

                            skip('f')       -> '\u000c'

                            skip('n')       -> '\n'

                            skip('r')       -> '\r'

                            skip('t')       -> '\t'

                            skip('u')       -> unicode()

                            else            -> error("Invalid character escape '\\${peek()}'! @ $location")
                        }
                    )
                }
                else {
                    take()
                }
            }
        }

        return Token(location, VALUE, result)
    }

    private fun unicode() =
        buildString(4) { repeat(4) { take() } }
            .toInt(16)
            .toChar()

    private fun literal(): Token {
        val location = here()

        val result = buildString {
            do {
                take()
            }
            while (!isEOF() && match { isLetter() })
        }

        return Token(
            location, VALUE, when (result) {
                "true"  -> true

                "false" -> false

                "null"  -> Null

                else    -> error("Invalid literal '$result'! @ $location")
            }
        )
    }

    private fun symbol(): Token {
        val location = here()

        return Token(
            location,
            when {
                skip('{') -> LEFT_BRACE

                skip('}') -> RIGHT_BRACE

                skip('[') -> LEFT_SQUARE

                skip(']') -> RIGHT_SQUARE

                skip(':') -> COLON

                skip(',') -> COMMA

                else      -> error("Invalid character '${peek()}'! @ $location")
            }
        )
    }
}
