package org.example.yolofarmbe.Response;

public class TemperatureMqtt {
   public String id;
   public String value;
   public int feed_id;
   public String feed_key;
   public String created_at;
   public long created_epoch;
   public String expiration;

   public String getValue() {
      return value;
   }
}
