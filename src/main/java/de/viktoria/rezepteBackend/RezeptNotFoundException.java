package de.viktoria.rezepteBackend;

public class RezeptNotFoundException extends RuntimeException {
    public RezeptNotFoundException(Long id) {
        super("Could not find recipe " + id);
    }
}
