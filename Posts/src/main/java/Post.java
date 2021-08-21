import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Integer postID;

    @Column(name = "user")
    private Integer user;

    @Column(name = "time_taken")
    private Integer timeTaken;

    public Post() {
        super();
    }

    public Post(Integer user, Integer timeTaken) {
        this.user = user;
        this.timeTaken = timeTaken;
    }

    public Integer getTimeTaken() {
        return timeTaken;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return postID.equals(post.postID)
                && user.equals(post.user)
                && timeTaken.equals(post.timeTaken);
    }
}
