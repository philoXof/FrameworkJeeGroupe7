package com.esgi.framework_JEE.user.command;



import com.esgi.framework_JEE.user.Domain.entities.User;
import com.esgi.framework_JEE.user.Domain.repository.UserRepository;
import com.esgi.framework_JEE.user.query.UserQuery;
import com.esgi.framework_JEE.user.validation.UserValidationService;
import com.esgi.framework_JEE.user.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
@Service
public final class UserCommand implements UserDetailsService {
    final
    UserRepository userRepository;
    final
    UserQuery userQuery;

    private final UserValidationService userValidationService = new UserValidationService();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserCommand(UserRepository userRepository, UserQuery userQuery) {
        this.userRepository = userRepository;
        this.userQuery = userQuery;
    }

    public User create(UserRequest userRequest){
        var user = new User();
        user.setEmail(userRequest.email);
        user.setFirstname(userRequest.firstname);
        user.setLastname(userRequest.lastname);
        user.setPassword(userRequest.password);
        if (!userValidationService.isUserValid(user))
            return null;
        user.setPassword(
                passwordEncoder.encode(userRequest.password)
        );
        return userRepository.save(user);
    }


    public User changePassword(int userId, UserRequest userRequest){
        Optional<User> dbUser = Optional.ofNullable(userRepository.findById(userId));
        if(dbUser.isPresent()){
            var user = dbUser.get();
            user.setPassword(userRequest.password);
            if(userValidationService.isUserValid(user)){
                user.setPassword(
                        passwordEncoder.encode(userRequest.password)
                );
                return userRepository.save(user);
            }

        }
        return null;
    }

    public User changeFirstname(int userId, UserRequest userRequest) {
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findById(userId));
        if(userFromDB.isPresent()){
            var user = userFromDB.get();
            user.setFirstname(userRequest.firstname);
            if(userValidationService.isUserValid(user))
                return userRepository.save(user);
        }
        return null;
    }

    public User changeLastname(int userId, UserRequest userRequest) {
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findById(userId));
        if(userFromDB.isPresent()){
            var user = userFromDB.get();
            user.setLastname(userRequest.lastname);
            if(userValidationService.isUserValid(user))
                return userRepository.save(user);
        }
        return null;
    }

    public User changeEmail(int userId, UserRequest userRequest){
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findById(userId));
        if(userFromDB.isPresent()){
            var user = userFromDB.get();
            user.setEmail(userRequest.email);
            if(userValidationService.isUserValid(user))
                if(!userQuery.userEmailExist(user.getEmail()))
                    return userRepository.save(user);
        }
        return null;
    }


    public void delete(int userId) {
        Optional<User> userFromDb = Optional.ofNullable(userRepository.findById(userId));
        userFromDb.ifPresent(userRepository::delete);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getPermission().getTitlePermission()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
