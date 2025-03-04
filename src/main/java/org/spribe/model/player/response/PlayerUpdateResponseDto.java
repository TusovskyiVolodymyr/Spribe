package org.spribe.model.player.response;

import lombok.Data;

@Data
public class PlayerUpdateResponseDto {
    private int age;
    private String gender;
    private long id;
    private String role;
    private String screenName;
}
