package app.user.property;

import app.user.model.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Setter
@Getter
@ConfigurationProperties(prefix = "users")

public class UsersProperty {
    private UserRole defaultRole;
    private boolean defaultAccountState;


    }


