import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestListQuotes {

    String uRLApi = "https://favqs.com/api";
    String uRLApiQuote = "/quotes/";
    String token = "Token token=\"9f80837a8bf0863099e7bdb68d6ca455\"";
    String uRLFilter = "?filter=";
    String uRLAuthor = "&type=author";

public Quotes createQuoteList(String jsonInString) {
    Gson gson = new Gson();
    Quotes quotes = gson.fromJson(jsonInString, Quotes.class);
    return quotes;
}
@Test
@DisplayName("When get a list of quotes then return Http Status 220 to check if the server is on")
public void getHttpStatus200 (){

    given()
            .header("Authorization", token)
            .contentType(ContentType.JSON)
    .when()
            .get(uRLApi+uRLApiQuote)
    .then()
            .statusCode(HttpStatus.SC_OK);
};

@Test
@DisplayName("When get a List by quotes  then return one or more quotes")
public void getListQuote (){
    given()
            .header("Authorization", token)
            .contentType(ContentType.JSON)
    .when()
            .get(uRLApi+uRLApiQuote)
    .then()
            .assertThat()
            .body("quotes", hasSize(greaterThanOrEqualTo(1)));
};

@Test
@DisplayName("When get a  quotes by ID  then return the quotes with the ID")
public void getQuoteByID (){
    int iD = 11914;
    given()
            .header("Authorization", token)
            .contentType(ContentType.JSON)
    .when()
            .get(uRLApi+uRLApiQuote+iD)
    .then()
            .assertThat()
            .body("id",is(iD));
};

@Test
@DisplayName("When get a  quotes filter by name  then return the quotes contains the name filtered")
public void getQuoteByName (){
        String name = "funny";

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
        .when()
            .get(uRLApi+uRLApiQuote+uRLFilter+name)
        .then()
                .assertThat()
                .body(containsString(name));
    };

@Test
@DisplayName("When get a  quotes filter by author  then return the quotes contains the author filtered")
public void getQuoteByAuthor (){
        String author = "Mark Twain";

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .get(uRLApi+uRLApiQuote+uRLFilter+author+uRLAuthor)
                .then()
                .assertThat()
                .body(containsStringIgnoringCase(author));
    };

};





