package ks.msx.TaskManager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private String username;

    private String password;
}
