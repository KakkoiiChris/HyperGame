package kakkoiichris.hypergame.data

import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

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
class XML(override val source: Source) : DataFile {
    lateinit var doc: Document; private set

    override fun read() {
        try {
            val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()

            doc = builder.parse(InputSource(StringReader(source.read())))

            doc.normalize()
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun write() {
        try {
            val transformer = TransformerFactory.newInstance().newTransformer()

            val source = DOMSource(doc)

            val result = StreamResult()

            transformer.transform(source, result)
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object : DataFile.Extension {
        override fun isExtension(extension: String) =
            extension.matches("xml".toRegex())
    }
}