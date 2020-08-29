package am.basic.notificator.service;

import am.basic.notificator.model.User;
import am.basic.notificator.model.exceptions.AccessDeniedException;
import am.basic.notificator.model.exceptions.DuplicateDataException;
import am.basic.notificator.model.exceptions.NotFoundException;
import am.basic.notificator.model.exceptions.UnverifiedException;

public interface UserService {



    void register(User user) throws DuplicateDataException;


    void  changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException;

    void sendCode(String username) throws NotFoundException;

    void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException;

    void verify(String username, String code) throws NotFoundException, AccessDeniedException;

    void makeAdmin(int userId) throws NotFoundException;

}
