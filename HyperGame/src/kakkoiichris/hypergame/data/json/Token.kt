package kakkoiichris.hypergame.data.json

data class Token<T : Token.Type>(val location: Location, val type: T) {
    sealed interface Type {
        data class Boolean(val value: kotlin.Boolean) : Type

        data class Number(val value: Double) : Type

        data class String(val value: kotlin.String) : Type

        enum class Delimiter : Type {
            LEFT_BRACE,
            RIGHT_BRACE,
            LEFT_SQUARE,
            RIGHT_SQUARE,
            COLON,
            COMMA
        }

        object End : Type
    }
}