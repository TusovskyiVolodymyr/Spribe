package org.spribe.model.player.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PlayerGetByPlayerIdRequestDto {
    private long playerId;
}
