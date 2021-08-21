import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "comment_id")
    private Integer commentID;

    @Column(name = "commenter")
    private Integer commenter;

    @Column(name = "comment")
    private String comment;

    @Column(name = "post")
    private Integer post;

    public Comment() {
    }

    public Comment(Integer commenter, String comment, Integer post) {
        this.commenter = commenter;
        this.comment = comment;
        this.post = post;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public Integer getCommenter() {
        return commenter;
    }

    public void setCommenter(Integer commenter) {
        this.commenter = commenter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment1 = (Comment) o;
        return commentID.equals(comment1.commentID)
                && commenter.equals(comment1.commenter)
                && comment.equals(comment1.comment)
                && post.equals(comment1.post);
    }
}
