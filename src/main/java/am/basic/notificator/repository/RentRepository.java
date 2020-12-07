package am.basic.notificator.repository;

import am.basic.notificator.model.Rent;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends CassandraRepository<Rent, Long> {

    @AllowFiltering
    List<Rent> getByUserId(int id);

}
