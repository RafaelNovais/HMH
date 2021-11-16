import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestFavQuotes {

    String uRLApi = "https://favqs.com/api/";
    String uRLApiQuote = "quotes/";
    String uRLApiQuoteFav = "/fav/";
    String uRLApiQuoteUnfav = "/unfav/";
    String token = "Token token=\"9f80837a8bf0863099e7bdb68d6ca455\"";
    //String userToken = "EQ31CffImbwj10NiPJsv4Th7wNqtpuAUKqrCw2xivAfR4lRd6/HoxZUXhecYIEQoFZkrgQ4VVZxWFCC2ZZxxlQ==";
    String uRLApiSession = "session/";


    public Quotes createQuoteList(String jsonInString) {
        Gson gson = new Gson();
        Quotes quotes = gson.fromJson(jsonInString, Quotes.class);
        return quotes;
    }


    public String getUserToken() {

        String userToken =
            given()
                    .header("Authorization", token)
                    .body("{ \n" +
                            "  \"user\": {\n" +
                            "    \"login\": \"rafaelnovais\",\n" +
                            "    \"password\": \"123456\"\n" +
                            "  }\n" +
                            "}")
                    .contentType(ContentType.JSON)
            .when()
                    .post(uRLApi +  uRLApiSession)
            .then()
                    .extract()
                    .path("User-Token");

        return userToken;
}

    @Test
    @DisplayName("When put a quotes on fav then return favorite is true")
    public void putQuoteFav() {

        int id = 11914;

        given()
                .header("Authorization", token)
                .header("User-Token",getUserToken())
                .contentType(ContentType.JSON)
        .when()
                .put(uRLApi + uRLApiQuote + id +uRLApiQuoteFav)
        .then()
                .assertThat()
                .body("user_details.favorite",is(true));
    }

    @Test
    @DisplayName("When remove a quotes on fav then return favorite is false")
    public void removeQuoteFav() {

        int id = 11914;

        given()
                .header("Authorization", token)
                .header("User-Token",getUserToken())
                .contentType(ContentType.JSON)
        .when()
                .put(uRLApi + uRLApiQuote + id +uRLApiQuoteUnfav)
        .then()
                .assertThat()
                .body("user_details.favorite",is(false));
    }

    @AfterEach
    public void destroySession(){
        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
        .when()
                .delete(uRLApi +  uRLApiSession)
                .then();

    }

}