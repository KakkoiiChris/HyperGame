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

import org.w3c.dom.Document
import java.io.File
import java.io.FileInputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class XML(private val filePath: String) {
    companion object{
        val extensions = arrayOf("xml")
    }
    
    lateinit var doc: Document; private set
    
    fun readResource(): Boolean {
        try {
            val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            
            doc = builder.parse(javaClass.getResourceAsStream(filePath))
            
            doc.normalize()
        }
        catch (e: Exception) {
            e.printStackTrace()
            
            return false
        }
        
        return true
    }
    
    fun read(): Boolean {
        try {
            val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            
            doc = builder.parse(FileInputStream(File(filePath)))
            
            doc.normalize()
        }
        catch (e: Exception) {
            e.printStackTrace()
            
            return false
        }
        
        return true
    }
    
    fun write(): Boolean {
        try {
            val transformer = TransformerFactory.newInstance().newTransformer()
            
            val source = DOMSource(doc)
            
            val result = StreamResult(File(filePath))
            
            transformer.transform(source, result)
        }
        catch (e: Exception) {
            e.printStackTrace()
            
            return false
        }
        
        return true
    }
}