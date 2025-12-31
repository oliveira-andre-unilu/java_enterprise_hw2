package unilu.jfe.hw2.graphql.types;

import lombok.Data;

/**
 * Class representing one of the input types that are defined in the GraphQl
 * schema.
 * 
 * @author Andre Martins
 */
@Data
public class PostTweetInput {
    private String userId;
    private String title;
    private String message;
    private int validityLength;
}
