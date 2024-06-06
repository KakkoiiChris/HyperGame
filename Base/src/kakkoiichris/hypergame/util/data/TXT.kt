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
package kakkoiichris.hypergame.util.data

import java.io.*

class TXT(private val filePath: String) {
    companion object{
        val extensions = arrayOf("txt")
    }
    
    val lines = mutableListOf<String>()
    
    val text get() = lines.joinToString(separator = "\n")
    
    private var isResource = false
    
    fun readResource() {
        lines.clear()
        
        lines.addAll(
            javaClass
                .getResourceAsStream(filePath)
                ?.bufferedReader()
                ?.readLines()
                ?: return
        )
        
        isResource = true
    }
    
    fun read() {
        if (!isResource) {
            lines.clear()
            
            val reader = BufferedReader(InputStreamReader(FileInputStream(filePath)))
            
            reader.forEachLine {
                lines.add(it)
            }
            
            reader.close()
        }
    }
    
    fun write() {
        if (!isResource) {
            val writer = BufferedWriter(OutputStreamWriter(FileOutputStream(filePath), "utf-8"))
            
            for (line in lines) {
                writer.write(line)
                writer.newLine()
            }
            
            writer.close()
        }
    }
    
    override fun toString() = text
}