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

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_is_not_ISO639_language_code_and_has_3_under_line() {
        //given
        testStr = "a_b_c_d";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_is_not_ISO639_language_code_and_has_no_under_line() {
        //given
        testStr = "abcd";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_1_under_line_and_1st_part_is_not_ISO639() {
        //given
        testStr = "abcd_cd";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_1_under_line_and_2nd_part_is_all_lower_case() {
        //given
        testStr = "ab_cd";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_1_under_line_and_2nd_part_length_eq_3() {
        //given
        testStr = "ab_CDE";
        //when
        executeTestMethod();
        //then
    }

    @Test
    public void should_return_locale_when_str_has_1_under_line_and_2nd_part_is_ISO3166() {
        //given
        testStr = "ab_CD";
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("ab", "CD"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_1_under_line_and_2nd_part_is_empty() {
        //given
        testStr = "ab_";
        //when
        executeTestMethod();
        //then
    }

    @Test
    public void should_return_locale_when_str_has_1_under_line_and_2nd_part_is_numeric_area_code() {
        //given
        testStr = "ab_123";
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("ab", "123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_2_under_line_and_1st_part_is_not_ISO639() {
        //given
        testStr = "abcd_123_";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_2_under_line_and_2nd_part_is_not_ISO3166() {
        //given
        testStr = "ab_cd_";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_2_under_line_and_2nd_part_is_not_numeric_area_code() {
        //given
        testStr = "ab_12_";
        //when
        executeTestMethod();
        //then
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_ex_when_str_has_2_under_line_and_both_2nd_part_and_3th_part_are_empty() {
        //given
        testStr = "ab__";
        //when
        executeTestMethod();
        //then
    }

    @Test
    public void should_return_locale_when_str_has_2_under_line_and_3th_part_length_gt_0() {
        //given
        testStr = "ab_CD_e";
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("ab", "CD", "e"));
    }

    @Test
    public void should_return_locale_when_str_has_2_under_lin_eand_2nd_part_is_numeric_area_code_and_3th_part_length_gt_0() {
        //given
        testStr = "ab_001_e";
        //when
        executeTestMethod();
        //then
        assertThat(result).isEqualToComparingFieldByField(new Locale("ab", "001", "e"));
    }
}