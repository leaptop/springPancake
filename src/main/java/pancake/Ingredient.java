package pancake;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity/*Эта аннотация  нужна, чтобы объявить данный класс сущностью JPA для 
возможности добавления данного объекта в БД. При этом @Id - уникальный айдишник для этой БД.
*/
public class Ingredient {
  
  @Id/*Эта аннотация указывает CrudRepository что брать как второй параметр при 
  добавлении объекта  Ingredient  в репозиторий. В данном случает это строковое значение.
  Этот айди - unit of persistence для интерфейса этого репозитория.
  */
  private final String id;
  private final String name;
  private final Type type;
  
  public static enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
