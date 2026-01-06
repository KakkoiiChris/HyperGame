package kakkoiichris.hypergame.data.json.nodes

import kakkoiichris.hypergame.data.json.JSONMember
import kotlin.reflect.KParameter
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

class JSONObject(val map: MutableMap<String, JSONNode> = mutableMapOf()) : JSONNode,
    MutableMap<String, JSONNode> by map {
    fun getBoolean(key: String) =
        (map[key] as JSONBoolean).value

    fun setBoolean(key: String, value: Boolean) {
        (map[key] as JSONBoolean).value = value
    }

    fun addBoolean(key: String, value: Boolean) {
        map[key] = JSONBoolean(value)
    }

    fun removeBoolean(key: String) =
        (map.remove(key) as JSONBoolean).value

    fun getByte(key: String) =
        (map[key] as JSONNumber).value.toInt().toByte()

    fun setByte(key: String, value: Byte) {
        (map[key] as JSONNumber).value = value.toDouble()
    }

    fun addByte(key: String, value: Byte) {
        map[key] = JSONNumber(value.toDouble())
    }

    fun removeByte(key: String) =
        (map.remove(key) as JSONNumber).value.toInt().toByte()

    fun getShort(key: String) =
        (map[key] as JSONNumber).value.toInt().toShort()

    fun setShort(key: String, value: Short) {
        (map[key] as JSONNumber).value = value.toDouble()
    }

    fun addShort(key: String, value: Short) {
        map[key] = JSONNumber(value.toDouble())
    }

    fun removeShort(key: String) =
        (map.remove(key) as JSONNumber).value.toInt().toShort()

    fun getInt(key: String) =
        (map[key] as JSONNumber).value.toInt()

    fun setInt(key: String, value: Int) {
        (map[key] as JSONNumber).value = value.toDouble()
    }

    fun addInt(key: String, value: Int) {
        map[key] = JSONNumber(value.toDouble())
    }

    fun removeInt(key: String) =
        (map.remove(key) as JSONNumber).value.toInt()

    fun getLong(key: String) =
        (map[key] as JSONNumber).value.toLong()

    fun setLong(key: String, value: Long) {
        (map[key] as JSONNumber).value = value.toDouble()
    }

    fun addLong(key: String, value: Long) {
        map[key] = JSONNumber(value.toDouble())
    }

    fun removeLong(key: String) =
        (map.remove(key) as JSONNumber).value.toLong()

    fun getFloat(key: String) =
        (map[key] as JSONNumber).value.toFloat()

    fun setFloat(key: String, value: Float) {
        (map[key] as JSONNumber).value = value.toDouble()
    }

    fun addFloat(key: String, value: Float) {
        map[key] = JSONNumber(value.toDouble())
    }

    fun removeFloat(key: String) =
        (map.remove(key) as JSONNumber).value.toFloat()

    fun getDouble(key: String) =
        (map[key] as JSONNumber).value

    fun setDouble(key: String, value: Double) {
        (map[key] as JSONNumber).value = value
    }

    fun addDouble(key: String, value: Double) {
        map[key] = JSONNumber(value)
    }

    fun removeDouble(key: String) =
        (map.remove(key) as JSONNumber).value

    fun getString(key: String) =
        (map[key] as JSONString).value

    fun setString(key: String, value: String) {
        (map[key] as JSONString).value = value
    }

    fun addString(key: String, value: String) {
        map[key] = JSONString(value)
    }

    fun removeString(key: String) =
        (map.remove(key) as JSONString).value

    fun getArray(key: String) =
        map[key] as JSONArray

    fun getByteArray(key: String) =
        getArray(key).map { (it as JSONNumber).value.toInt().toByte() }

    fun getShortArray(key: String) =
        getArray(key).map { (it as JSONNumber).value.toInt().toShort() }

    fun getIntArray(key: String) =
        getArray(key).map { (it as JSONNumber).value.toInt() }

    fun getLongArray(key: String) =
        getArray(key).map { (it as JSONNumber).value.toLong() }

    fun getFloatArray(key: String) =
        getArray(key).map { (it as JSONNumber).value.toFloat() }

    fun getDoubleArray(key: String) =
        getArray(key).map { (it as JSONNumber).value }

    fun getStringArray(key: String) =
        getArray(key).map { (it as JSONString).value }

    fun getArrayArray(key: String) =
        getArray(key).map { it as JSONArray }

    fun getObjectArray(key: String) =
        getArray(key).map { it as JSONObject }

    fun getObject(key: String) =
        map[key] as JSONObject

    inline fun <reified T : Any> create(): T {
        val constructor = T::class.primaryConstructor!!

        val params = mutableMapOf<KParameter, Any>()

        for (param in constructor.parameters) {
            val member = param.findAnnotation<JSONMember>()!!.name

            val value = when (param.type) {
                Boolean -> getBoolean(member)
                Byte    -> getByte(member)
                Short   -> getShort(member)
                Int     -> getInt(member)
                Long    -> getLong(member)
                Float   -> getFloat(member)
                Double  -> getDouble(member)
                String  -> getString(member)
                else    -> TODO()
            }

            params[param] = value
        }

        return constructor.callBy(params)
    }

    /*@Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any> create(clazz: KClass<T>): T? {
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
    }*/

    override fun printNode(indent: Int, name: String) = buildString {
        append(super.printNode(indent, name))

        append("{\n")

        var first = true

        this@JSONObject.forEach { (k, v) ->
            if (!first) {
                append(",\n")
            }

            append(v.printNode(indent + 1, k))

            first = false
        }

        append('\n')

        repeat(indent) { append("  ") }

        append('}')
    }

    override fun toString() = printNode()
}