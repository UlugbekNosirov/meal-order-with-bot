package uz.elmurodov.meal.dto.meal;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.elmurodov.meal.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
public class MealUpdateDto extends GenericDto {
    private String name;

    private String image;

    private String type;

    @Builder(builderMethodName = "childBuilder")
    public MealUpdateDto(String name, String image, String type) {
        this.name = name;
        this.image = image;
        this.type = type;
    }
}
