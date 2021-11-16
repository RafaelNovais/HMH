import com.google.gson.annotations.SerializedName;

public class Quotes {

    public int id;
    public String dialogue;
    public String privat;
    public String tags;
    public String url;
    @SerializedName("favorites_count")
    public int favoritesCount;
    @SerializedName("upvotes_count")
    public int upVotesCount;
    @SerializedName("downvotes_count")
    public  int downVotesCount;
    public  String author;
    @SerializedName("author_permalink")
    public  String authorPermalink;
    public  String body;


    public Quotes(int id, String dialogue, String privat, String tags, String url, int favoritesCount, int upVotesCount, int downVotesCount, String author, String authorPermalink, String body) {
        this.id = id;
        this.dialogue = dialogue;
        this.privat = privat;
        this.tags = tags;
        this.url = url;
        this.favoritesCount = favoritesCount;
        this.upVotesCount = upVotesCount;
        this.downVotesCount = downVotesCount;
        this.author = author;
        this.authorPermalink = authorPermalink;
        this.body = body;
    }


    public int getId() {
        return id;
    }

    public String getDialogue() {
        return dialogue;
    }

    public String getPrivat() {
        return privat;
    }

    public String getTags() {
        return tags;
    }

    public String getUrl() {
        return url;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public int getUpVotesCount() {
        return upVotesCount;
    }

    public int getDownVotesCount() {
        return downVotesCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorPermalink() {
        return authorPermalink;
    }

    public String getBody() {
        return body;
    }
}
