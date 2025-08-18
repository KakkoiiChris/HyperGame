package kakkoiichris.hypergame.data

import java.nio.file.Files
import java.nio.file.Path

class Source private constructor(val path: Path, val isResource: Boolean) {
    fun read() =
        Files.readString(path)

    fun readLines() =
        Files.readAllLines(path)

    fun write(data: String) {
        if (isResource) {
            System.err.println("PLAYKID ERROR @ Source: Cannot write to a system resource! ($path)")
            return
        }

        Files.writeString(path, data)
    }

    companion object {
        fun ofResource(path: String) =
            Source(Path.of(ClassLoader.getSystemResource(path).toURI()), isResource = true)

        fun of(path: String) =
            Source(Path.of(path), isResource = false)
    }
}