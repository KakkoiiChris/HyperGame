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
package kakkoiichris.hypergame.view

import kakkoiichris.hypergame.state.State

abstract class Sketch(width: Int, height: Int, title: String, frameRate: Double = 60.0) : State {
    private val display = Display(width, height, frameRate = frameRate, title = title)
    
    fun open() {
        display.manager.push(this)
        
        display.open()
    }
    
    fun close() = display.close()
    
    override fun swapTo(view: View) = Unit
    
    override fun swapFrom(view: View) = Unit
    
    override fun halt(view: View) = Unit
}