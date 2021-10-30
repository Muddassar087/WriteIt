package Net.writeit.main.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import Net.writeit.main.model.User;
import Net.writeit.main.web.UserRegistrationDto;

@Service
public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
