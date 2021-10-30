package Net.writeit.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErorrHandler {
	@RequestMapping("/404")
	public String error() {
		return "error";
	}
}
