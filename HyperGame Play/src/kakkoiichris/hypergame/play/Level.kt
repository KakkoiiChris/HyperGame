package kakkoiichris.hypergame.play

import kakkoiichris.hypergame.Game
import kakkoiichris.hypergame.input.Input
import kakkoiichris.hypergame.input.Key
import kakkoiichris.hypergame.media.*
import kakkoiichris.hypergame.util.Time
import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.view.View

/**
 * HyperGame Play
 *
 * Copyright (C) 2023, KakkoiiChris
 *
 * File:    Level.kt
 *
 * Created: Monday, June 05, 2023, 22:19:42
 *
 * @author Christian Bryce Alexander
 */
class Level(private val viewport: Box, val cols: Int, val rows: Int) : Renderable {
    private val blockSize = 16

    private val blocks = Array<Block>(cols * rows) { Block.Empty }

    val width = cols * blockSize
    val height = rows * blockSize

    private val tileSet: SpriteSheet

    private val entities = mutableListOf<Entity>()

    private val camera = Camera(this, viewport.copy(x = 0.0, y = 0.0))

    init {
        val size = blockSize * 2
        val sizeSq = size * size
        val tiles = Sprite(size, size, IntArray(sizeSq) { i -> (i * (0xFFFFFF / sizeSq)) or 0xFF000000.toInt() })

        tileSet = SpriteSheet(tiles, blockSize, blockSize)

        val e = TestEntity()

        entities += e

        camera.target = e
    }

    fun addEntity(entity: Entity) {
        entities += entity
    }

    override fun update(view: View, game: Game, time: Time, input: Input) {
        entities.forEach { it.update(view, game, time, input) }

        camera.update(view, game, time, input)
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        renderer.push()

        renderer.clip = viewport.rectangle

        renderer.translate(viewport.position)

        renderer.push()

        renderer.translate(-camera.position)

        for (y in 0..<rows) {
            val by = y * blockSize

            for (x in 0..<cols) {
                val bx = x * blockSize

                val (spriteIndex, collider) = blocks[x + y * cols]

                if (spriteIndex >= 0) {
                    renderer.drawImage(tileSet[spriteIndex], bx, by, blockSize, blockSize)
                    renderer.drawString(spriteIndex.toString(), bx, by + blockSize)
                }

                val shape = collider.shape(bx, by, blockSize)

                if (shape != null) {
                    renderer.draw(shape)
                }
            }
        }

        entities.forEach { it.render(view, game, renderer) }

        renderer.pop()

        camera.render(view, game, renderer)

        renderer.pop()
    }
}

enum class MyBlocks : Block {
    A, B, C, D;

    override val spriteIndex: Int
        get() = ordinal

    override val collider: Block.Collider
        get() = Block.Collider.BOX

    companion object {
        fun random() = entries.random()
    }
}

class TestEntity : Entity(0.0, 0.0, 16.0, 16.0) {
    override fun update(view: View, game: Game, time: Time, input: Input) {
        if (input.keyHeld(Key.W)) {
            y -= time.delta
        }

        if (input.keyHeld(Key.S)) {
            y += time.delta
        }

        if (input.keyHeld(Key.A)) {
            x -= time.delta
        }

        if (input.keyHeld(Key.D)) {
            x += time.delta
        }
    }

    override fun render(view: View, game: Game, renderer: Renderer) {
        renderer.color = Colors.CSS.mediumPurple

        renderer.fillOval(this)
    }
}