package ul.lviv.iot.springjpa.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ul.lviv.iot.springjpa.entity.User;
import ul.lviv.iot.springjpa.exception.ResourceNotFoundException;
import ul.lviv.iot.springjpa.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Not Found");
    }

    public User updateUser(Long id, User user) {
        var toUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return userRepository.save(user.setId(toUpdate.getId()));
    }
}
