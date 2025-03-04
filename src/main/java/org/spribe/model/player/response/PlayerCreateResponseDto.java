package org.spribe.model.player.response;

import lombok.Data;

@Data
public class PlayerCreateResponseDto {
   private Integer age;
   private String gender;
   private Long id;
   private String login;
   private String password;
   private String role;
   private String screenName;
}
