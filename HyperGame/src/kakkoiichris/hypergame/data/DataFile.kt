package kakkoiichris.hypergame.data

interface DataFile {
    val source: Source

    val isResource get() = source.isResource
    val path get() = source.path

    fun read()

    fun write()

    interface Extension {
        fun isSupportedFileType(path: String) =
            isExtension(path.substringAfterLast('.'))

        fun isExtension(extension: String): Boolean
    }
}