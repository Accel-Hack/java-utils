package com.accelhack.commons.utils;

import java.util.regex.Pattern;

public class JapanUtils {

  /**
   * remove all whitespace of string value
   * @param value string with no whitespace
   * @return result
   */
  public static String removeWhitespace(String value) {
    return value.replaceAll("\\s|　", "");
  }

  /**
   * check if string only contains zenkaku katakana
   * @param value string to check
   * @return result
   */
  public static boolean isZenkakuHiragana(String value) {
    String hiraganaInput = value.replace(" ", "");
    if (hiraganaInput.equals("")) {
      return true; // 空文字ならOKとする.
    }
    String regex = "^[\\u3040-\\u309F]*$";
    return hiraganaInput.chars()
      .mapToObj(c -> (char) c)
      .allMatch(c -> Pattern.matches(regex, String.valueOf(c)));
  }

  /**
   * check if string only contains zenkaku katakana
   * @param value string to check
   * @return result
   */
  public static boolean isZenkakuKatakana(String value) {
    String katakanaInput = value.replace(" ", "");
    if (katakanaInput.equals("")) {
      return true; // 空文字ならOKとする.
    }
    String regex = "^[ァ-ヶー]*$";
    return katakanaInput.chars()
      .mapToObj(c -> (char) c)
      .allMatch(c -> Pattern.matches(regex, String.valueOf(c)));
  }

  /**
   * check if zipcode is proper format
   * @param zipcode code to check
   * @return result
   */
  public static boolean isPostalCode(String zipcode) {
    String regex = "^(\\d{7})$";
    return zipcode.matches(regex);
  }

  /**
   * check if phoneNumber is proper format
   * @param phoneNumber phoneNumber to check
   * @return result
   */
  public static boolean isPhoneNumber(String phoneNumber) {
    String regex = "^(\\d{10,12})$";
    return phoneNumber.matches(regex);
  }
}
