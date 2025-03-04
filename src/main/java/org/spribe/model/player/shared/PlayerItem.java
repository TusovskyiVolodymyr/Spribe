package org.spribe.model.player.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerItem {
    private Integer age;
    private String gender;
    private Long id;
    private String role;
    private String screenName;
}
