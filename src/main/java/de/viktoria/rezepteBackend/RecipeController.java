package de.viktoria.rezepteBackend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class RecipeController {

    private final RecipeRepository repository;

    RecipeController(RecipeRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    @GetMapping("/recipes")
    List<Recipe> all() {
        return repository.findAll();
    }

    @PostMapping("/recipes")
    Recipe newRecipe(@RequestBody Recipe newRecipe) {
        return repository.save(newRecipe);
    }

    // Single item
    @GetMapping("/recipes/{id}")
    Recipe one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

    @PutMapping("/recipes/{id}")
    Recipe replaceRecipe(@RequestBody Recipe newRecipe, @PathVariable Long id) {
        return repository.findById(id)
                .map(recipe -> {
                    recipe.setTitle(newRecipe.getTitle());
                    recipe.setRecipe(newRecipe.getRecipe());
                    recipe.setLevel(newRecipe.getRecipe());
                    return repository.save(recipe);
                })
                .orElseGet(() -> {
                    newRecipe.setId(id);
                    return repository.save(newRecipe);
                });
    }

    @DeleteMapping("/recipes/{id}")
    void deleteRecipe(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
