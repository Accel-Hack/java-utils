package com.accelhack.accelparts.utils;

import com.accelhack.commons.utils.JapanUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JapanUtilsSTest {

  @ParameterizedTest
  @CsvSource({
    /* 英字（スペース）   */ "TanakaTaro, Tanaka Taro  ",
    /* 英字（スペースなし）*/ "TanakaTaro,TanakaTaro",
    /* かな（スペース）   */ "田中太郎, 田中太郎",
    /* かな（スペースなし）*/ "田中太郎, 田  中　 太　　　郎",
  })
  void removeWhitespace(String expected, String hiraganaStr) {
    assertEquals(expected, JapanUtils.removeWhitespace(hiraganaStr));
  }

  @ParameterizedTest
  @CsvSource({
    /* 全角カナ一覧 */ "true, ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんゔゕゖ",
    /* 空文字　　　 */ "true, ''",
    /* 全角かな　　 */ "false, あアイウエオ",
    /* 数字　　　　 */ "false, あ20220912",
  })
  void isZenkakuHiragana(boolean expected, String hiraganaStr) {
    assertEquals(expected, JapanUtils.isZenkakuHiragana(hiraganaStr));
  }

  @ParameterizedTest
  @CsvSource({
    /* 全角カナ一覧 */ "true, ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶ, ",
    /* 空文字　　　 */ "true, ''",
    /* 半角カナ一覧 */ "false, ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｯｬｭｮｰｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟｳﾞ, ",
    /* 全角かな　　 */ "false, アあいうえお",
    /* 数字　　　　 */ "false, ア20220912",
  })
  void isZenkakuKatakana(boolean expected, String katakanaStr) {
    assertEquals(expected, JapanUtils.isZenkakuKatakana(katakanaStr));
  }

  @ParameterizedTest
  @CsvSource({
    /* 正常系　　　 */ "true, 1638001",
    /* 数字以外　　 */ "false, あいうえお",
    /* 規定外の長さ */ "false, 20220912",
  })
  void isPostalCode(boolean expected, String postalCode) {
    assertEquals(expected, JapanUtils.isPostalCode(postalCode));
  }

  @ParameterizedTest
  @CsvSource({
    /* 正常系　　　 */ "true, 09012345678",
    /* 数字以外　　 */ "false, あいうえお",
    /* 規定外の長さ */ "false, 20220912",
  })
  void isPhoneNumber(boolean expected, String str) {
    assertEquals(expected, JapanUtils.isPhoneNumber(str));
  }
}
