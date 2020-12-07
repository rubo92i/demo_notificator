package am.basic.notificator.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Embedded;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

import static org.springframework.data.cassandra.core.mapping.Embedded.OnEmpty.USE_NULL;

@Data
@Table
public class Rent {


    @PrimaryKey
    private long id;

    private String notes;

    private Date start;

    private Date end;

    private int duration;

    private double price;

    private boolean sameUser;

    private int userId;

    @Embedded(onEmpty = USE_NULL)
    private Car car;


}
