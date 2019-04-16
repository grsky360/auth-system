package ilio.oauth.core.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum Platform {


    Other("other");

    @Getter private String code;

    public static Platform getByCode(String code) {
        return Arrays.stream(values()).filter(c -> c.code.equals(code)).findFirst().orElse(Other);
    }
}
