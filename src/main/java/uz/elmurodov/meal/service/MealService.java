package uz.elmurodov.meal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.elmurodov.meal.criteria.MealCriteria;
import uz.elmurodov.meal.domain.Meal;
import uz.elmurodov.meal.dto.meal.MealCreateDto;
import uz.elmurodov.meal.dto.meal.MealDto;
import uz.elmurodov.meal.dto.response.AppErrorDto;
import uz.elmurodov.meal.dto.response.DataDto;
import uz.elmurodov.meal.mappers.MealMapper;
import uz.elmurodov.meal.repository.MealRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MealService implements AbstractService {
    private final MealRepository repository;
    private final MealMapper mapper;


    public ResponseEntity<DataDto<List<MealDto>>> getAll(MealCriteria criteria) {
        PageRequest request = PageRequest.of(criteria.getPage(), criteria.getSize());
        List<Meal> meals = repository.findAll(request).get().toList();
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(meals), (long) meals.size()), HttpStatus.OK);
    }


    public ResponseEntity<DataDto<MealDto>> get(Long id) {
        Optional<Meal> mealOptional = repository.findById(id);
        if (mealOptional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().build()), HttpStatus.OK);

        return new ResponseEntity<>(new DataDto<>(mapper.toDto(mealOptional.get())), HttpStatus.OK);
    }

    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        Optional<Meal> mealOptional = repository.findById(id);
        if (mealOptional.isEmpty())
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build()), HttpStatus.OK);
        repository.delete(mealOptional.get());
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }


    public Long createFromTelegramBot(@NotNull MealCreateDto dto) {
        Meal meal = mapper.fromCreateDto(dto);
        repository.save(meal);
        return meal.getId();
    }

    public ResponseEntity<DataDto<Long>> create(MealCreateDto dto) {
        return null;
    }
}
