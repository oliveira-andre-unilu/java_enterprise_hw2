package unilu.jfe.hw2.graphql.types;

import lombok.Data;

@Data
public class PostTweetInput {
    private String userId;
    private String title;
    private String message;
}
