package pw.backend.lab.backlab.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.backend.lab.backlab.appException.ResourceNotFoundException;
import pw.backend.lab.backlab.user.User;
import pw.backend.lab.backlab.user.UserRepository;

import java.util.Optional;

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(String.valueOf(user.getId()));
        if (existingUser.isPresent()) {
            return userRepository.save(user);
        }
        throw new ResourceNotFoundException(String.format("User with id [%s] not found.", user.getId()));
    }
}
