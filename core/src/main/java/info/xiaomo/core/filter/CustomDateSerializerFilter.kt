package info.xiaomo.core.filter

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import info.xiaomo.core.untils.TimeUtil
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author : xiaomo
 */
class CustomDateSerializerFilter : JsonSerializer<Date>() {

    @Throws(IOException::class)
    override fun serialize(value: Date, jsonGenerator: JsonGenerator, provider: SerializerProvider) {
        val sdf = SimpleDateFormat(TimeUtil.DEFAULT_FORMAT2)
        jsonGenerator.writeString(sdf.format(value))
    }
}