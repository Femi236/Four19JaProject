import java.io.Serializable;

public class FollowID implements Serializable {
    private Integer follower;
    private Integer followed;

    public FollowID() {

    }

    public FollowID(Integer follower, Integer followed) {
        this.follower = follower;
        this.followed = followed;
    }
}
