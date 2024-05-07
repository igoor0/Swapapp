package dev.university.project.model;

import dev.university.project.auth.ROLES;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Indexed(unique = true)
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String email;
    private String roles;
    private boolean isActive = true;

}
