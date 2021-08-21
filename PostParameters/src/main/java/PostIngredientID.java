import java.io.Serializable;

public class PostIngredientID implements Serializable {
    private Integer post;
    private Integer ingredient;

    public PostIngredientID() {

    }

    public PostIngredientID(Integer post, Integer ingredient) {
        this.post = post;
        this.ingredient = ingredient;
    }
}
