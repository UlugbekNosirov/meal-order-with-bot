package uz.elmurodov.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.elmurodov.meal.domain.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
