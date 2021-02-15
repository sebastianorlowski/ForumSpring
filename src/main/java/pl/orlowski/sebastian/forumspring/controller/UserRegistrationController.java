package pl.orlowski.sebastian.forumspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.orlowski.sebastian.forumspring.dto.UserRegistrationDto;
import pl.orlowski.sebastian.forumspring.service.UserService;
import pl.orlowski.sebastian.forumspring.validator.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    private UserValidator userValidator;

    @Autowired
    public UserRegistrationController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto,
                                      BindingResult bindingResult) {
            userValidator.validate(userRegistrationDto, bindingResult);

            if (bindingResult.hasErrors()) {
                return "registration";
            }

            userService.save(userRegistrationDto);
            return "redirect:/registration?success";
        }
    }

