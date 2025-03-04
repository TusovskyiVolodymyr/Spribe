package player;

import org.apache.http.HttpStatus;
import org.spribe.api.PlayerClient;
import org.spribe.model.player.request.PlayerCreateRequestDto;
import org.spribe.model.player.request.PlayerUpdateRequestDto;
import org.spribe.utils.AssertObject;
import org.spribe.utils.RandomUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.spribe.utils.RandomUtils.*;
import static org.testng.Assert.*;

public class PlayerControllerTest extends BaseTest {

    private PlayerClient playerClient;

    @BeforeMethod
    public void init() {
        playerClient = new PlayerClient();
    }

    @Test()
    public void getAll() throws Exception {
      var getAllResponse = playerClient.getAllPlayers();

      assertEquals(getAllResponse.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200 (Ok)");

      assertTrue(Arrays.stream(getAllResponse.getBody().getPlayers())
              .allMatch(playerItem -> playerItem.getId() != null),
              "Ids shouldn't be null");
    }

    @Test
    public void getAllSECOND() {
        playerClient.getAllPlayers();
    }

    @Test
    public void getAllGKKJDKJFKJDFKJDF() {
        playerClient.getAllPlayers();
    }

    @Test
    public void createPlayer_OK_200() {
        var editor = "admin";
        var player = PlayerCreateRequestDto.builder()
                .age(20)
                .gender("male")
                .role("admin")
                .screenName("Player")
                .login("admin")
                .password("pass")
                .build();

        var createPlayerResp = playerClient.createPlayer(editor, player);

        assertEquals(createPlayerResp.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200 (Ok)");
        assertNotNull(createPlayerResp.getBody().getId(), "Created player Id shouldn't be null");

        new AssertObject(createPlayerResp.getBody(), player)
                .assertMatchingFields()
                .assertAll();

    }

    @Test
    public void getPlayerById_OK_200() {
        var playerId = playerClient.getAllPlayers().getBody().getPlayers()[0].getId();
        var getPlayerResp = playerClient.getPlayerById(playerId);

        assertEquals(getPlayerResp.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200 (Ok)");
        assertEquals(getPlayerResp.getBody().getId(), playerId, "Player ids should match");
    }

    @Test
    public void updatePlayer() {
        var playerId = playerClient
                        .getAllPlayers()
                        .getBody()
                        .getPlayers()[0]
                .getId();
        var editor = "admin";
        var request = PlayerUpdateRequestDto.builder()
                .age(randomInt(18, 100))
                .gender(randomElement(new String[] { "male", "female" }))
                .login("updatedLogin" + RandomUtils.randomString(7))
                .password("updatedPass" + RandomUtils.randomString(7))
                .screenName("updatedScreenName" + randomString(5))
                .build();

        var updatePlayerResp = playerClient.updatePlayer(editor, playerId, request);
        assertEquals(updatePlayerResp.getStatusCode(), HttpStatus.SC_OK, "Status code should be 200 (Ok)");
        new AssertObject(updatePlayerResp.getBody(), request)
                .assertMatchingFields()
                .assertAll();
    }

    @Test
    public void deletePlayerById() {
        var editor = "admin";
        var playerId = playerClient
                .getAllPlayers()
                .getBody()
                .getPlayers()[0]
                .getId();

        var deletePlayerResp = playerClient.deletePlayerById(editor, playerId);

        assertEquals(deletePlayerResp.getStatusCode(), HttpStatus.SC_NO_CONTENT, "Status code should be 204 (No content)");
    }
}
