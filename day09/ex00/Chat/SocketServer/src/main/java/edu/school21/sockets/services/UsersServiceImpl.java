package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private PasswordEncoder passwordEncoder;
    private UsersRepository usersRepository;

    @Autowired
    private void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String saveNewUser(String userName, String password) {
        Optional<User> user = usersRepository.findByName(userName);

        if (user.isPresent()) {
            return "User with the name is already exist!\n";
        }

        usersRepository.save(new User(userName, passwordEncoder.encode(password)));

        return "Successful!\n";
    }
}
