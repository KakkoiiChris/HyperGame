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
package kakkoiichris.hypergame.input

import java.awt.event.KeyEvent

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/7/2018, 00:31
 */
enum class Key(override val code: Int) : Toggle.ID {
    /**
     * @see KeyEvent.VK_CANCEL
     */
    CANCEL(KeyEvent.VK_CANCEL),

    /**
     * @see KeyEvent.VK_BACK_SPACE
     */
    BACK_SPACE(KeyEvent.VK_BACK_SPACE),

    /**
     * @see KeyEvent.VK_TAB
     */
    TAB(KeyEvent.VK_TAB),

    /**
     * @see KeyEvent.VK_ENTER
     */
    ENTER(KeyEvent.VK_ENTER),

    /**
     * @see KeyEvent.VK_CLEAR
     */
    CLEAR(KeyEvent.VK_CLEAR),

    /**
     * @see KeyEvent.VK_SHIFT
     */
    SHIFT(KeyEvent.VK_SHIFT),

    /**
     * @see KeyEvent.VK_CONTROL
     */
    CONTROL(KeyEvent.VK_CONTROL),

    /**
     * @see KeyEvent.VK_ALT
     */
    ALT(KeyEvent.VK_ALT),

    /**
     * @see KeyEvent.VK_PAUSE
     */
    PAUSE(KeyEvent.VK_PAUSE),

    /**
     * @see KeyEvent.VK_ESCAPE
     */
    ESCAPE(KeyEvent.VK_ESCAPE),

    /**
     * @see KeyEvent.VK_SPACE
     */
    SPACE(KeyEvent.VK_SPACE),

    /**
     * @see KeyEvent.VK_PAGE_UP
     */
    PAGE_UP(KeyEvent.VK_PAGE_UP),

    /**
     * @see KeyEvent.VK_PAGE_DOWN
     */
    PAGE_DOWN(KeyEvent.VK_PAGE_DOWN),

    /**
     * @see KeyEvent.VK_END
     */
    END(KeyEvent.VK_END),

    /**
     * @see KeyEvent.VK_HOME
     */
    HOME(KeyEvent.VK_HOME),

    /**
     * @see KeyEvent.VK_LEFT
     */
    LEFT(KeyEvent.VK_LEFT),

    /**
     * @see KeyEvent.VK_UP
     */
    UP(KeyEvent.VK_UP),

    /**
     * @see KeyEvent.VK_RIGHT
     */
    RIGHT(KeyEvent.VK_RIGHT),

    /**
     * @see KeyEvent.VK_DOWN
     */
    DOWN(KeyEvent.VK_DOWN),

    /**
     * @see KeyEvent.VK_COMMA
     */
    COMMA(KeyEvent.VK_COMMA),

    /**
     * @see KeyEvent.VK_MINUS
     */
    MINUS(KeyEvent.VK_MINUS),

    /**
     * @see KeyEvent.VK_PERIOD
     */
    PERIOD(KeyEvent.VK_PERIOD),

    /**
     * @see KeyEvent.VK_SLASH
     */
    SLASH(KeyEvent.VK_SLASH),

    /**
     * @see KeyEvent.VK_0
     */
    ZERO(KeyEvent.VK_0),

    /**
     * @see KeyEvent.VK_1
     */
    ONE(KeyEvent.VK_1),

    /**
     * @see KeyEvent.VK_2
     */
    TWO(KeyEvent.VK_2),

    /**
     * @see KeyEvent.VK_3
     */
    THREE(KeyEvent.VK_3),

    /**
     * @see KeyEvent.VK_4
     */
    FOUR(KeyEvent.VK_4),

    /**
     * @see KeyEvent.VK_5
     */
    FIVE(KeyEvent.VK_5),

    /**
     * @see KeyEvent.VK_6
     */
    SIX(KeyEvent.VK_6),

    /**
     * @see KeyEvent.VK_7
     */
    SEVEN(KeyEvent.VK_7),

    /**
     * @see KeyEvent.VK_8
     */
    EIGHT(KeyEvent.VK_8),

    /**
     * @see KeyEvent.VK_9
     */
    NINE(KeyEvent.VK_9),

    /**
     * @see KeyEvent.VK_SEMICOLON
     */
    SEMICOLON(KeyEvent.VK_SEMICOLON),

    /**
     * @see KeyEvent.VK_EQUALS
     */
    EQUALS(KeyEvent.VK_EQUALS),

    /**
     * @see KeyEvent.VK_A
     */
    A(KeyEvent.VK_A),

    /**
     * @see KeyEvent.VK_B
     */
    B(KeyEvent.VK_B),

    /**
     * @see KeyEvent.VK_C
     */
    C(KeyEvent.VK_C),

    /**
     * @see KeyEvent.VK_D
     */
    D(KeyEvent.VK_D),

    /**
     * @see KeyEvent.VK_E
     */
    E(KeyEvent.VK_E),

    /**
     * @see KeyEvent.VK_F
     */
    F(KeyEvent.VK_F),

    /**
     * @see KeyEvent.VK_G
     */
    G(KeyEvent.VK_G),

    /**
     * @see KeyEvent.VK_H
     */
    H(KeyEvent.VK_H),

    /**
     * @see KeyEvent.VK_I
     */
    I(KeyEvent.VK_I),

    /**
     * @see KeyEvent.VK_J
     */
    J(KeyEvent.VK_J),

    /**
     * @see KeyEvent.VK_K
     */
    K(KeyEvent.VK_K),

    /**
     * @see KeyEvent.VK_L
     */
    L(KeyEvent.VK_L),

    /**
     * @see KeyEvent.VK_M
     */
    M(KeyEvent.VK_M),

    /**
     * @see KeyEvent.VK_N
     */
    N(KeyEvent.VK_N),

    /**
     * @see KeyEvent.VK_O
     */
    O(KeyEvent.VK_O),

    /**
     * @see KeyEvent.VK_P
     */
    P(KeyEvent.VK_P),

    /**
     * @see KeyEvent.VK_Q
     */
    Q(KeyEvent.VK_Q),

    /**
     * @see KeyEvent.VK_R
     */
    R(KeyEvent.VK_R),

    /**
     * @see KeyEvent.VK_S
     */
    S(KeyEvent.VK_S),

    /**
     * @see KeyEvent.VK_T
     */
    T(KeyEvent.VK_T),

    /**
     * @see KeyEvent.VK_U
     */
    U(KeyEvent.VK_U),

    /**
     * @see KeyEvent.VK_V
     */
    V(KeyEvent.VK_V),

    /**
     * @see KeyEvent.VK_W
     */
    W(KeyEvent.VK_W),

    /**
     * @see KeyEvent.VK_X
     */
    X(KeyEvent.VK_X),

    /**
     * @see KeyEvent.VK_Y
     */
    Y(KeyEvent.VK_Y),

    /**
     * @see KeyEvent.VK_Z
     */
    Z(KeyEvent.VK_Z),

    /**
     * @see KeyEvent.VK_OPEN_BRACKET
     */
    OPEN_BRACKET(KeyEvent.VK_OPEN_BRACKET),

    /**
     * @see KeyEvent.VK_BACK_SLASH
     */
    BACK_SLASH(KeyEvent.VK_BACK_SLASH),

    /**
     * @see KeyEvent.VK_CLOSE_BRACKET
     */
    CLOSE_BRACKET(KeyEvent.VK_CLOSE_BRACKET),

    /**
     * @see KeyEvent.VK_NUMPAD0
     */
    NUMPAD_ZERO(KeyEvent.VK_NUMPAD0),

    /**
     * @see KeyEvent.VK_NUMPAD1
     */
    NUMPAD_ONE(KeyEvent.VK_NUMPAD1),

    /**
     * @see KeyEvent.VK_NUMPAD2
     */
    NUMPAD_TWO(KeyEvent.VK_NUMPAD2),

    /**
     * @see KeyEvent.VK_NUMPAD3
     */
    NUMPAD_THREE(KeyEvent.VK_NUMPAD3),

    /**
     * @see KeyEvent.VK_NUMPAD4
     */
    NUMPAD_FOUR(KeyEvent.VK_NUMPAD4),

    /**
     * @see KeyEvent.VK_NUMPAD5
     */
    NUMPAD_FIVE(KeyEvent.VK_NUMPAD5),

    /**
     * @see KeyEvent.VK_NUMPAD6
     */
    NUMPAD_SIX(KeyEvent.VK_NUMPAD6),

    /**
     * @see KeyEvent.VK_NUMPAD7
     */
    NUMPAD_SEVEN(KeyEvent.VK_NUMPAD7),

    /**
     * @see KeyEvent.VK_NUMPAD8
     */
    NUMPAD_EIGHT(KeyEvent.VK_NUMPAD8),

    /**
     * @see KeyEvent.VK_NUMPAD9
     */
    NUMPAD_NINE(KeyEvent.VK_NUMPAD9),

    /**
     * @see KeyEvent.VK_MULTIPLY
     */
    MULTIPLY(KeyEvent.VK_MULTIPLY),

    /**
     * @see KeyEvent.VK_ADD
     */
    ADD(KeyEvent.VK_ADD),

    /**
     * @see KeyEvent.VK_SEPARATOR
     */
    SEPARATOR(KeyEvent.VK_SEPARATOR),

    /**
     * @see KeyEvent.VK_SUBTRACT
     */
    SUBTRACT(KeyEvent.VK_SUBTRACT),

    /**
     * @see KeyEvent.VK_DECIMAL
     */
    DECIMAL(KeyEvent.VK_DECIMAL),

    /**
     * @see KeyEvent.VK_DIVIDE
     */
    DIVIDE(KeyEvent.VK_DIVIDE),

    /**
     * @see KeyEvent.VK_F1
     */
    F1(KeyEvent.VK_F1),

    /**
     * @see KeyEvent.VK_F2
     */
    F2(KeyEvent.VK_F2),

    /**
     * @see KeyEvent.VK_F3
     */
    F3(KeyEvent.VK_F3),

    /**
     * @see KeyEvent.VK_F4
     */
    F4(KeyEvent.VK_F4),

    /**
     * @see KeyEvent.VK_F5
     */
    F5(KeyEvent.VK_F5),

    /**
     * @see KeyEvent.VK_F6
     */
    F6(KeyEvent.VK_F6),

    /**
     * @see KeyEvent.VK_F7
     */
    F7(KeyEvent.VK_F7),

    /**
     * @see KeyEvent.VK_F8
     */
    F8(KeyEvent.VK_F8),

    /**
     * @see KeyEvent.VK_F9
     */
    F9(KeyEvent.VK_F9),

    /**
     * @see KeyEvent.VK_F10
     */
    F10(KeyEvent.VK_F10),

    /**
     * @see KeyEvent.VK_F11
     */
    F11(KeyEvent.VK_F11),

    /**
     * @see KeyEvent.VK_F12
     */
    F12(KeyEvent.VK_F12),

    /**
     * @see KeyEvent.VK_DELETE
     */
    DELETE(KeyEvent.VK_DELETE),

    /**
     * @see KeyEvent.VK_AMPERSAND
     */
    AMPERSAND(KeyEvent.VK_AMPERSAND),

    /**
     * @see KeyEvent.VK_ASTERISK
     */
    ASTERISK(KeyEvent.VK_ASTERISK),

    /**
     * @see KeyEvent.VK_QUOTEDBL
     */
    DOUBLE_QUOTE(KeyEvent.VK_QUOTEDBL),

    /**
     * @see KeyEvent.VK_LESS
     */
    LESS(KeyEvent.VK_LESS),

    /**
     * @see KeyEvent.VK_GREATER
     */
    GREATER(KeyEvent.VK_GREATER),

    /**
     * @see KeyEvent.VK_BRACELEFT
     */
    LEFT_BRACE(KeyEvent.VK_BRACELEFT),

    /**
     * @see KeyEvent.VK_BRACERIGHT
     */
    RIGHT_BRACE(KeyEvent.VK_BRACERIGHT),

    /**
     * @see KeyEvent.VK_BACK_QUOTE
     */
    BACK_QUOTE(KeyEvent.VK_BACK_QUOTE),

    /**
     * @see KeyEvent.VK_QUOTE
     */
    QUOTE(KeyEvent.VK_QUOTE),

    /**
     * @see KeyEvent.VK_KP_UP
     */
    NUMPAD_UP(KeyEvent.VK_KP_UP),

    /**
     * @see KeyEvent.VK_KP_DOWN
     */
    NUMPAD_DOWN(KeyEvent.VK_KP_DOWN),

    /**
     * @see KeyEvent.VK_KP_LEFT
     */
    NUMPAD_LEFT(KeyEvent.VK_KP_LEFT),

    /**
     * @see KeyEvent.VK_KP_RIGHT
     */
    NUMPAD_RIGHT(KeyEvent.VK_KP_RIGHT);
}