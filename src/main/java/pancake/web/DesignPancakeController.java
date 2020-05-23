package pancake.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pancake.Ingredient;
import pancake.Ingredient.Type;
import pancake.Order;
import pancake.Pancake;
import pancake.User;
import pancake.data.IngredientRepository;
import pancake.data.PancakeRepository;
import pancake.data.UserRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignPancakeController {
  
  private final IngredientRepository ingredientRepo;
  
  private PancakeRepository tacoRepo;

  private UserRepository userRepo;

  @Autowired
  public DesignPancakeController(
        IngredientRepository ingredientRepo, 
        PancakeRepository tacoRepo,
        UserRepository userRepo) {
    this.ingredientRepo = ingredientRepo;
    this.tacoRepo = tacoRepo;
    this.userRepo = userRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "design")
  public Pancake design() {
    return new Pancake();
  }
  
  @GetMapping
  public String showDesignForm(Model model, Principal principal) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
    
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), 
          filterByType(ingredients, type));      
    }
    
    String username = principal.getName();
    User user = userRepo.findByUsername(username);
    model.addAttribute("user", user);

    return "design";
  }

  @PostMapping
  public String processDesign(
      @Valid Pancake taco, Errors errors, 
      @ModelAttribute Order order) {
    
    if (errors.hasErrors()) {
      return "design";
    }

    Pancake saved = tacoRepo.save(taco);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }
  
}
