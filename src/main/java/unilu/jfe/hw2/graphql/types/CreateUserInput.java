package unilu.jfe.hw2.graphql.types;

import lombok.Data;

@Data
public class CreateUserInput {
    private String userName;
    private String email;
    
}
