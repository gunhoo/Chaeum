package com.tls.user.dto;

import com.tls.allergy.entity.single.Allergy;
import com.tls.ingredient.entity.single.Ingredient;
import com.tls.recipe.entity.single.Recipe;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileDto {

    String userEmail;
    int veganId;
    List<Ingredient> ingredientList;
    List<Recipe> recipeList;
    List<Allergy> allergyList;
}
