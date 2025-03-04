package org.spribe.api;

import io.qameta.allure.Step;
import org.spribe.model.ResponseModel;
import org.spribe.model.player.request.PlayerCreateRequestDto;
import org.spribe.model.player.request.PlayerDeleteRequestDto;
import org.spribe.model.player.request.PlayerGetByPlayerIdRequestDto;
import org.spribe.model.player.request.PlayerUpdateRequestDto;
import org.spribe.model.player.response.PlayerCreateResponseDto;
import org.spribe.model.player.response.PlayerGetAllResponseDto;
import org.spribe.model.player.response.PlayerGetByPlayerIdResponseDto;
import org.spribe.model.player.response.PlayerUpdateResponseDto;
import org.spribe.utils.PojoToMapConverter;

import java.util.HashMap;
import java.util.Map;

public class PlayerClient extends BaseClient {

    private static final String BASE_PATH = "/player/";

    @Step
    public ResponseModel<PlayerGetAllResponseDto> getAllPlayers() {
       return new ResponseModel<>(get(BASE_PATH + "get/all"), PlayerGetAllResponseDto.class);
    }

    @Step
    public ResponseModel<PlayerCreateResponseDto> createPlayer(String editor, PlayerCreateRequestDto player) {
        var queryParams = PojoToMapConverter.convertToMap(player);
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put("editor", editor);

        return new ResponseModel<>(get(BASE_PATH + "create/{editor}", queryParams, pathParams),
                PlayerCreateResponseDto.class);
    }

    @Step
    public ResponseModel<PlayerGetByPlayerIdResponseDto> getPlayerById(Long id) {
        return new ResponseModel<>(post(BASE_PATH + "get", new PlayerGetByPlayerIdRequestDto(id)),
                PlayerGetByPlayerIdResponseDto.class);
    }

    @Step
    public ResponseModel<PlayerUpdateResponseDto> updatePlayer(String editor, Long id, PlayerUpdateRequestDto player) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("editor", editor);
        pathParams.put("id", id);

       return new ResponseModel<>(patch(BASE_PATH + "update/{editor}/{id}", player, pathParams),
               PlayerUpdateResponseDto.class);
    }

    @Step
    public ResponseModel<Void> deletePlayerById(String editor, Long id) {
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("editor", editor);

        return new ResponseModel<>(delete(BASE_PATH + "delete/{editor}", new PlayerDeleteRequestDto(id), pathParams),
                Void.class);
    }
}
