package info.xiaomo.core.filter;

import info.xiaomo.core.utils.TimeUtil;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : xiaomo
 */
public class CustomDateSerializerFilter extends ValueSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jsonGenerator, SerializationContext context) {
        SimpleDateFormat sdf = new SimpleDateFormat(TimeUtil.DEFAULT_FORMAT2);
        jsonGenerator.writeString(sdf.format(value));
    }
}
