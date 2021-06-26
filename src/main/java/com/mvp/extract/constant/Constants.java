package com.mvp.extract.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {
    public static final List<String> ALPHA_NUMBER_LIST = Collections.unmodifiableList(Arrays.asList("ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"));
    public static final String CONTENT_TYPE_HTML = "html";
    public static final String BASE_PATH = "character-management";
    public static final String NUMERICS = "numerics";

    private Constants() {
    }

}
