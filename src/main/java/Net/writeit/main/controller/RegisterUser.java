package Net.writeit.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Net.writeit.main.service.UserService;
import Net.writeit.main.web.UserRegistrationDto;


@Controller
public class RegisterUser {
	private UserService userService;

	public RegisterUser(UserService userService) {
		this.userService = userService;
	}
	
	@ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
	
	@RequestMapping("/register")
	public String showRegistrationForm() {
		return "register";
	}
	
	@PostMapping("/registeration")
	public ModelAndView registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		userService.save(registrationDto);
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
}
