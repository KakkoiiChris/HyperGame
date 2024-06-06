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
package kakkoiichris.hypergame.util.filesystem

import kakkoiichris.hypergame.media.Fonts
import kakkoiichris.hypergame.media.Sound
import kakkoiichris.hypergame.media.Sprite
import kakkoiichris.hypergame.util.data.CSV
import kakkoiichris.hypergame.util.data.TXT
import kakkoiichris.hypergame.util.data.XML
import kakkoiichris.hypergame.util.data.json.JSON
import java.io.File

class ResourceManager(rootPath: String) {
    private val root = Folder(rootPath)
    
    private var cd = root
    
    fun getSound(name: String) =
        cd.getSound(name)
    
    fun getSprite(name: String) =
        cd.getSprite(name)
    
    fun getFont(name: String) =
        cd.getFont(name)
    
    fun getCSV(name: String) =
        cd.getCSV(name)
    
    fun getJSON(name: String) =
        cd.getJSON(name)
    
    fun getTXT(name: String) =
        cd.getTXT(name)
    
    fun getXML(name: String) =
        cd.getXML(name)
    
    fun getFolder(name: String) =
        cd.getFolder(name)
    
    fun goBack(): Boolean {
        cd = cd.parent ?: return false
        
        return true
    }
    
    fun goTo(name: String): Boolean {
        cd = cd.getFolderOrNull(name) ?: return false
        
        return true
    }
    
    class Folder(private val path: String, val parent: Folder? = null) {
        private val sounds = mutableMapOf<String, Sound>()
        private val sprites = mutableMapOf<String, Sprite>()
        private val fonts = mutableMapOf<String, String>()
        private val csvFiles = mutableMapOf<String, CSV>()
        private val jsonFiles = mutableMapOf<String, JSON>()
        private val txtFiles = mutableMapOf<String, TXT>()
        private val xmlFiles = mutableMapOf<String, XML>()
        private val subFolders = mutableMapOf<String, Folder>()
        
        init {
            val resource = javaClass.getResource(path) ?: error("Unable to load resource '$path'!")
            
            val file = File(resource.toURI())
            
            val files = file.listFiles()?.toList() ?: emptyList()
            
            files.filter { it.isFile }.forEach {
                val resourceName = it.nameWithoutExtension
                val resourcePath = "$path/${it.name}"
                
                when (it.extension.lowercase()) {
                    in Sound.extensions  -> sounds[resourceName] = Sound.load(resourcePath)
                    
                    in Sprite.extensions -> sprites[resourceName] = Sprite.load(resourcePath)
                    
                    in Fonts.extensions  -> fonts[resourceName] = Fonts.register(resourcePath)
                    
                    in CSV.extensions    -> csvFiles[resourceName] = CSV(resourcePath).apply { readResource() }
                    
                    in JSON.extensions   -> jsonFiles[resourceName] = JSON(resourcePath).apply { readResource() }
                    
                    in TXT.extensions    -> txtFiles[resourceName] = TXT(resourcePath).apply { readResource() }
                    
                    in XML.extensions    -> xmlFiles[resourceName] = XML(resourcePath).apply { readResource() }
                }
            }
            
            files.filter { it.isDirectory }.forEach {
                subFolders[it.name] = Folder("$path/${it.name}", this)
            }
        }
        
        fun getSound(name: String) =
            sounds[name] ?: error("Sound '$path/$name' does not exist!")
        
        fun getSprite(name: String) =
            sprites[name] ?: error("Sprite '$path/$name' does not exist!")
        
        fun getFont(name: String) =
            fonts[name] ?: error("Font '$path/$name' does not exist!")
        
        fun getCSV(name: String) =
            csvFiles[name] ?: error("CSV file '$path/$name' does not exist!")
        
        fun getJSON(name: String) =
            jsonFiles[name] ?: error("JSON file '$path/$name' does not exist!")
        
        fun getTXT(name: String) =
            txtFiles[name] ?: error("Text file '$path/$name' does not exist!")
        
        fun getXML(name: String) =
            xmlFiles[name] ?: error("XML file '$path/$name' does not exist!")
        
        fun getFolder(name: String) =
            subFolders[name] ?: error("Folder '$path/$name' does not exist!")
        
        internal fun getFolderOrNull(name: String) =
            subFolders[name]
    }
}