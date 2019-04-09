package ilio.auth.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

public class FastJsonCodec extends BaseCodec {

    private final static ParserConfig autoType = new ParserConfig();
    static {
        autoType.setAutoTypeSupport(true);
    }
    @Override
    public Decoder<Object> getValueDecoder() {
        return (buffer, state) -> {
            return JSON.parseObject(new ByteBufInputStream(buffer), IOUtils.UTF8, Object.class, autoType);
        };
    }

    @Override
    public Encoder getValueEncoder() {
        return (in) -> {
            ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
            try {
                ByteBufOutputStream out = new ByteBufOutputStream(buffer);
                JSON.writeJSONString(out, in, SerializerFeature.WriteClassName);
                return out.buffer();
            } catch (Exception e) {
                buffer.release();
                throw e;
            }
        };
    }
}
