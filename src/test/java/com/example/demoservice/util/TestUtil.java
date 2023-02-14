package com.example.demoservice.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;

public class TestUtil {

    @SneakyThrows
    public static String jsonToString(Object request) {
        return JsonMapper.builder().serializationInclusion(JsonInclude.Include.NON_NULL)
                .build().writeValueAsString(request);

    }
}
