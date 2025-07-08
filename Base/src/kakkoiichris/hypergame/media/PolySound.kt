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

import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.LineEvent
import kotlin.concurrent.thread

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/23/2018, 23:48
 */
class PolySound internal constructor(private val clips: List<Clip>) {
    companion object {
        private const val COUNT = 10

        fun isExtension(ext: String) =
            ext.matches("pls".toRegex())

        fun load(path: String): PolySound {
            val clips = List(COUNT) {
                val ais = AudioSystem.getAudioInputStream(Sound::class.java.getResourceAsStream(path))
                val baseFormat = ais.format
                val sampleRate = baseFormat.sampleRate
                val channels = baseFormat.channels

                val decoded = AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    sampleRate,
                    16,
                    channels,
                    channels * 2,
                    sampleRate,
                    false
                )

                val dais = AudioSystem.getAudioInputStream(decoded, ais)

                val clip = AudioSystem.getClip()

                clip.open(dais)

                clip.addLineListener { e ->
                    when (e.type) {
                        LineEvent.Type.STOP -> {
                            clip.stop()
                            clip.microsecondPosition = 0
                        }
                    }
                }

                clip
            }

            return PolySound(clips)
        }
    }

    val isPlaying get() = clips.any { it.isRunning }

    fun play() {
        val clip = clips.firstOrNull { !it.isRunning } ?: return

        thread {
            clip.start()
        }
    }

    fun close() {
        clips.forEach {
            it.stop()
            it.flush()
            it.close()
        }
    }
}