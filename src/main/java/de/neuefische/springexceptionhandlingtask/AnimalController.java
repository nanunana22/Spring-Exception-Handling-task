package de.neuefische.springexceptionhandlingtask;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species) {
        if (!species.equals("dog")) {
            throw new IllegalArgumentException("Only 'dog' is allowed");
        }
        return species;
    }

    @GetMapping
    String getAllAnimals() {
        throw new NoSuchElementException("No Animals found");
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGlobalException2(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage("Ein Fehler ist aufgetreten, kein Tier gefunden: " + ex.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleGlobalException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage("Ein Fehler ist aufgetreten: " + ex.getMessage());
        return errorMessage;
    }
}

//@RestControllerAdvice
//@ResponseStatus(HttpStatus.BAD_REQUEST)
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public de.neuefische.springexceptionhandlingtask.ErrorMessage handleGlobalException(Exception ex) {
//        de.neuefische.springexceptionhandlingtask.ErrorMessage errorMessage = new de.neuefische.springexceptionhandlingtask.ErrorMessage("Ein Fehler ist aufgetreten: " + ex.getMessage());
//        return errorMessage;
//    }
//}