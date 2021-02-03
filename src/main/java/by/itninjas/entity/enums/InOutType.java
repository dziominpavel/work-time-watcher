package by.itninjas.entity.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum InOutType {

    IN("Вход"),
    OUT("Bыход");   // первый символ, буква 'B' написана на латинице, косяк в xml

    private final String value;

    InOutType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static InOutType getByValue(String value) {
        validateValue(value);
        return mapByValue.get(value);
    }

    private static final Map<String, InOutType> mapByValue = new HashMap<>
        (Arrays.stream(InOutType.values()).collect(Collectors.toMap(InOutType::getValue, Function.identity())));



    public static void validateValue(String value) {
        if (!mapByValue.containsKey(value)) {
            throw new IllegalArgumentException("InOutType value [" + value + "] not supported.");
        }
    }


}
