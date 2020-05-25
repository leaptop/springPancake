package pancake.security;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pancake.data.UserRepository;

@Controller//эта аннотация будет отсканирована с помощью @ComponentScan
@RequestMapping("/register")//теперь адреса всех методов этого класса должны иметь префикс /register
public class RegistrationController {
  
  private UserRepository userRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(
      UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }
  
  @GetMapping     //нет параметров=> путь по умолчанию. Данный метод д.б. вызван, когда кто-то вызывает метод GET на пути /register
  public String registerForm() {
    return "registration";
  }
  
  @PostMapping
  public String processRegistration(RegistrationForm form) {
    userRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }

}
