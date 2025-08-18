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
package kakkoiichris.hypergame.data.txt

import kakkoiichris.hypergame.data.DataFile
import kakkoiichris.hypergame.data.Source

class TXT(override val source: Source) : DataFile {
    var text = ""

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