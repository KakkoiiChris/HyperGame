package kakkoiichris.hypergame.media

import java.io.IOException
import java.io.InputStream
import java.util.logging.ConsoleHandler
import java.util.logging.Logger
import javax.sound.sampled.*
import kotlin.concurrent.thread
import kotlin.math.ln

class SoundX internal constructor(input: InputStream) {
    private val stream = try {
        AudioSystem.getAudioInputStream(input)
    }
    catch (e: UnsupportedAudioFileException) {
        throw e
    }
    catch (e: IOException) {
        throw e
    }

    private val line: SourceDataLine

    private var panControl: FloatControl? = null
    private var gainControl: FloatControl? = null

    var playing = false
        private set

    init {
        val format = stream.format
        val info = DataLine.Info(SourceDataLine::class.java, format)

        line = try {
            AudioSystem.getLine(info) as SourceDataLine
        }
        catch (e: UnsupportedAudioFileException) {
            logger.severe(e.message)

            TODO("LINE ERROR")
        }

        try {
            line.open(format)
        }
        catch (e: LineUnavailableException) {
            logger.severe(e.message)

            TODO("LINE OPEN ERROR")
        }

        if (line.isControlSupported(FloatControl.Type.PAN)) {
            panControl = line.getControl(FloatControl.Type.PAN) as FloatControl
        }
        else {
            logger.info("Pan control not supported.")

            TODO("PAN CONTROL ERROR")
        }

        if (line.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            gainControl = line.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
        }
        else {
            logger.info("Gain control not supported.")

            TODO("GAIN CONTROL ERROR")
        }
    }

    var pan: Float = 0f
        set(value) {
            field = Math.clamp(value, MIN_PAN, MAX_PAN)
        }

    var volume: Float = 1f
        set(value) {
            field = Math.clamp(value, MIN_VOLUME, MAX_VOLUME)

            gain = Math.clamp(value.gain, MIN_GAIN, MAX_GAIN)
        }

    private var gain = volume.gain

    fun play() {
        thread(name = "play_sound") {
            stream.reset()
            line.start()

            playing = true

            try {
                var nBytesRead = 0
                val abData = ByteArray(BUFFER_SIZE)

                while (playing && nBytesRead != -1) {
                    nBytesRead = stream.read(abData, 0, abData.size)

                    if (nBytesRead >= 0) {
                        line.write(abData, 0, nBytesRead)
                    }

                    panControl?.value = pan
                    gainControl?.value = gain
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
            finally {
                line.drain()
                line.close()
            }
        }
    }

    fun loop() {
        thread(name = "loop_sound") {
            stream.reset()
            line.start()

            playing = true

            try {
                while (playing) {
                    var nBytesRead = 0
                    val abData = ByteArray(BUFFER_SIZE)

                    while (playing && nBytesRead != -1) {
                        nBytesRead = stream.read(abData, 0, abData.size)

                        if (nBytesRead >= 0) {
                            line.write(abData, 0, nBytesRead)
                        }

                        panControl?.value = pan
                        gainControl?.value = gain
                    }

                    stream.reset()
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
            finally {
                line.drain()
                line.close()
            }
        }
    }

    private companion object {
        const val MIN_GAIN = -80f
        const val MAX_GAIN = 0f
        const val MIN_VOLUME = 0f
        const val MAX_VOLUME = 1f
        const val MIN_PAN = -1f
        const val MAX_PAN = 1f
        const val BUFFER_SIZE = 1024

        val logger = Logger.getLogger(SoundX::class.simpleName)

        private val Float.gain
            get() = (6f * (ln(toDouble()) / ln(2.0))).toFloat()

        init {
            logger.addHandler(ConsoleHandler())
        }
    }
}