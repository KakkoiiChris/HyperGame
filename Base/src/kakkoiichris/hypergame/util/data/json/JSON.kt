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

import java.io.*

class JSON(private val filePath: String) {
    companion object {
        fun isExtension(ext: String) =
            ext.matches("json".toRegex())
    }

    lateinit var root: Node.Object; private set

    private var isResource = false

    fun readResource(): Boolean {
        val source = javaClass
            .getResourceAsStream(filePath)
            ?.bufferedReader()
            ?.readText()
                ?: return false

        val lexer = Lexer(source)
        val parser = Parser(lexer)
        root = parser.parse()

        isResource = true

        return true
    }

    fun read(): Boolean {
        if (isResource) return false

        val source = InputStreamReader(FileInputStream(filePath)).buffered().readText()

        val lexer = Lexer(source)
        val parser = Parser(lexer)
        root = parser.parse()

        return true
    }

    fun write(): Boolean {
        if (isResource) return false

        val writer = OutputStreamWriter(FileOutputStream(filePath), "utf-8").buffered()

        writer.write(root.toString())

        writer.close()

        return true
    }

    operator fun get(name: String) =
        root[name]
}