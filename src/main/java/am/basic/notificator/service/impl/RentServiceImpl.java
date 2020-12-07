package am.basic.notificator.service.impl;

import am.basic.notificator.model.Rent;
import am.basic.notificator.repository.RentRepository;
import am.basic.notificator.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;


    @Override
    public List<Rent> getByUserId(int id) {
        return rentRepository.getByUserId(id);
    }

    @Override
    public Rent add(Rent rent) {
        return rentRepository.save(rent);
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public Optional<Rent> getById(long id) {
        return rentRepository.findById(id);
    }
}
