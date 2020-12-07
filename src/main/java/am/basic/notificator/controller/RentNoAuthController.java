package am.basic.notificator.controller;

import am.basic.notificator.model.Rent;
import am.basic.notificator.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent")
public class RentNoAuthController {


    @Autowired
    private RentService rentService;


    @GetMapping
    public ResponseEntity<?> getRents() {
        return ResponseEntity.ok(rentService.getByUserId(2));
    }


    @PostMapping
    public ResponseEntity<?> addRent(@RequestBody Rent rent) {
        rent.setUserId(2);
        return ResponseEntity.ok(rentService.add(rent));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRents() {
        return ResponseEntity.ok(rentService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        return ResponseEntity.of(rentService.getById(id));
    }

}
