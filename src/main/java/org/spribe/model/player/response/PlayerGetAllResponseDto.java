package org.spribe.model.player.response;

import lombok.Data;
import org.spribe.model.player.shared.PlayerItem;

@Data
public class PlayerGetAllResponseDto {
    private PlayerItem[] players;
}
