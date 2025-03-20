package org.example.yolofarmbe.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdafruitResponse {
   private String id;
   private String feed_id;
   private String value;
   private String location;
   private String create_at;
   private String update_at;
   private String expiration;
   private String lat;
   private String lon;
   private String ele;
}
