package kakkoiichris.hypergame.util.filesystem

import kakkoiichris.hypergame.media.Fonts
import kakkoiichris.hypergame.media.PolySound
import kakkoiichris.hypergame.media.Sound
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.util.data.CSV
import kakkoiichris.hypergame.util.data.TXT
import kakkoiichris.hypergame.util.data.XML
import kakkoiichris.hypergame.util.data.json.JSON
import java.io.File
import java.nio.file.Paths

/**
 * HyperGame
 *
 * Copyright (C) 2023, KakkoiiChris
 *
 * File:    FileSystem.kt
 *
 * Created: Saturday, January 28, 2023, 20:12:42
 *
 * @author Christian Bryce Alexander
 */
class FileSystem(rootPath: String) {
    private val root = Folder(rootPath)

    private var cd = root

    fun getSound(name: String) =
        cd.getSound(name)

    fun getSoundOrNull(name: String) =
        cd.getSoundOrNull(name)

    fun getPolySound(name: String) =
        cd.getPolySound(name)

    fun getPolySoundOrNull(name: String) =
        cd.getPolySoundOrNull(name)

    fun getSprite(name: String) =
        cd.getSprite(name)

    fun getSpriteOrNull(name: String) =
        cd.getSpriteOrNull(name)

    fun getFont(name: String) =
        cd.getFont(name)

    fun getFontOrNull(name: String) =
        cd.getFontOrNull(name)

    fun getCSV(name: String) =
        cd.getCSV(name)

    fun getCSVOrNull(name: String) =
        cd.getCSVOrNull(name)

    fun getJSON(name: String) =
        cd.getJSON(name)

    fun getJSONOrNull(name: String) =
        cd.getJSONOrNull(name)

    fun getTXT(name: String) =
        cd.getTXT(name)

    fun getTXTOrNull(name: String) =
        cd.getTXTOrNull(name)

    fun getXML(name: String) =
        cd.getXML(name)

    fun getXMLOrNull(name: String) =
        cd.getXMLOrNull(name)

    fun getFolder(name: String) =
        cd.getFolder(name)

    fun getFolderOrNull(name: String) =
        cd.getFolderOrNull(name)

    fun goBack(): Boolean {
        cd = cd.parent ?: return false

        return true
    }

    fun goTo(name: String): Boolean {
        cd = cd.getFolderOrNull(name) ?: return false

        return true
    }

    class Folder(private val path: String, val parent: Folder? = null) {
        private val root = Paths.get(path)

        private val sounds = mutableMapOf<String, Sound>()
        private val polySounds = mutableMapOf<String, PolySound>()
        private val sprites = mutableMapOf<String, Sprite>()
        private val fonts = mutableMapOf<String, String>()
        private val csvFiles = mutableMapOf<String, CSV>()
        private val jsonFiles = mutableMapOf<String, JSON>()
        private val txtFiles = mutableMapOf<String, TXT>()
        private val xmlFiles = mutableMapOf<String, XML>()
        private val subFolders = mutableMapOf<String, Folder>()

        init {
            val resource = javaClass.getResource(path) ?: error("Unable to load resource '$root'!")

            val file = File(resource.toURI())

            val files = file.listFiles()?.toList() ?: emptyList()

            files.filter { it.isFile }.forEach {
                val resourceName = it.nameWithoutExtension
                val resourcePath = "$root/${it.name}"

                val ext = it.extension.lowercase()

                when {
                    Sound.isExtension(ext)     -> sounds[resourceName] = Sound.load(resourcePath)

                    PolySound.isExtension(ext) -> polySounds[resourceName] = PolySound.load(resourcePath)

                    Sprite.isExtension(ext)    -> sprites[resourceName] = Sprite.load(resourcePath)

                    Fonts.isExtension(ext)     -> fonts[resourceName] = Fonts.register(resourcePath)

                    CSV.isExtension(ext)       -> csvFiles[resourceName] = CSV(resourcePath).apply { readResource() }

                    JSON.isExtension(ext)      -> jsonFiles[resourceName] = JSON(resourcePath).apply { readResource() }

                    TXT.isExtension(ext)       -> txtFiles[resourceName] = TXT(resourcePath).apply { readResource() }

                    XML.isExtension(ext)       -> xmlFiles[resourceName] = XML(resourcePath).apply { readResource() }
                }
            }

            files.filter { it.isDirectory }.forEach {
                subFolders[it.name] = Folder("$root/${it.name}", this)
            }
        }

        fun getSound(name: String) =
            sounds[name] ?: error("Sound '$root/$name' does not exist!")

        fun getSoundOrNull(name: String) =
            sounds[name]

        fun getPolySound(name: String) =
            polySounds[name] ?: error("Poly sound '$root/$name' does not exist!")

        fun getPolySoundOrNull(name: String) =
            polySounds[name]

        fun getSprite(name: String) =
            sprites[name] ?: error("Sprite '$root/$name' does not exist!")

        fun getSpriteOrNull(name: String) =
            sprites[name]

        fun getFont(name: String) =
            fonts[name] ?: error("Font '$root/$name' does not exist!")

        fun getFontOrNull(name: String) =
            fonts[name]

        fun getCSV(name: String) =
            csvFiles[name] ?: error("CSV file '$root/$name' does not exist!")

        fun getCSVOrNull(name: String) =
            csvFiles[name]

        fun getJSON(name: String) =
            jsonFiles[name] ?: error("JSON file '$root/$name' does not exist!")

        fun getJSONOrNull(name: String) =
            jsonFiles[name]

        fun getTXT(name: String) =
            txtFiles[name] ?: error("Text file '$root/$name' does not exist!")

        fun getTXTOrNull(name: String) =
            txtFiles[name]

        fun getXML(name: String) =
            xmlFiles[name] ?: error("XML file '$root/$name' does not exist!")

        fun getXMLOrNull(name: String) =
            xmlFiles[name]

        fun getFolder(name: String) =
            subFolders[name] ?: error("Folder '$root/$name' does not exist!")

        fun getFolderOrNull(name: String) =
            subFolders[name]
    }
}