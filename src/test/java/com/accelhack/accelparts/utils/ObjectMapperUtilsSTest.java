package com.accelhack.accelparts.utils;

import com.accelhack.commons.utils.ObjectMapperUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ObjectMapperUtilsSTest {

  @Nested
  class StringTest {
    public static class Entity {
      public String string;

      public Entity() {
      }

      public Entity(String string) {
        this.string = string;
      }

      public String getString() {
        return string;
      }
    }

    @ParameterizedTest
    @CsvSource({
      /* strings */ "{\"string\":\"\\\" 1234567890abcdefghijljmnopqrstuvxwyz[](){}\"} ,\" 1234567890abcdefghijljmnopqrstuvxwyz[](){}",
    })
    void encodeStringTest(String expected, String value) throws JsonProcessingException {
      assertEquals(expected, ObjectMapperUtils.writeValueAsString(new Entity(value)));
    }

    @Test
    void encodeEmptyStringTest() throws JsonProcessingException {
      assertEquals("{\"string\":\"\"}", ObjectMapperUtils.writeValueAsString(new Entity("")));
    }

    @Test
    void encodeNullStringTest() throws JsonProcessingException {
      assertEquals("{\"string\":null}", ObjectMapperUtils.writeValueAsString(new Entity(null)));
    }

    @ParameterizedTest
    @CsvSource({
      /* strings */ "\" 1234567890abcdefghijljmnopqrstuvxwyz[](){}, {\"string\":\"\\\" 1234567890abcdefghijljmnopqrstuvxwyz[](){}\"}",
    })
    void decodeStringTest(String expected, String json) throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue(json, Entity.class);
      assertEquals(expected, result.getString());
    }

    @Test
    void decodeEmptyStringTest() throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue("{\"string\":\"\"}", Entity.class);
      assertEquals("", result.getString());
    }

    @Test
    void decodeNullStringTest() throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue("{\"string\":null}", Entity.class);
      assertNull(result.getString());
    }
  }

  @Nested
  class IntTest {
    public static class Entity {
      public Integer integer;

      public Entity() {
      }

      public Entity(Integer integer) {
        this.integer = integer;
      }

      public Integer getInteger() {
        return integer;
      }
    }

    @ParameterizedTest
    @CsvSource(value = {
      /* positive */ "{\"integer\":1}   ,    1",
      /* negative */ "{\"integer\":-1}  ,   -1",
      /* null     */ "{\"integer\":null}, null",
    }, nullValues = {"null"})
    void encodeIntTest(String expected, Integer value) throws JsonProcessingException {
      assertEquals(expected, ObjectMapperUtils.writeValueAsString(new Entity(value)));
    }

    @ParameterizedTest
    @CsvSource(value = {
      /* positive */ "  1 , {\"integer\":1}",
      /* negative */ "  -1, {\"integer\":-1}",
      /* null     */ "null, {\"integer\":null}",
    }, nullValues = {"null"})
    void decodeIntTest(Integer expected, String json) throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue(json, Entity.class);
      assertEquals(expected, result.getInteger());
    }
  }

  @Nested
  class BooleanTest {
    public static class Entity {
      public Boolean bool;

      public Entity() {
      }

      public Entity(Boolean bool) {
        this.bool = bool;
      }

      public Boolean getBool() {
        return bool;
      }
    }

    @ParameterizedTest
    @CsvSource(value = {
      /* true  */ "{\"bool\":true}   ,  true",
      /* false */ "{\"bool\":false}  , false",
      /* null  */ "{\"bool\":null}   ,  null",
    }, nullValues = {"null"})
    void encodeIntTest(String expected, Boolean value) throws JsonProcessingException {
      assertEquals(expected, ObjectMapperUtils.writeValueAsString(new Entity(value)));
    }

    @ParameterizedTest
    @CsvSource(value = {
      /* true  */ "  true, {\"bool\":true}",
      /* false */ " false, {\"bool\":false}",
      /* null  */ "  null, {\"bool\":null}",
    }, nullValues = {"null"})
    void decodeIntTest(Boolean expected, String json) throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue(json, Entity.class);
      assertEquals(expected, result.getBool());
    }
  }

  @Nested
  class ListTest {
    public static class Entity {
      public List<Integer> list;

      public Entity() {
      }

      public Entity(List<Integer> list) {
        this.list = list;
      }

      public List<Integer> getList() {
        return list;
      }
    }

    @Test
    void encodeListTest() throws JsonProcessingException {
      assertEquals("{\"list\":[1,0,-1]}", ObjectMapperUtils.writeValueAsString(new Entity(List.of(1, 0, -1))));
    }

    @Test
    void encodeEmptyListTest() throws JsonProcessingException {
      assertEquals("{\"list\":[]}", ObjectMapperUtils.writeValueAsString(new Entity(Collections.emptyList())));
    }

    @Test
    void encodeNullItemListTest() throws JsonProcessingException {
      List<Integer> list = new java.util.ArrayList<>(Collections.emptyList());
      list.add(null);
      assertEquals("{\"list\":[null]}", ObjectMapperUtils.writeValueAsString(new Entity(list)));
    }

    @Test
    void encodeNullListTest() throws JsonProcessingException {
      assertEquals("{\"list\":null}", ObjectMapperUtils.writeValueAsString(new Entity(null)));
    }

    @Test
    void decodeListTest() throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue("{\"list\":[1,0,-1]}", Entity.class);
      assertEquals(List.of(1, 0, -1), result.getList());
    }

    @Test
    void decodeEmptyListTest() throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue("{\"list\":[]}", Entity.class);
      assertEquals(Collections.emptyList(), result.getList());
    }

    @Test
    void decodeNullItemListTest() throws JsonProcessingException {
      List<Integer> expected = new java.util.ArrayList<>(Collections.emptyList());
      expected.add(null);
      Entity result = ObjectMapperUtils.readValue("{\"list\":[null]}", Entity.class);
      assertEquals(expected, result.getList());
    }

    @Test
    void decodeNullListTest() throws JsonProcessingException {
      Entity result = ObjectMapperUtils.readValue("{\"list\":null}", Entity.class);
      assertNull(result.getList());
    }
  }

  @Nested
  class InstantTest {

    public static class Entity {
      public Instant instant;

      public Entity() {
      }

      public Entity(Instant instant) {
        this.instant = instant;
      }

      public Instant getInstant() {
        return instant;
      }
    }

    @Test
    void encodeInstantTest() throws JsonProcessingException {
      assertEquals("{\"instant\":\"1994-09-14T11:30:12Z\"}",
        ObjectMapperUtils.writeValueAsString(new Entity(Instant.parse("1994-09-14T11:30:12Z"))));
    }

    @Test
    void decodeInstantTest() throws JsonProcessingException {
      Entity expected = new Entity(Instant.parse("1994-09-14T11:30:12Z"));
      Entity result = ObjectMapperUtils.readValue("{\"instant\":\"1994-09-14T11:30:12Z\"}", Entity.class);
      assertEquals(expected.getInstant(), result.getInstant());
    }
  }

  @Nested
  class LocalDatetimeTest {
    // FIXME: write when use
  }
}
