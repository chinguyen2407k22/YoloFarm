package org.example.yolofarmbe.Response;

import com.fasterxml.jackson.databind.JsonNode;

public class FeedMqttResponse {
   Double value;

   public Double getValue() {
      return value;
   }

   public void setValue(Double value) {
      this.value = value;
   }

   public void setProperties(JsonNode properties) {
      this.value = properties.get("value").asDouble();
   }
}
