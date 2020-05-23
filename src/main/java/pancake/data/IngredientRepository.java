package pancake.data;

import org.springframework.data.repository.CrudRepository;

import pancake.Ingredient;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
