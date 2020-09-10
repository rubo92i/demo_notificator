package am.basic.notificator.model;

import am.basic.notificator.model.lcp.UserStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class User {


    private int id;

    private String name;

    private String surname;

    private String code;


    private String username;



    private String password;


    private UserStatus status;

    private List<Authority> authorities;


}
