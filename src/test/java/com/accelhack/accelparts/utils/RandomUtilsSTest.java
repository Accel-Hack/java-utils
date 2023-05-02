package com.accelhack.accelparts.utils;

import com.accelhack.commons.utils.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomUtilsSTest {

  @ParameterizedTest
  @CsvSource({
    /* 長さ10 */ "10",
    /* 長さ20 */ "20",
  })
  void alphaNumeric01(int length) {
    String generatedString = RandomUtils.alphaNumeric(length);
    assertEquals(length, generatedString.length());
    generatedString.chars().forEach(c ->
      assertTrue(Character.isLetterOrDigit(c)));
  }

  @Test
  @DisplayName("Exclude numbers")
  void alphaNumeric02() {
    String generatedString = RandomUtils.alphaNumeric(10000, '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
    generatedString.chars().forEach(c -> assertTrue(Character.isLetter(c)));
  }
}
