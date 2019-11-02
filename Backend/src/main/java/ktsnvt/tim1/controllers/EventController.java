package ktsnvt.tim1.controllers;

import ktsnvt.tim1.DTOs.EventDTO;
import ktsnvt.tim1.exceptions.EntityNotFoundException;
import ktsnvt.tim1.exceptions.EntityNotValidException;
import ktsnvt.tim1.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(eventService.getEvent(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createEvent(@Valid @RequestBody EventDTO event) {
        try {
            return new ResponseEntity<>(eventService.createEvent(event), HttpStatus.CREATED);
        }
        catch(ParseException e) {
            return new ResponseEntity<>("Dates of event days are in invalid format", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> editEvent(@Valid @RequestBody EventDTO event) {
        try {
            return new ResponseEntity<>(eventService.editEvent(event), HttpStatus.OK);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }
        catch(ParseException e) {
            return new ResponseEntity<>("Dates of event days are in invalid format", HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
