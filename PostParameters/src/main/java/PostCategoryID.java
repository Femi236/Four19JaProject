import java.io.Serializable;

public class PostCategoryID implements Serializable {
    private Integer post;
    private Integer category;

    public PostCategoryID() {

    }

    public PostCategoryID(Integer post, Integer catrgory) {
        this.post = post;
        this.category = catrgory;
    }
}
