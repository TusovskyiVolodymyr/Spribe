package org.spribe.model.player.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDeleteRequestDto {
    private long playerId;
}
