package am.basic.notificator.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {


    private int id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
