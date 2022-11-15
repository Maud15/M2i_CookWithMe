import java.util.List;
import java.util.Optional;

public interface RecipeCrudDao<E> {

    List<E> findAll();

    E findRandom();

    Optional<E> findByKeyword(String criterion, String keyword);

    E create(E element);


    //boolean delete(Long id);

    //E update(E element);


}
