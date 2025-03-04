package org.spribe.model.player.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PlayerUpdateRequestDto {
    private int age;
    private String gender;
    private String role;
    private String screenName;
    private String login;
    private String password;
}
