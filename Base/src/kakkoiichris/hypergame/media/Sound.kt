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
import kotlin.concurrent.thread

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/23/2018, 23:48
 */
class Sound internal constructor(private val clip: Clip) {
    companion object {
        val extensions = arrayOf("wav", "mp3")
        
        fun load(path: String): Sound {
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
            
            return Sound(clip)
        }
    }
    
    private var listenerThread: Thread = Thread()
    
    private var listening = false
    
    val isPlaying get() = clip.isRunning
    
    fun play(listener: ((Double) -> Unit)? = null) {
        clip.stop()
        
        clip.framePosition = 0
        
        clip.start()
        
        listen(listener)
    }
    
    fun loop(count: Int = Clip.LOOP_CONTINUOUSLY, listener: ((Double) -> Unit)? = null) {
        clip.stop()
        
        clip.framePosition = 0
        
        clip.loop(count)
        
        listen(listener)
    }
    
    private fun listen(listener: ((Double) -> Unit)?) {
        if (listener == null) return
        
        listening = true
        
        listenerThread = thread {
            while (listening) {
                listener(clip.microsecondPosition / clip.microsecondLength.toDouble())
            }
        }
    }
    
    fun pause() = clip.stop()
    
    fun stop() {
        listening = false
        
        clip.stop()
    }
    
    fun close() {
        listening = false
        
        clip.stop()
        clip.flush()
        clip.close()
    }
}