package org.acme.core.unit;

import org.acme.core.utils.EmailUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnitTest {

    @ParameterizedTest(name = "Assert that valid email return true")
    @MethodSource("provideValidEmail")
    void shouldReturnTrueForValidEmail(String email){
        assertTrue(EmailUtils.isValidEmail(email));
    }

    @ParameterizedTest(name = "Assert that invalid email return false")
    @MethodSource("provideInvalidEmail")
    void shouldReturnFalseForInvalidEmail(String email){
        assertFalse(EmailUtils.isValidEmail(email));
    }

    @ParameterizedTest(name = "Assert that empty and null email return false")
    @NullAndEmptySource
    void shouldReturnFalseForEmptyAndNullEmail(String email){
        assertFalse(EmailUtils.isValidEmail(email));
    }

    @ParameterizedTest(name = "Assert that nonsense email return false")
    @ValueSource(strings = {"potato", "banana"})
    void shouldReturnFalseForNonSenseEmail(String email){
        assertFalse(EmailUtils.isValidEmail(email));
    }


    public static Stream<Arguments> provideValidEmail() {
        return Stream.of(
                Arguments.of("paulo@gmail.com"),
                Arguments.of("pedro@gmail.com")
        );
    }

    public static Stream<Arguments> provideInvalidEmail() {
        return Stream.of(
                Arguments.of("paulo#gmail.com"),
                Arguments.of("pedro#gmail.com")
        );
    }

}
