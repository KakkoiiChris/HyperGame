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

import kakkoiichris.hypergame.util.math.Box
import kakkoiichris.hypergame.util.math.Vector
import java.awt.*
import java.awt.font.GlyphVector
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import java.awt.image.BufferedImageOp
import java.awt.image.ImageObserver
import java.awt.image.RenderedImage
import java.awt.image.renderable.RenderableImage
import java.text.AttributedCharacterIterator
import java.util.*


/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/22/2018, 19:15
 */
class Renderer(private val context: Graphics2D) {
    private val history = Stack<State>()
    
    private val vertices = mutableListOf<Vector>()
    
    val clipBounds
        get() =
            context.clipBounds
    
    val deviceConfiguration
        get() =
            context.deviceConfiguration
    
    val fontMetrics
        get() =
            context.fontMetrics
    
    val fontRenderContext
        get() =
            context.fontRenderContext
    
    val renderingHints
        get() =
            context.renderingHints
    
    var background
        get() =
            context.background
        set(value) {
            context.background = value
        }
    
    var clip
        get() =
            context.clip
        set(value) {
            context.clip = value
        }
    
    var color
        get() =
            context.color
        set(value) {
            context.color = value
        }
    
    var composite
        get() =
            context.composite
        set(value) {
            context.composite = value
        }
    
    var font
        get() =
            context.font
        set(value) {
            context.font = value
        }
    
    var paint
        get() =
            context.paint
        set(value) {
            context.paint = value
        }
    
    var stroke
        get() =
            context.stroke
        set(value) {
            context.stroke = value
        }
    
    var transform
        get() =
            context.transform
        set(value) {
            context.transform = value
        }
    
    init {
        history.push(getCurrentState())
    }
    
    // LIBRARY METHODS
    
    private fun getCurrentState() =
        State(background, clip, color, composite, font, paint, stroke, transform)
    
    fun push() =
        history.push(getCurrentState())
    
    fun pop(): State {
        if (history.size > 1) {
            val lastState = history.pop()
            
            background = lastState.background
            clip = lastState.clip
            color = lastState.color
            composite = lastState.composite
            font = lastState.font
            paint = lastState.paint
            stroke = lastState.stroke
            transform = lastState.transform
            
            return lastState
        }
        
        return history.peek()
    }
    
    fun withState(f: Renderer.() -> Unit) {
        push()
        
        f()
        
        pop()
    }
    
    fun rotate(theta: Double, vector: Vector) =
        context.rotate(theta, vector.x, vector.y)
    
    fun scale(vector: Vector) =
        context.scale(vector.x, vector.y)
    
    fun shear(vector: Vector) =
        context.shear(vector.x, vector.y)
    
    fun translate(vector: Vector) =
        translate(vector.x, vector.y)
    
    fun drawAnimation(animation: Animation, x: Int, y: Int, observer: ImageObserver? = null) =
        context.drawImage(animation.frame, x, y, observer)
    
    fun drawAnimation(animation: Animation, x: Int, y: Int, background: Color, observer: ImageObserver? = null) =
        context.drawImage(animation.frame, x, y, background, observer)
    
    fun drawAnimation(animation: Animation, x: Int, y: Int, width: Int, height: Int, observer: ImageObserver? = null) =
        context.drawImage(animation.frame, x, y, width, height, observer)
    
    fun drawAnimation(
        animation: Animation,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        background: Color,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(animation.frame, x, y, width, height, background, observer)
    
    fun drawAnimation(
        animation: Animation,
        dxa: Int,
        dya: Int,
        dxb: Int,
        dyb: Int,
        sxa: Int,
        sya: Int,
        sxb: Int,
        syb: Int,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(animation.frame, dxa, dya, dxb, dyb, sxa, sya, sxb, syb, observer)
    
    fun drawAnimation(
        animation: Animation,
        dxa: Int,
        dya: Int,
        dxb: Int,
        dyb: Int,
        sxa: Int,
        sya: Int,
        sxb: Int,
        syb: Int,
        background: Color,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(animation.frame, dxa, dya, dxb, dyb, sxa, sya, sxb, syb, background, observer)
    
    fun drawAnimation(animation: Animation, vector: Vector, observer: ImageObserver? = null) =
        context.drawImage(animation.frame, vector.x.toInt(), vector.y.toInt(), observer)
    
    fun drawAnimation(animation: Animation, vector: Vector, background: Color, observer: ImageObserver? = null) =
        context.drawImage(animation.frame, vector.x.toInt(), vector.y.toInt(), background, observer)
    
    fun drawAnimation(animation: Animation, box: Box, observer: ImageObserver? = null) =
        context.drawImage(
            animation.frame,
            box.x.toInt(),
            box.y.toInt(),
            box.width.toInt(),
            box.height.toInt(),
            observer
        )
    
    fun drawAnimation(animation: Animation, box: Box, background: Color, observer: ImageObserver? = null) =
        context.drawImage(
            animation.frame,
            box.x.toInt(),
            box.y.toInt(),
            box.width.toInt(),
            box.height.toInt(),
            background,
            observer
        )
    
    fun drawAnimation(
        animation: Animation,
        dstA: Vector,
        dstB: Vector,
        srcA: Vector,
        srcB: Vector,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(
            animation.frame,
            dstA.x.toInt(),
            dstA.y.toInt(),
            dstB.x.toInt(),
            dstB.y.toInt(),
            srcA.x.toInt(),
            srcA.y.toInt(),
            srcB.x.toInt(),
            srcB.y.toInt(),
            observer
        )
    
    fun drawAnimation(
        animation: Animation,
        dstA: Vector,
        dstB: Vector,
        srcA: Vector,
        srcB: Vector,
        background: Color,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(
            animation.frame,
            dstA.x.toInt(),
            dstA.y.toInt(),
            dstB.x.toInt(),
            dstB.y.toInt(),
            srcA.x.toInt(),
            srcA.y.toInt(),
            srcB.x.toInt(),
            srcB.y.toInt(),
            background,
            observer
        )
    
    fun drawAnimation(animation: Animation, dst: Box, src: Box, observer: ImageObserver? = null) =
        context.drawImage(
            animation.frame,
            dst.left.toInt(),
            dst.top.toInt(),
            dst.right.toInt(),
            dst.bottom.toInt(),
            src.left.toInt(),
            src.top.toInt(),
            src.right.toInt(),
            src.bottom.toInt(),
            observer
        )
    
    fun drawAnimation(animation: Animation, dst: Box, src: Box, background: Color, observer: ImageObserver? = null) =
        context.drawImage(
            animation.frame,
            dst.left.toInt(),
            dst.top.toInt(),
            dst.right.toInt(),
            dst.bottom.toInt(),
            src.left.toInt(),
            src.top.toInt(),
            src.right.toInt(),
            src.bottom.toInt(),
            background,
            observer
        )
    
    fun drawImage(image: Image, vector: Vector, observer: ImageObserver? = null) =
        context.drawImage(image, vector.x.toInt(), vector.y.toInt(), observer)
    
    fun drawImage(image: Image, vector: Vector, background: Color, observer: ImageObserver? = null) =
        context.drawImage(image, vector.x.toInt(), vector.y.toInt(), background, observer)
    
    fun drawImage(image: Image, box: Box, observer: ImageObserver? = null) =
        context.drawImage(image, box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt(), observer)
    
    fun drawImage(image: Image, box: Box, background: Color, observer: ImageObserver? = null) =
        context.drawImage(
            image,
            box.x.toInt(),
            box.y.toInt(),
            box.width.toInt(),
            box.height.toInt(),
            background,
            observer
        )
    
    fun drawImage(
        image: Image,
        dstA: Vector,
        dstB: Vector,
        srcA: Vector,
        srcB: Vector,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(
            image,
            dstA.x.toInt(),
            dstA.y.toInt(),
            dstB.x.toInt(),
            dstB.y.toInt(),
            srcA.x.toInt(),
            srcA.y.toInt(),
            srcB.x.toInt(),
            srcB.y.toInt(),
            observer
        )
    
    fun drawImage(
        image: Image,
        dstA: Vector,
        dstB: Vector,
        srcA: Vector,
        srcB: Vector,
        background: Color,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(
            image,
            dstA.x.toInt(),
            dstA.y.toInt(),
            dstB.x.toInt(),
            dstB.y.toInt(),
            srcA.x.toInt(),
            srcA.y.toInt(),
            srcB.x.toInt(),
            srcB.y.toInt(),
            background,
            observer
        )
    
    fun drawImage(image: Image, dst: Box, src: Box, observer: ImageObserver? = null) =
        context.drawImage(
            image,
            dst.left.toInt(),
            dst.top.toInt(),
            dst.right.toInt(),
            dst.bottom.toInt(),
            src.left.toInt(),
            src.top.toInt(),
            src.right.toInt(),
            src.bottom.toInt(),
            observer
        )
    
    fun drawImage(image: Image, dst: Box, src: Box, background: Color, observer: ImageObserver? = null) =
        context.drawImage(
            image,
            dst.left.toInt(),
            dst.top.toInt(),
            dst.right.toInt(),
            dst.bottom.toInt(),
            src.left.toInt(),
            src.top.toInt(),
            src.right.toInt(),
            src.bottom.toInt(),
            background,
            observer
        )
    
    fun drawSheet(sheet: SpriteSheet, row: Int, column: Int, x: Int, y: Int) =
        context.drawImage(
            sheet.sprite,
            x,
            y,
            x + sheet.spriteWidth,
            y + sheet.spriteHeight,
            column * sheet.spriteWidth,
            row * sheet.spriteHeight,
            (column * sheet.spriteWidth) + sheet.spriteWidth,
            (row * sheet.spriteHeight) + sheet.spriteHeight,
            null
        )
    
    fun drawSheet(sheet: SpriteSheet, row: Int, column: Int, vector: Vector) =
        context.drawImage(
            sheet.sprite,
            vector.x.toInt(),
            vector.y.toInt(),
            (vector.x + sheet.spriteWidth).toInt(),
            (vector.y + sheet.spriteHeight).toInt(),
            column * sheet.spriteWidth,
            row * sheet.spriteHeight,
            (column * sheet.spriteWidth) + sheet.spriteWidth,
            (row * sheet.spriteHeight) + sheet.spriteHeight,
            null
        )
    
    fun drawSheet(sheet: SpriteSheet, row: Int, column: Int, x: Int, y: Int, width: Int, height: Int) =
        context.drawImage(
            sheet.sprite,
            x,
            y,
            x + width,
            y + height,
            column * sheet.spriteWidth,
            row * sheet.spriteHeight,
            (column * sheet.spriteWidth) + sheet.spriteWidth,
            (row * sheet.spriteHeight) + sheet.spriteHeight,
            null
        )
    
    fun drawSheet(sheet: SpriteSheet, row: Int, column: Int, box: Box) =
        context.drawImage(
            sheet.sprite,
            box.left.toInt(),
            box.top.toInt(),
            box.right.toInt(),
            box.bottom.toInt(),
            column * sheet.spriteWidth,
            row * sheet.spriteHeight,
            (column * sheet.spriteWidth) + sheet.spriteWidth,
            (row * sheet.spriteHeight) + sheet.spriteHeight,
            null
        )
    
    fun drawLine(va: Vector, vb: Vector) =
        context.drawLine(va.x.toInt(), va.y.toInt(), vb.x.toInt(), vb.y.toInt())
    
    fun drawOval(box: Box) =
        context.drawOval(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt())
    
    fun fillOval(box: Box) =
        context.fillOval(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt())
    
    fun drawRect(box: Box) =
        context.drawRect(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt())
    
    fun fillRect(box: Box) =
        context.fillRect(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt())
    
    fun drawRoundRect(box: Box, arcWidth: Int, arcHeight: Int) =
        context.drawRoundRect(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt(), arcWidth, arcHeight)
    
    fun fillRoundRect(box: Box, arcWidth: Int, arcHeight: Int) =
        context.fillRoundRect(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt(), arcWidth, arcHeight)
    
    fun draw3DRect(box: Box, raised: Boolean) =
        context.draw3DRect(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt(), raised)
    
    fun fill3DRect(box: Box, raised: Boolean) =
        context.fill3DRect(box.x.toInt(), box.y.toInt(), box.width.toInt(), box.height.toInt(), raised)
    
    fun drawString(string: String, vector: Vector) =
        context.drawString(string, vector.x.toFloat(), vector.y.toFloat())
    
    fun drawString(string: String, x: Int, y: Int, width: Int, height: Int, xAlign: Double = 0.5, yAlign: Double = 0.5) {
        val sx = x + (width - fontMetrics.stringWidth(string)) * xAlign
        val sy = y + (height - fontMetrics.height) * yAlign + fontMetrics.ascent
        
        drawString(string, sx.toInt(), sy.toInt())
    }
    
    fun drawString(string: String, box: Box, xAlign: Double = 0.5, yAlign: Double = 0.5) {
        val sx = box.x + (box.width - fontMetrics.stringWidth(string)) * xAlign
        val sy = box.y + (box.height - fontMetrics.height) * yAlign + fontMetrics.ascent
        
        drawString(string, sx.toInt(), sy.toInt())
    }
    
    fun addVertex(x: Int, y: Int) {
        vertices.add(Vector(x.toDouble(), y.toDouble()))
    }
    
    fun addVertex(vector: Vector) {
        vertices += vector
    }
    
    fun addVertices(vectors: Collection<Vector>) {
        vertices += vectors
    }
    
    fun clearVertices() {
        vertices.clear()
    }
    
    fun drawVertices() {
        val xPoints = vertices.map { it.x.toInt() }.toIntArray()
        val yPoints = vertices.map { it.y.toInt() }.toIntArray()
        val nPoints = vertices.size
        
        drawPolygon(xPoints, yPoints, nPoints)
    }
    
    fun fillVertices() {
        val xPoints = vertices.map { it.x.toInt() }.toIntArray()
        val yPoints = vertices.map { it.y.toInt() }.toIntArray()
        val nPoints = vertices.size
        
        fillPolygon(xPoints, yPoints, nPoints)
    }
    
    // NATIVE METHODS
    
    fun create() =
        context.create()
    
    fun create(x: Int, y: Int, width: Int, height: Int) =
        context.create(x, y, width, height)
    
    fun setPaintMode() =
        context.setPaintMode()
    
    fun setXORMode(color: Color) =
        context.setXORMode(color)
    
    fun getFontMetrics(font: Font) =
        context.getFontMetrics(font)
    
    fun clipRect(x: Int, y: Int, width: Int, height: Int) =
        context.clipRect(x, y, width, height)
    
    fun setClip(x: Int, y: Int, width: Int, height: Int) =
        context.setClip(x, y, width, height)
    
    fun clip(shape: Shape) =
        context.clip(shape)
    
    fun clearRect(x: Int, y: Int, width: Int, height: Int) =
        context.clearRect(x, y, width, height)
    
    fun copyArea(x: Int, y: Int, width: Int, height: Int, dx: Int, dy: Int) =
        context.copyArea(x, y, width, height, dx, dy)
    
    fun drawLine(xa: Int, ya: Int, xb: Int, yb: Int) =
        context.drawLine(xa, ya, xb, yb)
    
    fun drawRect(x: Int, y: Int, width: Int, height: Int) =
        context.drawRect(x, y, width, height)
    
    fun fillRect(x: Int, y: Int, width: Int, height: Int) =
        context.fillRect(x, y, width, height)
    
    fun drawRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) =
        context.drawRoundRect(x, y, width, height, arcWidth, arcHeight)
    
    fun fillRoundRect(x: Int, y: Int, width: Int, height: Int, arcWidth: Int, arcHeight: Int) =
        context.fillRoundRect(x, y, width, height, arcWidth, arcHeight)
    
    fun draw3DRect(x: Int, y: Int, width: Int, height: Int, raised: Boolean) =
        context.draw3DRect(x, y, width, height, raised)
    
    fun fill3DRect(x: Int, y: Int, width: Int, height: Int, raised: Boolean) =
        context.fill3DRect(x, y, width, height, raised)
    
    fun drawOval(x: Int, y: Int, width: Int, height: Int) =
        context.drawOval(x, y, width, height)
    
    fun fillOval(x: Int, y: Int, width: Int, height: Int) =
        context.fillOval(x, y, width, height)
    
    fun drawArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) =
        context.drawArc(x, y, width, height, startAngle, arcAngle)
    
    fun fillArc(x: Int, y: Int, width: Int, height: Int, startAngle: Int, arcAngle: Int) =
        context.fillArc(x, y, width, height, startAngle, arcAngle)
    
    fun drawPolyline(xPoints: IntArray, yPoints: IntArray, nPoints: Int) =
        context.drawPolyline(xPoints, yPoints, nPoints)
    
    fun drawPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) =
        context.drawPolygon(xPoints, yPoints, nPoints)
    
    fun drawPolygon(polygon: Polygon) =
        context.drawPolygon(polygon)
    
    fun fillPolygon(xPoints: IntArray, yPoints: IntArray, nPoints: Int) =
        context.fillPolygon(xPoints, yPoints, nPoints)
    
    fun fillPolygon(polygon: Polygon) =
        context.fillPolygon(polygon)
    
    fun drawString(string: String, x: Int, y: Int) =
        context.drawString(string, x, y)
    
    fun drawString(iterator: AttributedCharacterIterator, x: Int, y: Int) =
        context.drawString(iterator, x, y)
    
    fun drawString(string: String, x: Float, y: Float) =
        context.drawString(string, x, y)
    
    fun drawString(iterator: AttributedCharacterIterator, x: Float, y: Float) =
        context.drawString(iterator, x, y)
    
    fun drawChars(data: CharArray, offset: Int, length: Int, x: Int, y: Int) =
        context.drawChars(data, offset, length, x, y)
    
    fun drawBytes(data: ByteArray, offset: Int, length: Int, x: Int, y: Int) =
        context.drawBytes(data, offset, length, x, y)
    
    fun drawGlyphVector(glyph: GlyphVector, x: Float, y: Float) =
        context.drawGlyphVector(glyph, x, y)
    
    fun drawImage(image: Image, x: Int, y: Int, observer: ImageObserver? = null) =
        context.drawImage(image, x, y, observer)
    
    fun drawImage(image: Image, x: Int, y: Int, width: Int, height: Int, observer: ImageObserver? = null) =
        context.drawImage(image, x, y, width, height, observer)
    
    fun drawImage(image: Image, x: Int, y: Int, background: Color, observer: ImageObserver? = null) =
        context.drawImage(image, x, y, background, observer)
    
    fun drawImage(
        image: Image,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        background: Color,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(image, x, y, width, height, background, observer)
    
    fun drawImage(
        image: Image,
        dxa: Int,
        dya: Int,
        dxb: Int,
        dyb: Int,
        sxa: Int,
        sya: Int,
        sxb: Int,
        syb: Int,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(image, dxa, dya, dxb, dyb, sxa, sya, sxb, syb, observer)
    
    fun drawImage(
        image: Image,
        dxa: Int,
        dya: Int,
        dxb: Int,
        dyb: Int,
        sxa: Int,
        sya: Int,
        sxb: Int,
        syb: Int,
        background: Color,
        observer: ImageObserver? = null,
    ) =
        context.drawImage(image, dxa, dya, dxb, dyb, sxa, sya, sxb, syb, background, observer)
    
    fun drawImage(image: Image, transform: AffineTransform, observer: ImageObserver? = null) =
        context.drawImage(image, transform, observer)
    
    fun drawImage(image: BufferedImage, op: BufferedImageOp, x: Int, y: Int) =
        context.drawImage(image, op, x, y)
    
    fun drawRenderedImage(image: RenderedImage, transform: AffineTransform) =
        context.drawRenderedImage(image, transform)
    
    fun drawRenderableImage(image: RenderableImage, transform: AffineTransform) =
        context.drawRenderableImage(image, transform)
    
    fun hitClip(x: Int, y: Int, width: Int, height: Int) =
        context.hitClip(x, y, width, height)
    
    fun getClipBounds(rectangle: Rectangle?) =
        context.getClipBounds(rectangle)
    
    fun draw(shape: Shape) =
        context.draw(shape)
    
    fun fill(shape: Shape) =
        context.fill(shape)
    
    fun hit(rect: Rectangle, shape: Shape, onStroke: Boolean) =
        context.hit(rect, shape, onStroke)
    
    fun getRenderingHint(hintKey: RenderingHints.Key) =
        context.getRenderingHint(hintKey)
    
    fun setRenderingHint(hintKey: RenderingHints.Key, hintValue: Any) =
        context.setRenderingHint(hintKey, hintValue)
    
    fun setRenderingHints(hints: Map<*, *>) =
        context.setRenderingHints(hints)
    
    fun addRenderingHints(hints: Map<*, *>) =
        context.addRenderingHints(hints)
    
    fun rotate(theta: Double) =
        context.rotate(theta)
    
    fun rotate(theta: Double, x: Double, y: Double) =
        context.rotate(theta, x, y)
    
    fun scale(sx: Double, sy: Double) =
        context.scale(sx, sy)
    
    fun shear(shx: Double, shy: Double) =
        context.shear(shx, shy)
    
    fun translate(x: Int, y: Int) =
        context.translate(x, y)
    
    fun translate(tx: Double, ty: Double) =
        context.translate(tx, ty)
    
    fun transform(transform: AffineTransform) =
        context.transform(transform)
    
    fun dispose() =
        context.dispose()
    
    override fun toString() =
        "${javaClass.name}[${getCurrentState()}]"
    
    data class State(
        val background: Color?,
        val clip: Shape?,
        val color: Color?,
        val composite: Composite?,
        val font: Font?,
        val paint: Paint?,
        val stroke: Stroke?,
        val transform: AffineTransform?,
    )
}
