package pancake.data;

import org.springframework.data.repository.CrudRepository;

import pancake.Pancake;

public interface PancakeRepository 
         extends CrudRepository<Pancake, Long> {

}
