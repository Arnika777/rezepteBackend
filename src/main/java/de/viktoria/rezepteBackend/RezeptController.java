package de.viktoria.rezepteBackend;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RezeptController {

    private final RezeptRepository repository;

    RezeptController(RezeptRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    @GetMapping("/rezepte")
    List<Rezept> all() {
        return repository.findAll();
    }

    @PostMapping("/rezepte")
    Rezept newRecipe(@RequestBody Rezept neuesRezept) {
        return repository.save(neuesRezept);
    }

    // Single item
    @GetMapping("/rezepte/{id}")
    Rezept one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RezeptNotFoundException(id));
    }

    @PutMapping("/rezepte/{id}")
    Rezept replaceRecipe(@RequestBody Rezept neuesRezept, @PathVariable Long id) {
        return repository.findById(id)
                .map(rezept -> {
                    rezept.setTitle(neuesRezept.getTitle());
                    rezept.setRezept(neuesRezept.getRezept());
                    rezept.setSchwierigkeitsgrad(neuesRezept.getRezept());
                    return repository.save(rezept);
                })
                .orElseGet(() -> {
                    neuesRezept.setId(id);
                    return repository.save(neuesRezept);
                });
    }

    @DeleteMapping("/rezepte/{id}")
    void deleteRecipe(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
