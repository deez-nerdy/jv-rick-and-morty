package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Page<Character> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
