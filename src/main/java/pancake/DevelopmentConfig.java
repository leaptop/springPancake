package pancake;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import pancake.Ingredient.Type;
import pancake.data.IngredientRepository;
import pancake.data.UserRepository;

@Profile("!prod")
@Configuration /*эта аннотация говорит Спрингу о том, что данный класс является конфигурационным, он будет поставлять бины 
в контекст приложения Спринг.
*/
public class DevelopmentConfig {

  @Bean/*Эта аннотация говорит о том, что данный метод возвращает объект, который будет возвращён как бин в контекст приложения. При этом 
  По умолчанию ID бина будет таким же, как имя метода.
  */
  public CommandLineRunner dataLoader(IngredientRepository repo,
        UserRepository userRepo, PasswordEncoder encoder) { // user repo для простоты тестирования со встроеным пользователем
    return new CommandLineRunner() {
      @Override//Эта аннотация говорит о том, что мы собираемся переопределить метод базового класса
      public void run(String... args) throws Exception {
        repo.save(new Ingredient("EGGS", "С яйцами", Type.WRAP));//в методе run сразу создаём список ингредиентов
        repo.save(new Ingredient("VEGG", "Веганский", Type.WRAP));
        repo.save(new Ingredient("CORN", "из кукрузной муки", Type.WRAP));
        repo.save(new Ingredient("CHCK", "Курица", Type.PROTEIN));
        repo.save(new Ingredient("BEEF", "Говядина", Type.PROTEIN));
        repo.save(new Ingredient("TMTO", "Помидоры", Type.VEGGIES));
        repo.save(new Ingredient("GREN", "Зелень", Type.VEGGIES));
        repo.save(new Ingredient("CHED", "Чеддер", Type.CHEESE));
        repo.save(new Ingredient("GAUD", "Гауда", Type.CHEESE));
        repo.save(new Ingredient("BSHM", "Бешамель", Type.SAUCE));
        repo.save(new Ingredient("SRCR", "Сметана", Type.SAUCE));
        
        
        userRepo.save(new User("username", encoder.encode("password"), //создал пользователя по умолчанию
            "Wesley Snypes", "Иванова 45, 15", "Омск", "РФ", 
            "345", "123-123-1234"));
      }
    };
  }
  
}
