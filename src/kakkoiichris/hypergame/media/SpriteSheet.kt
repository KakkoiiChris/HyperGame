package kakkoiichris.hypergame.media

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:16
 */
class SpriteSheet(sheet: Sprite, val spriteWidth: Int, val spriteHeight: Int) {
    val cols = sheet.width / spriteWidth
    val rows = sheet.height / spriteHeight
    val sprites: Array<Sprite>
    
    val count: Int
        get() = sprites.size
    
    init {
        val spriteList = mutableListOf<Sprite>()
        
        for (y in 0 until rows)
            for (x in 0 until cols)
                spriteList += sheet.crop(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight)
        
        sprites = spriteList.toTypedArray()
    }
    
    operator fun get(i: Int) =
        sprites[i]
    
    operator fun get(x: Int, y: Int) =
        sprites[x + y * cols]
}
