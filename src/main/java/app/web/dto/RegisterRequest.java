package app.web.dto;

import app.user.model.Country;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

//@Data анотоация(@Getter, @Setter and other annotations)

@Data
@Builder
public class RegisterRequest {
    @Size(min = 6, message = "Username must be at least 6 symbols")
    private String userName;

    @Size(min = 6, message = "Password must be at least 6 symbols")
    private String password;

    @NotNull
    private Country country;


}
