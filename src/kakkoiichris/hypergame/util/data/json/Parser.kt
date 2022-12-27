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
package kakkoiichris.hypergame.util.data.json

import kakkoiichris.hypergame.util.data.json.Token.Type
import kakkoiichris.hypergame.util.data.json.Token.Type.*

internal class Parser(private val lexer: Lexer) {
    private var currentToken = lexer.next()
    
    fun parse() =
        parseObject()
    
    private fun step() {
        currentToken = lexer.next()
    }
    
    private fun match(type: Type) =
        currentToken.type == type
    
    private fun mustSkip(vararg types: Type) {
        for (type in types) {
            if (match(type)) {
                step()
            }
            else {
                error("Invalid token type '${currentToken.type}'!")
            }
        }
    }
    
    private fun parseObject(): Node.Object {
        mustSkip(LeftBrace)
        
        val members = mutableMapOf<String, Node>()
        
        var first = true
        
        do {
            if (!first) {
                mustSkip(Comma)
            }
            
            val name =
                currentToken.value as? String ?: error("JSON member name '${currentToken.value}' must be a string!")
            
            mustSkip(Value, Colon)
            
            val node = when {
                match(LeftBrace)  -> parseObject()
                
                match(LeftSquare) -> parseArray()
                
                else              -> parseValue()
            }
            
            members[name] = node
            
            first = false
        }
        while (match(Comma))
        
        mustSkip(RightBrace)
        
        return Node.Object(members)
    }
    
    private fun parseArray(): Node.Array {
        mustSkip(LeftSquare)
        
        val elements = mutableListOf<Node>()
        
        var first = true
        
        do {
            if (!first) {
                mustSkip(Comma)
            }
    
            val node = when {
                match(LeftBrace)  -> parseObject()
        
                match(LeftSquare) -> parseArray()
        
                else              -> parseValue()
            }
            
            elements.add(node)
            
            first = false
        }
        while (match(Comma))
        
        mustSkip(RightSquare)
        
        return Node.Array(elements)
    }
    
    private fun parseValue(): Node.Value {
        val value = currentToken.value
        
        mustSkip(Value)
        
        return Node.Value(value)
    }
}
