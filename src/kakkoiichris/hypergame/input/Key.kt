package kakkoiichris.hypergame.input

import java.awt.event.KeyEvent

/**
 * ClassDescription
 *
 * @author Christian Bryce Alexander
 * @since 2/7/2018, 00:31
 */
enum class Key(val code: Int) {
    Cancel(KeyEvent.VK_CANCEL),
    
    /**
     * @see KeyEvent.VK_BACK_SPACE
     */
    BackSpace(KeyEvent.VK_BACK_SPACE),
    
    /**
     * @see KeyEvent.VK_TAB
     */
    Tab(KeyEvent.VK_TAB),
    
    /**
     * @see KeyEvent.VK_ENTER
     */
    Enter(KeyEvent.VK_ENTER),
    
    /**
     * @see KeyEvent.VK_CLEAR
     */
    Clear(KeyEvent.VK_CLEAR),
    
    /**
     * @see KeyEvent.VK_SHIFT
     */
    Shift(KeyEvent.VK_SHIFT),
    
    /**
     * @see KeyEvent.VK_CONTROL
     */
    Control(KeyEvent.VK_CONTROL),
    
    /**
     * @see KeyEvent.VK_ALT
     */
    Alt(KeyEvent.VK_ALT),
    
    /**
     * @see KeyEvent.VK_PAUSE
     */
    Pause(KeyEvent.VK_PAUSE),
    
    /**
     * @see KeyEvent.VK_ESCAPE
     */
    Escape(KeyEvent.VK_ESCAPE),
    
    /**
     * @see KeyEvent.VK_SPACE
     */
    Space(KeyEvent.VK_SPACE),
    
    /**
     * @see KeyEvent.VK_PAGE_UP
     */
    PageUp(KeyEvent.VK_PAGE_UP),
    
    /**
     * @see KeyEvent.VK_PAGE_DOWN
     */
    PageDown(KeyEvent.VK_PAGE_DOWN),
    
    /**
     * @see KeyEvent.VK_END
     */
    End(KeyEvent.VK_END),
    
    /**
     * @see KeyEvent.VK_HOME
     */
    Home(KeyEvent.VK_HOME),
    
    /**
     * @see KeyEvent.VK_LEFT
     */
    Left(KeyEvent.VK_LEFT),
    
    /**
     * @see KeyEvent.VK_UP
     */
    Up(KeyEvent.VK_UP),
    
    /**
     * @see KeyEvent.VK_RIGHT
     */
    Right(KeyEvent.VK_RIGHT),
    
    /**
     * @see KeyEvent.VK_DOWN
     */
    Down(KeyEvent.VK_DOWN),
    
    /**
     * @see KeyEvent.VK_COMMA
     */
    Comma(KeyEvent.VK_COMMA),
    
    /**
     * @see KeyEvent.VK_MINUS
     */
    Minus(KeyEvent.VK_MINUS),
    
    /**
     * @see KeyEvent.VK_PERIOD
     */
    Period(KeyEvent.VK_PERIOD),
    
    /**
     * @see KeyEvent.VK_SLASH
     */
    Slash(KeyEvent.VK_SLASH),
    
    /**
     * @see KeyEvent.VK_0
     */
    Zero(KeyEvent.VK_0),
    
    /**
     * @see KeyEvent.VK_1
     */
    One(KeyEvent.VK_1),
    
    /**
     * @see KeyEvent.VK_2
     */
    Two(KeyEvent.VK_2),
    
    /**
     * @see KeyEvent.VK_3
     */
    Three(KeyEvent.VK_3),
    
    /**
     * @see KeyEvent.VK_4
     */
    Four(KeyEvent.VK_4),
    
    /**
     * @see KeyEvent.VK_5
     */
    Five(KeyEvent.VK_5),
    
    /**
     * @see KeyEvent.VK_6
     */
    Six(KeyEvent.VK_6),
    
    /**
     * @see KeyEvent.VK_7
     */
    Seven(KeyEvent.VK_7),
    
    /**
     * @see KeyEvent.VK_8
     */
    Eight(KeyEvent.VK_8),
    
    /**
     * @see KeyEvent.VK_9
     */
    Nine(KeyEvent.VK_9),
    
    /**
     * @see KeyEvent.VK_SEMICOLON
     */
    Semicolon(KeyEvent.VK_SEMICOLON),
    
    /**
     * @see KeyEvent.VK_EQUALS
     */
    Equals(KeyEvent.VK_EQUALS),
    
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
    OpenBracket(KeyEvent.VK_OPEN_BRACKET),
    
    /**
     * @see KeyEvent.VK_BACK_SLASH
     */
    BackSlash(KeyEvent.VK_BACK_SLASH),
    
    /**
     * @see KeyEvent.VK_CLOSE_BRACKET
     */
    CloseBracket(KeyEvent.VK_CLOSE_BRACKET),
    
    /**
     * @see KeyEvent.VK_NUMPAD0
     */
    NumpadZero(KeyEvent.VK_NUMPAD0),
    
    /**
     * @see KeyEvent.VK_NUMPAD1
     */
    NumpadOne(KeyEvent.VK_NUMPAD1),
    
    /**
     * @see KeyEvent.VK_NUMPAD2
     */
    NumpadTwo(KeyEvent.VK_NUMPAD2),
    
    /**
     * @see KeyEvent.VK_NUMPAD3
     */
    NumpadThree(KeyEvent.VK_NUMPAD3),
    
    /**
     * @see KeyEvent.VK_NUMPAD4
     */
    NumpadFour(KeyEvent.VK_NUMPAD4),
    
    /**
     * @see KeyEvent.VK_NUMPAD5
     */
    NumpadFive(KeyEvent.VK_NUMPAD5),
    
    /**
     * @see KeyEvent.VK_NUMPAD6
     */
    NumpadSix(KeyEvent.VK_NUMPAD6),
    
    /**
     * @see KeyEvent.VK_NUMPAD7
     */
    NumpadSeven(KeyEvent.VK_NUMPAD7),
    
    /**
     * @see KeyEvent.VK_NUMPAD8
     */
    NumpadEight(KeyEvent.VK_NUMPAD8),
    
    /**
     * @see KeyEvent.VK_NUMPAD9
     */
    NumpadNine(KeyEvent.VK_NUMPAD9),
    
    /**
     * @see KeyEvent.VK_MULTIPLY
     */
    Multiply(KeyEvent.VK_MULTIPLY),
    
    /**
     * @see KeyEvent.VK_ADD
     */
    Add(KeyEvent.VK_ADD),
    
    /**
     * @see KeyEvent.VK_SEPARATOR
     */
    Separator(KeyEvent.VK_SEPARATOR),
    
    /**
     * @see KeyEvent.VK_SUBTRACT
     */
    Subtract(KeyEvent.VK_SUBTRACT),
    
    /**
     * @see KeyEvent.VK_DECIMAL
     */
    Decimal(KeyEvent.VK_DECIMAL),
    
    /**
     * @see KeyEvent.VK_DIVIDE
     */
    Divide(KeyEvent.VK_DIVIDE),
    
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
    Delete(KeyEvent.VK_DELETE),
    
    /**
     * @see KeyEvent.VK_AMPERSAND
     */
    Ampersand(KeyEvent.VK_AMPERSAND),
    
    /**
     * @see KeyEvent.VK_ASTERISK
     */
    Asterisk(KeyEvent.VK_ASTERISK),
    
    /**
     * @see KeyEvent.VK_QUOTEDBL
     */
    DoubleQuote(KeyEvent.VK_QUOTEDBL),
    
    /**
     * @see KeyEvent.VK_LESS
     */
    Less(KeyEvent.VK_LESS),
    
    /**
     * @see KeyEvent.VK_GREATER
     */
    Greater(KeyEvent.VK_GREATER),
    
    /**
     * @see KeyEvent.VK_BRACELEFT
     */
    LeftBrace(KeyEvent.VK_BRACELEFT),
    
    /**
     * @see KeyEvent.VK_BRACERIGHT
     */
    RightBrace(KeyEvent.VK_BRACERIGHT),
    
    /**
     * @see KeyEvent.VK_BACK_QUOTE
     */
    BackQuote(KeyEvent.VK_BACK_QUOTE),
    
    /**
     * @see KeyEvent.VK_QUOTE
     */
    Quote(KeyEvent.VK_QUOTE),
    
    /**
     * @see KeyEvent.VK_KP_UP
     */
    NumpadUp(KeyEvent.VK_KP_UP),
    
    /**
     * @see KeyEvent.VK_KP_DOWN
     */
    NumpadDown(KeyEvent.VK_KP_DOWN),
    
    /**
     * @see KeyEvent.VK_KP_LEFT
     */
    NumpadLeft(KeyEvent.VK_KP_LEFT),
    
    /**
     * @see KeyEvent.VK_KP_RIGHT
     */
    NumpadRight(KeyEvent.VK_KP_RIGHT);
}