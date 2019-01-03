package com.github.vanbv.num.demo;

import com.github.vanbv.num.AbstractHttpMappingHandler;
import com.github.vanbv.num.annotation.*;
import com.github.vanbv.num.json.JsonParser;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpVersion;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class HelloHttpHandler extends AbstractHttpMappingHandler {

    public HelloHttpHandler(JsonParser parser) {
        super(parser);
    }

    @Get("/test/get/{id}")
    public DefaultFullHttpResponse test(@PathParam(value = "id") int id, @QueryParam(value = "msg") String msg) {
        return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK,
                Unpooled.copiedBuffer("{id: '" + id +"', msg: '" + msg + "'}", StandardCharsets.UTF_8));
    }

    @Post("/test/post")
    public DefaultFullHttpResponse save(@RequestBody Message message) {
        return new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK,
                Unpooled.copiedBuffer("{id: '" + message.getId() +"', msg: '" + message.getMessage() + "'}",
                        StandardCharsets.UTF_8));
    }
}
