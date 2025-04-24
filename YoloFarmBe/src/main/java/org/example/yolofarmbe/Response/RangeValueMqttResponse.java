package org.example.yolofarmbe.Response;

import com.fasterxml.jackson.databind.JsonNode;

public class RangeValueMqttResponse {
   private int minValue;
   private int maxValue;

   public int getMinValue() {
      return minValue;
   }

   public void setMinValue(int minValue) {
      this.minValue = minValue;
   }

   public int getMaxValue() {
      return maxValue;
   }

   public void setMaxValue(int maxValue) {
      this.maxValue = maxValue;
   }

   public void setProperties(JsonNode properties) {
      if (properties.has("minValue")) {
         this.minValue = properties.get("minValue").asInt();
      }
      if (properties.has("maxValue")) {
         this.maxValue = properties.get("maxValue").asInt();
      }
   }
}
