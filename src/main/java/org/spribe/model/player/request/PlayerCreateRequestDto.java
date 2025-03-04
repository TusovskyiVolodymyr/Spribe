package org.spribe.model.player.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCreateRequestDto {
    private Integer age;
    private String gender;
    private String role;
    private String screenName;
    private String login;
    private String password;
}
