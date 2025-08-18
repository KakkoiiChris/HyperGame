package kakkoiigames.playkid.data.json.nodes

class JSONArray(
    val list: MutableList<JSONNode> = mutableListOf()
) : JSONNode, MutableList<JSONNode> by list {
    fun getBoolean(index: Int) =
        (list[index] as JSONBoolean).value

    fun setBoolean(index: Int, value: Boolean) {
        (list[index] as JSONBoolean).value = value
    }

    fun getByte(index: Int) =
        (list[index] as JSONNumber).value.toInt().toByte()

    fun setByte(index: Int, value: Byte) {
        (list[index] as JSONNumber).value = value.toDouble()
    }

    fun getShort(index: Int) =
        (list[index] as JSONNumber).value.toInt().toShort()

    fun setShort(index: Int, value: Short) {
        (list[index] as JSONNumber).value = value.toDouble()
    }

    fun getInt(index: Int) =
        (list[index] as JSONNumber).value.toInt()

    fun setInt(index: Int, value: Int) {
        (list[index] as JSONNumber).value = value.toDouble()
    }

    fun getLong(index: Int) =
        (list[index] as JSONNumber).value.toLong()

    fun setLong(index: Int, value: Long) {
        (list[index] as JSONNumber).value = value.toDouble()
    }

    fun getFloat(index: Int) =
        (list[index] as JSONNumber).value.toFloat()

    fun setFloat(index: Int, value: Float) {
        (list[index] as JSONNumber).value = value.toDouble()
    }

    fun getDouble(index: Int) =
        (list[index] as JSONNumber).value

    fun setDouble(index: Int, value: Double) {
        (list[index] as JSONNumber).value = value
    }

    fun getString(index: Int) =
        (list[index] as JSONString).value

    fun setString(index: Int, value: String) {
        (list[index] as JSONString).value = value
    }

    fun getArray(index: Int) =
        list[index] as JSONArray

    fun getBooleanArray(index: Int) =
        getArray(index).map { (it as JSONBoolean).value }

    fun getByteArray(index: Int) =
        getArray(index).map { (it as JSONNumber).value.toInt().toByte() }

    fun getShortArray(index: Int) =
        getArray(index).map { (it as JSONNumber).value.toInt().toShort() }

    fun getIntArray(index: Int) =
        getArray(index).map { (it as JSONNumber).value.toInt() }

    fun getLongArray(index: Int) =
        getArray(index).map { (it as JSONNumber).value.toLong() }

    fun getFloatArray(index: Int) =
        getArray(index).map { (it as JSONNumber).value.toFloat() }

    fun getDoubleArray(index: Int) =
        getArray(index).map { (it as JSONNumber).value }

    fun getStringArray(index: Int) =
        getArray(index).map { (it as JSONString).value }

    fun getArrayArray(index: Int) =
        getArray(index).map { it as JSONArray }

    fun getObjectArray(index: Int) =
        getArray(index).map { it as JSONObject }

    fun getObject(index: Int) =
        list[index] as JSONObject

    override fun printNode(indent: Int, name: String) = buildString {
        append(super.printNode(indent, name))

        append("[\n")

        var first = true

        this@JSONArray.forEach {
            if (!first) {
                append(",\n")
            }

            append(it.printNode(indent + 1))

            first = false
        }

        append('\n')

        repeat(indent) { append("  ") }

        append(']')
    }

    override fun toString() = printNode()
}