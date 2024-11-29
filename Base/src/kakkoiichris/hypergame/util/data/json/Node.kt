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

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

sealed class Node(private val location: Location, var value: Any) {
    fun asValueOrNull() = this as? Value

    fun asValue() = (this as? Value)
        ?: error("Node is not a value! @ $location")

    fun asBooleanOrNull(): Boolean? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return value as? Boolean
            ?: error("Node is not a boolean value! @ $location")
    }

    fun asBoolean(): Boolean {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return value as? Boolean
            ?: error("Node is not a boolean value! @ $location")
    }

    fun asByteOrNull(): Byte? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return (value as? Double
            ?: error("Node is not a byte value! @ $location")).toInt().toByte()
    }

    fun asByte(): Byte {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return (value as? Double
            ?: error("Node is not a byte value! @ $location")).toInt().toByte()
    }

    fun asShortOrNull(): Short? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return (value as? Double
            ?: error("Node is not a short value! @ $location")).toInt().toShort()
    }

    fun asShort(): Short {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return (value as? Double
            ?: error("Node is not a short value! @ $location")).toInt().toShort()
    }

    fun asIntOrNull(): Int? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return (value as? Double
            ?: error("Node is not an int value! @ $location")).toInt()
    }

    fun asInt(): Int {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return (value as? Double
            ?: error("Node is not an int value! @ $location")).toInt()
    }

    fun asLongOrNull(): Long? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return (value as? Double
            ?: error("Node is not a long value! @ $location")).toLong()
    }

    fun asLong(): Long {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return (value as? Double
            ?: error("Node is not a long value! @ $location")).toLong()
    }

    fun asFloatOrNull(): Float? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return (value as? Double
            ?: error("Node is not a float value! @ $location")).toFloat()
    }

    fun asFloat(): Float {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return (value as? Double
            ?: error("Node is not a float value! @ $location")).toFloat()
    }

    fun asDoubleOrNull(): Double? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return value as? Double
            ?: error("Node is not a double value! @ $location")
    }

    fun asDouble(): Double {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return value as? Double
            ?: error("Node is not a double value! @ $location")
    }

    fun asCharOrNull(): Char? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return (value as? String)?.takeIf { it.length == 1 }?.get(0)
            ?: error("Node is not a char value! @ $location")
    }

    fun asChar(): Char {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return (value as? String)?.takeIf { it.length == 1 }?.get(0)
            ?: error("Node is not a char value! @ $location")
    }

    fun asStringOrNull(): String? {
        val value = asValue().value

        if (value === Null) {
            return null
        }

        return value as? String
            ?: error("Node is not a string value! @ $location")
    }

    fun asString(): String {
        val value = asValue().value

        if (value === Null) {
            error("Node cannot be null! @ $location")
        }

        return value as? String
            ?: error("Node is not a string value! @ $location")
    }

    fun asArrayOrNull() = this as? Array

    fun asArray() = this as? Array
        ?: error("Node is not an array! @ $location")

    fun asBooleanArrayOrNull() = asArrayOrNull()?.map(Node::asBoolean)

    fun asBooleanArray() = asArray().map(Node::asBoolean)

    fun asByteArrayOrNull() = asArrayOrNull()?.map(Node::asByte)?.toByteArray()

    fun asByteArray() = asArray().map(Node::asByte).toByteArray()

    fun asShortArrayOrNull() = asArrayOrNull()?.map(Node::asShort)?.toShortArray()

    fun asShortArray() = asArray().map(Node::asShort).toShortArray()

    fun asIntArrayOrNull() = asArrayOrNull()?.map(Node::asInt)?.toIntArray()

    fun asIntArray() = asArray().map(Node::asInt).toIntArray()

    fun asLongArrayOrNull() = asArrayOrNull()?.map(Node::asLong)?.toLongArray()

    fun asLongArray() = asArray().map(Node::asLong).toLongArray()

    fun asFloatArrayOrNull() = asArrayOrNull()?.map(Node::asFloat)?.toFloatArray()

    fun asFloatArray() = asArray().map(Node::asFloat).toFloatArray()

    fun asDoubleArrayOrNull() = asArrayOrNull()?.map(Node::asDouble)?.toDoubleArray()

    fun asDoubleArray() = asArray().map(Node::asDouble).toDoubleArray()

    fun asCharArrayOrNull() = asArrayOrNull()?.map(Node::asChar)?.toCharArray()

    fun asCharArray() = asArray().map(Node::asChar).toCharArray()

    fun asStringArrayOrNull() = asArrayOrNull()?.map(Node::asString)?.toTypedArray()

    fun asStringArray() = asArray().map(Node::asString).toTypedArray()

    fun asArrayArrayOrNull() = asArrayOrNull()?.map(Node::asArray)?.toTypedArray()

    fun asArrayArray() = asArray().map(Node::asArray).toTypedArray()

    fun asObjectArrayOrNull() = asArrayOrNull()?.map(Node::asObject)?.toTypedArray()

    fun asObjectArray() = asArray().map(Node::asObject).toTypedArray()

    fun asObjectOrNull() = this as? Object

    fun asObject() = this as? Object
        ?: error("Node is not an object! @ $location")

    abstract fun format(indent: Int): String

    override fun toString() =
        format(0)

    class Value(location: Location, value: Any) : Node(location, value) {
        override fun format(indent: Int) = when (value) {
            is String -> "\"$value\""

            else      -> "$value"
        }
    }

    class Array(location: Location, list: MutableList<Node> = mutableListOf()) : Node(location, list),
                                                                                 MutableList<Node> by list {
        fun addValue(value: Any) = add(Value(Location.none, value))

        fun addValue(index: Int, value: Any) = add(index, Value(Location.none, value))

        fun addArray() = add(Array(Location.none))

        fun addArray(index: Int) = add(index, Array(Location.none))

        fun addObject() = add(Object(Location.none))

        fun addObject(index: Int) = add(index, Object(Location.none))

        override fun format(indent: Int) = buildString {
            val whitespace = " ".repeat(indent)

            append("$whitespace[\n")

            var first = true

            this@Array.forEach { node ->
                if (!first) {
                    append(",\n")
                }

                append("$whitespace${node.format(indent + 1)}")

                first = false
            }

            append("\n$whitespace]")
        }
    }

    class Object(location: Location, map: MutableMap<String, Node> = mutableMapOf()) : Node(location, map),
                                                                                       MutableMap<String, Node> by map {
        fun addValue(name: String, value: Any) = put(name, Value(Location.none, value))

        fun addArray(name: String) = put(name, Array(Location.none))

        fun addObject(name: String) = put(name, Object(Location.none))

        @Suppress("UNCHECKED_CAST")
        fun <T : Any> create(clazz: KClass<T>): T? {
            val parameters = clazz.primaryConstructor?.parameters ?: error("No Constructor Parameters!")

            val arguments = parameters.associateWith { kParameter ->
                val annotation =
                    kParameter.annotations.firstOrNull { it is JSONMember } as? JSONMember

                val name = annotation?.name?.takeIf { it.isNotEmpty() } ?: kParameter.name

                val value = this[name] ?: error("Cannot find value at '${name}'!")

                val type = kParameter.type.classifier as? KClass<T> ?: error("")

                val realValue: Any? = when (value) {
                    is Value  -> when (type) {
                        Boolean::class -> value.asBooleanOrNull()
                        Byte::class    -> value.asByteOrNull()
                        Short::class   -> value.asShortOrNull()
                        Int::class     -> value.asIntOrNull()
                        Long::class    -> value.asLongOrNull()
                        Float::class   -> value.asFloatOrNull()
                        Double::class  -> value.asDoubleOrNull()
                        Char::class    -> value.asCharOrNull()
                        String::class  -> value.asStringOrNull()
                        else           -> null
                    }

                    is Array  -> when (type) {
                        BooleanArray::class         -> value.asBooleanArrayOrNull()
                        ByteArray::class            -> value.asByteArrayOrNull()
                        ShortArray::class           -> value.asShortArrayOrNull()
                        IntArray::class             -> value.asIntArrayOrNull()
                        LongArray::class            -> value.asLongArrayOrNull()
                        FloatArray::class           -> value.asFloatArrayOrNull()
                        DoubleArray::class          -> value.asDoubleArrayOrNull()
                        CharArray::class            -> value.asCharArrayOrNull()
                        kotlin.Array<String>::class -> value.asStringArrayOrNull()
                        else                        -> null
                    }

                    is Object -> {
                        val subClazz = Class.forName(annotation?.classType ?: "")

                        value.create(subClazz.kotlin)
                    }
                }

                realValue
            }

            return clazz.primaryConstructor?.callBy(arguments)
        }

        override fun format(indent: Int) = buildString {
            val whitespace = " ".repeat(indent)

            append("$whitespace{\n")

            var first = true

            this@Object.forEach { key, value ->
                if (!first) {
                    append(",\n")
                }

                append("$whitespace\"$key\" : ${value.format(indent + 1)}")

                first = false
            }

            append("\n$whitespace}")
        }
    }
}

