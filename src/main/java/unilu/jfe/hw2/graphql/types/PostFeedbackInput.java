package unilu.jfe.hw2.graphql.types;

import lombok.Data;

@Data
public class PostFeedbackInput {
    private String postId;
    private String userId;
    private String content;
}
