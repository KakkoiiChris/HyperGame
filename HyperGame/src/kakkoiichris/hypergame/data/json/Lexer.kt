package kakkoiichris.hypergame.data.json

internal class Lexer(private val src: String) : Iterator<Token<*>> {
    private companion object {
        private const val NUL = '\u0000'
    }

    private var pos = 0
    private var row = 1
    private var col = 1

    override fun hasNext() =
        !isEOF()

    override fun next(): Token<*> {
        while (!isEOF()) {
            if (match(Char::isWhitespace)) {
                skipWhitespace()

                continue
            }

            return when {
                match(::isNumberStart)   -> number()

                match(Char::isLetter)    -> literal()

                match { it in "\'\"" }   -> string()

                match { it in "{}[]:," } -> delimiter()

                else                     -> error("Invalid character '${peek()}'!")
            }
        }

        return Token(here(), Token.Type.End)
    }

    private fun here() =
        Location(row, col)

    private fun peek() =
        if (pos in src.indices)
            src[pos]
        else
            NUL

    private fun match(char: Char) =
        peek() == char

    private fun match(predicate: (Char) -> Boolean) =
        predicate(peek())

    private fun isNumberStart(char: Char) =
        char.isDigit() || char == '-'

    private fun isEOF() =
        match(NUL)

    private fun skip(char: Char) =
        if (match(char)) {
            step()
            true
        }
        else false

    private fun mustSkip(char: Char) {
        if (!skip(char)) {
            error("Expected '$char', but had '${peek()}'! ${here()}")
        }
    }

    private fun step() {
        if (match('\n')) {
            row++
            col = 1
        }
        else {
            col++
        }

        pos++
    }

    private fun StringBuilder.take() {
        append(peek())
        step()
    }

    private fun skipWhitespace() {
        while (!isEOF() && match(Char::isWhitespace)) {
            step()
        }
    }

    private fun number(): Token<Token.Type.Number> {
        val loc = here()

        val result = buildString {
            take()

            while (!isEOF() && match(Char::isDigit)) {
                take()
            }

            if (match('.')) {
                do {
                    take()
                }
                while (!isEOF() && match(Char::isDigit))
            }

            if (match { it in "Ee" }) {
                take()

                do {
                    take()
                }
                while (!isEOF() && match(Char::isDigit))
            }
        }

        val value = result.toDoubleOrNull() ?: error("Number '$result' is invalid! $loc")

        return Token(loc, Token.Type.Number(value))
    }

    private fun string(): Token<Token.Type.String> {
        val loc = here()

        val delimiter = peek()

        mustSkip(delimiter)

        val result = buildString {
            while (!(isEOF() || match(delimiter))) {
                if (skip('\\')) {
                    append(escape(delimiter))
                }
                else {
                    val char = peek()

                    mustSkip(char)

                    append(char)
                }
            }
        }

        mustSkip(delimiter)

        return Token(loc, Token.Type.String(result))
    }

    private fun escape(delimiter: Char) = when {
        skip(delimiter) -> delimiter

        skip('b')       -> '\b'

        skip('f')       -> '\u000c'

        skip('n')       -> '\n'

        skip('r')       -> '\r'

        skip('t')       -> '\t'

        skip('u')       -> unicode()

        else            -> error("Invalid character escape '\\${peek()}'! ${here()}")
    }

    private fun unicode() =
        buildString(4) { repeat(4, ::take) }
            .toInt(16)
            .toChar()

    private fun literal(): Token<Token.Type.Boolean> {
        val loc = here()

        val result = buildString {
            while (!isEOF() && match(Char::isLetter)) {
                take()
            }
        }

        val value = when (result) {
            "true"  -> true

            "false" -> false

            else    -> error("Invalid literal '$result'! $loc")
        }

        return Token(loc, Token.Type.Boolean(value))
    }

    private fun delimiter(): Token<Token.Type.Delimiter> {
        val loc = here()

        val type = when {
            skip('{') -> Token.Type.Delimiter.LEFT_BRACE

            skip('}') -> Token.Type.Delimiter.RIGHT_BRACE

            skip('[') -> Token.Type.Delimiter.LEFT_SQUARE

            skip(']') -> Token.Type.Delimiter.RIGHT_SQUARE

            skip(':') -> Token.Type.Delimiter.COLON

            skip(',') -> Token.Type.Delimiter.COMMA

            else      -> error("Unexpected character '${peek()}'! $loc")
        }

        return Token(loc, type)
    }
}