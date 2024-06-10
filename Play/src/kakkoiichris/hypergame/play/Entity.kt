package kakkoiichris.hypergame.play

import kakkoiichris.hypergame.media.Renderable
import kakkoiichris.hypergame.util.math.Box

/**
 * HyperGame Play
 *
 * Copyright (C) 2023, KakkoiiChris
 *
 * File:    Entity.kt
 *
 * Created: Monday, June 05, 2023, 22:51:53
 *
 * @author Christian Bryce Alexander
 */
abstract class Entity(x: Double, y: Double, width: Double, height: Double) : Box(x, y, width, height), Renderable