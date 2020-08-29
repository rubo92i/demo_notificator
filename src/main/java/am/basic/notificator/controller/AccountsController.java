package am.basic.notificator.controller;

import am.basic.notificator.model.User;
import am.basic.notificator.model.dto.ChangePasswordDto;
import am.basic.notificator.model.dto.RecoverPasswordDto;
import am.basic.notificator.model.dto.UsernameCodeDto;
import am.basic.notificator.model.exceptions.AccessDeniedException;
import am.basic.notificator.model.exceptions.DuplicateDataException;
import am.basic.notificator.model.exceptions.NotFoundException;
import am.basic.notificator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) throws DuplicateDataException {
        userService.register(user);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/forget-password")
    public ResponseEntity forgetPassword(@RequestParam String username) throws NotFoundException {
        userService.sendCode(username);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/recover-password")
    public ResponseEntity recoverPassword(@RequestBody @Valid RecoverPasswordDto dto) throws NotFoundException, AccessDeniedException {
        userService.recoverPassword(dto.getUsername(), dto.getCode(), dto.getPassword());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/resend")
    public ResponseEntity resend(@RequestParam String username) throws NotFoundException {
        userService.sendCode(username);
        return ResponseEntity.ok().build();
    }


    @PostMapping(path = "/verify")
    public ResponseEntity verify(@RequestBody UsernameCodeDto dto) throws NotFoundException, AccessDeniedException {
        userService.verify(dto.getUsername(), dto.getCode());
        return ResponseEntity.ok().build();

    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto dto) throws NotFoundException, AccessDeniedException {
        userService.changePassword(dto.getUsername(), dto.getPassword(), dto.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
