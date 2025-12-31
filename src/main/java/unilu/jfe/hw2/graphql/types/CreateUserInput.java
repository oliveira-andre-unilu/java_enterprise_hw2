package unilu.jfe.hw2.graphql.types;

import lombok.Data;

/**
 * Class representing one of the input types that are defined in the GraphQl
 * schema.
 * 
 * @author Andre Martins
 */
@Data
public class CreateUserInput {
    private String userName;
    private String email;
}
