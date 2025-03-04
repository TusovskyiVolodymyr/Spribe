package org.spribe.model.player.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerGetByPlayerIdResponseDto {
    private Integer age;
    private String gender;
    private Long id;
    private String role;
    private String screenName;
    private String login;
}
