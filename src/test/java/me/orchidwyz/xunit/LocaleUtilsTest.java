package me.orchidwyz.xunit;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Locale;

public class LocaleUtilsTest {
    private LocaleUtils localeUtils = new LocaleUtils();
    private String testStr;
    private Locale result;

    private void executeTestMethod() {
        result = localeUtils.toLocale(testStr);
    }

    @Test
    public void should_return_null_when_str_is_null() {
        //given
        testStr = null;
        //when
        executeTestMethod();
        //then
        Assertions.assertThat(result).isNull();
    }
}