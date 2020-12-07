package am.basic.notificator.controller;

import am.basic.notificator.config.SecurityContextProvider;
import am.basic.notificator.model.Rent;
import am.basic.notificator.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/rent")
public class RentController {

    @Autowired
    private SecurityContextProvider securityContextProvider;

    @Autowired
    private RentService rentService;


    @GetMapping
    public ResponseEntity<?> getRents(OAuth2Authentication auth2Authentication){
         return ResponseEntity.ok(
                 rentService.getByUserId(
                         securityContextProvider.getByAuthentication(auth2Authentication).getId()
                 )
         );
    }


    @PostMapping
    public ResponseEntity<?> addRent(OAuth2Authentication auth2Authentication, @RequestBody Rent rent){
        rent.setUserId(securityContextProvider.getByAuthentication(auth2Authentication).getId());
        return ResponseEntity.ok(rentService.add(rent));
    }
}
