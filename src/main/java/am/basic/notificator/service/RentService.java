package am.basic.notificator.service;

import am.basic.notificator.model.Rent;

import java.util.List;
import java.util.Optional;

public interface RentService {

    List<Rent> getByUserId(int id);


    Rent add(Rent rent);

    List<Rent> getAll();

    Optional<Rent> getById(long id);
}
