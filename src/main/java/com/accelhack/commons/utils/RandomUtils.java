package com.accelhack.commons.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomUtils {

  /**
   * random alpha numeric string
   * @param length length of generated string
   * @return result
   */
  public static String alphaNumeric(int length, Character... excludes) {
    // ASCII範囲–英数字(0-9、a-z、A-Z)
    final String chars = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    final String excludeRegex = String.join("", Arrays.stream(excludes).map(String::valueOf).toList());
    final String filteredChars = chars.replaceAll(String.format("[%s]", excludeRegex), "");

    SecureRandom random = new SecureRandom();

    return IntStream.range(0, length)
      .map(i -> random.nextInt(filteredChars.length()))
      .mapToObj(randomIndex -> String.valueOf(filteredChars.charAt(randomIndex)))
      .collect(Collectors.joining());
  }
}
