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
package kakkoiichris.hypergame.media

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:16
 */
class SpriteSheet(val sprite: Sprite, val spriteWidth: Int, val spriteHeight: Int) {
    val rows = sprite.height / spriteHeight
    val columns = sprite.width / spriteWidth
    
    val count get() = rows * columns
    
    val sprites: Array<Sprite>
        get() {
            val spriteList = mutableListOf<Sprite>()
            
            for (y in 0 until rows) {
                for (x in 0 until columns) {
                    spriteList += sprite.crop(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight)
                }
            }
            
            return spriteList.toTypedArray()
        }
    
    operator fun get(x: Int, y: Int) =
        sprite.crop(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight)
    
    operator fun get(i: Int) =
        get(i % columns, i / columns)
}
