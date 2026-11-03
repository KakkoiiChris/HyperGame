/**************************************************************
 * ██  ██ ▄▄ ▄▄ ▄▄▄▄  ▄▄▄▄▄ ▄▄▄▄   ▄████   ▄▄▄  ▄▄   ▄▄ ▄▄▄▄▄ *
 * ██████ ▀███▀ ██▄█▀ ██▄▄  ██▄█▄ ██  ▄▄▄ ██▀██ ██▀▄▀██ ██▄▄  *
 * ██  ██   █   ██    ██▄▄▄ ██ ██  ▀███▀  ██▀██ ██   ██ ██▄▄▄ *
 *             Kotlin 2D Game Development Library             *
 *              Copyright (C) 2021, KakkoiiChris              *
 **************************************************************/
package kakkoiichris.hypergame.data

class TXT(override val source: Source) : DataFile {
    var text = ""

    val lines get() = text.lines()

    override fun read() {
        text = source.read()
    }

    override fun write() {
        if (isResource) {
            return
        }

        source.write(text)
    }

    override fun toString() = text

    companion object : DataFile.Extension {
        override fun isExtension(extension: String) =
            extension.matches("te?xt".toRegex())
    }
}