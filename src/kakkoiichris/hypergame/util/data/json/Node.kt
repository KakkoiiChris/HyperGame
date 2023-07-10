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

sealed class Node(val location: Location, var value: Any) {
    fun asValue() = (this as? Value) ?: error("Node is not a value! @ $location")

    fun asBoolean() = (asValue().value as? Boolean) ?: error("Node is not a boolean value! @ $location")

    fun asByte() = ((asValue().value as? Double) ?: error("Node is not a byte value! @ $location")).toInt().toByte()

    fun asShort() = ((asValue().value as? Double) ?: error("Node is not a short value! @ $location")).toInt().toShort()

    fun asInt() = ((asValue().value as? Double) ?: error("Node is not an int value! @ $location")).toInt()

    fun asLong() = ((asValue().value as? Double) ?: error("Node is not a long value! @ $location")).toLong()

    fun asFloat() = ((asValue().value as? Double) ?: error("Node is not a float value! @ $location")).toFloat()

    fun asDouble() = (asValue().value as? Double) ?: error("Node is not a double value! @ $location")

    fun asChar() = (asValue().value as? Char) ?: error("Node is not a char value! @ $location")

    fun asString() = (asValue().value as? String) ?: error("Node is not a string value! @ $location")

    fun asArray() = this as? Array ?: error("Node is not an array! @ $location")

    fun asBooleanArray() = asArray().map {
        (it.asValue().value as? Boolean) ?: error("Node is not a boolean array! @ $location")
    }.toBooleanArray()

    fun asByteArray() = asArray().map {
        ((it.asValue().value as? Double) ?: error("Node is not a byte array! @ $location")).toInt().toByte()
    }.toByteArray()

    fun asShortArray() = asArray().map {
        ((it.asValue().value as? Double) ?: error("Node is not a short array! @ $location")).toInt().toShort()
    }.toShortArray()

    fun asIntArray() = asArray().map {
        ((it.asValue().value as? Double) ?: error("Node is not an int array! @ $location")).toInt()
    }.toIntArray()

    fun asLongArray() = asArray().map {
        ((it.asValue().value as? Double) ?: error("Node is not a long array! @ $location")).toLong()
    }.toLongArray()

    fun asFloatArray() = asArray().map {
        ((it.asValue().value as? Double) ?: error("Node is not a float array! @ $location")).toFloat()
    }.toFloatArray()

    fun asDoubleArray() = asArray().map {
        (it.asValue().value as? Double) ?: error("Node is not a double array! @ $location")
    }.toDoubleArray()

    fun asCharArray() = asArray().map {
        (it.asValue().value as? Char) ?: error("Node is not a char array! @ $location")
    }.toCharArray()

    fun asStringArray() = asArray().map {
        (it.asValue().value as? String) ?: error("Node is not a string array! @ $location")
    }.toTypedArray()

    fun asArrayArray() = asArray().map {
        it.asArray()
    }.toTypedArray()

    fun asObjectArray() = asArray().map {
        it.asObject()
    }.toTypedArray()

    fun asObject() = this as? Object ?: error("Node is not an object! @ $location")

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
                        Boolean::class -> value.asBoolean()
                        Byte::class    -> value.asByte()
                        Short::class   -> value.asShort()
                        Int::class     -> value.asInt()
                        Long::class    -> value.asLong()
                        Float::class   -> value.asFloat()
                        Double::class  -> value.asDouble()
                        Char::class    -> value.asChar()
                        String::class  -> value.asString()
                        else           -> null
                    }

                    is Array  -> when (type) {
                        BooleanArray::class         -> value.asBooleanArray()
                        ByteArray::class            -> value.asByteArray()
                        ShortArray::class           -> value.asShortArray()
                        IntArray::class             -> value.asIntArray()
                        LongArray::class            -> value.asLongArray()
                        FloatArray::class           -> value.asFloatArray()
                        DoubleArray::class          -> value.asDoubleArray()
                        CharArray::class            -> value.asCharArray()
                        kotlin.Array<String>::class -> value.asStringArray()
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

