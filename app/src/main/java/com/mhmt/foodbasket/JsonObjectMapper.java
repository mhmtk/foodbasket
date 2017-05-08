package com.mhmt.foodbasket;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import javax.inject.Inject;

public final class JsonObjectMapper extends ObjectMapper {

  @Inject
  public JsonObjectMapper() {
    setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE);
    setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);
    setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.ANY);
    setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.ANY);
    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    setDateFormat(new ISO8601DateFormat());
  }
}
