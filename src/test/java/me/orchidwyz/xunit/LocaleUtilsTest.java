package me.orchidwyz.xunit;

import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class LocaleUtilsTest {

    private static final String EMPTY = "";

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
        assertThat(result).isNull();
    }

    @Test
    public void should_return_empty_locale_when_str_is_empty() {
        //given
        testStr = EMPTY;
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(EMPTY, EMPTY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_str_contains_hash_key() {
        //given
        testStr = "ab#c";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_str_length_eq_1() {
        //given
        testStr = "a";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_str_start_with_under_line_and_length_eq_2() {
        //given
        testStr = "_a";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_start_with_under_line_and_length_eq_3_and_the_2nd_letter_is_lower_case() {
        //given
        testStr = "_Ab";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_start_with_under_line_and_length_eq_3_and_is_all_lower_case() {
        //given
        testStr = "_ab";
        //when
        executeTestMethod();
        //then
    }

    @Test
    public void should_return_locale_when_str_start_with_under_line_and_length_eq_3_and_is_all_upper_case() {
        //given
        testStr = "_AB";
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(EMPTY, "AB"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_start_with_under_line_and_length_eq_4() {
        //given
        testStr = "_ABC";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_start_with_under_line_and_length_eq_5_and_4th_letter_is_not_under_line() {
        //given
        testStr = "_ABCD";
        //when
        executeTestMethod();
        //then
    }

    @Test
    public void should_throw_ex_when_str_start_with_under_line_and_length_eq_6_and_4th_letter_is_under_line() {
        //given
        testStr = "_AB_C";
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale(EMPTY, "AB", "C"));
    }

    @Test
    public void should_return_locale_when_str_is_ISO639_language_code() {
        //given
        testStr = "abc";
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("ABC"));
    }
}