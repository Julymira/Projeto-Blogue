package io.github.julymira.blogue.domain.model.bo;

import io.github.julymira.blogue.domain.model.dao.UserDAO;
import io.github.julymira.blogue.domain.model.entity.User;
//import io.github.julymira.blogue.domain.model.dto.CreateUserRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserBO {

    @Inject
    private UserDAO userDAO;

    public void saveUser(User user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User or email cannot be null or empty");
        }
        userDAO.persist(user);
    }

    public List<User> listAllUsers() {
        return userDAO.listAll();
    }

    public boolean deleteUser(Long id) {
        User user = userDAO.findById(id);
        if (user != null) {
            userDAO.delete(user);
            return true;
        }
        return false;
    }

//    public User updateUser(Long id, CreateUserRequest userData) {
//        User user = userDAO.findById(id);
//        if (user != null) {
//            user.setName(userData.getName());
//            user.setEmail(userData.getEmail());
//            user.setPassword(userData.getPassword());
//            userDAO.persist(user);
//            return user;
//        }
//        return null;
//    }
}
