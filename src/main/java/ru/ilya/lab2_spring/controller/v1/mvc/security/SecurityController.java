package ru.ilya.lab2_spring.controller.v1.mvc.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ilya.lab2_spring.dto.UserDTO;
import ru.ilya.lab2_spring.service.UserService;

import java.text.MessageFormat;

import static ru.ilya.lab2_spring.model.api.ApiConstants.*;

@Controller
@RequestMapping(AUTH_PATH)
public class SecurityController {
    private final UserService userService;

    @Autowired
    public SecurityController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDTO initUser() {
        return new UserDTO();
    }

    @RequestMapping(value = LOGIN_PATH, method = RequestMethod.GET)
    public String getLoginPage() {
        return "loginPage";
    }

    @RequestMapping(value = LOGOUT_PATH, method = RequestMethod.POST)
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute(initUser());
        return "registration";
    }

    @RequestMapping(value = REGISTER_PATH, method = RequestMethod.POST)
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, AUTH_PATH, REGISTER_PATH);
        }
        try {
            userService.registerNewUser(userDTO);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ошибка! Пользователь уже сущетсвует");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user",
                    bindingResult);
            return MessageFormat.format("{0}{1}{2}", REDIRECT_PATH, AUTH_PATH, REGISTER_PATH);
        }
        return REDIRECT_PATH+SPLIT_PATH;
    }
}
