package app.user.service;

import app.exception.DomainException;
import app.subscription.service.SubscriptionService;
import app.user.model.User;
import app.user.model.UserRole;
import app.user.property.UsersProperty;
import app.user.repository.UserRepository;
import app.wallet.service.WalletService;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

//easy for log
@Slf4j
@Service

public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionService subscriptionService;
    private final WalletService walletService;

    private final UsersProperty usersProperty;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, SubscriptionService subscriptionService,
                       WalletService walletService, UsersProperty usersProperty) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.subscriptionService = subscriptionService;
        this.walletService = walletService;
        this.usersProperty = usersProperty;
    }

    public User login(LoginRequest loginRequest){

        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());

        if (optionalUser.isEmpty()){
            throw new DomainException("Username or password are incorrect");
        }
        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword() ) ){
            throw new DomainException("Username or password are incorrect");
        }


        return user;
    }

    @Transactional
    public User register(RegisterRequest registerRequest){

        Optional<User> optionalUser = userRepository.findByUsername(registerRequest.getUserName());

        if (optionalUser.isPresent()){
            throw new DomainException("Username [%s] already exist.".formatted(registerRequest.getUserName()));


        }
        User user = userRepository.save(initilizeUser(registerRequest));


        subscriptionService.createDefaultSubscription(user);
        walletService.createNewWallet(user);


        log.info("Successfully create new user account for username[%s] and id [%s]"
                .formatted(user.getUsername(),user.getId()));
        return user;
    }
    private User initilizeUser(RegisterRequest registerRequest){
        return User.builder()
                .username(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(usersProperty.getDefaultRole())
                .isActive(usersProperty.isDefaultAccountState())
                .country(registerRequest.getCountry())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();
    }

}
