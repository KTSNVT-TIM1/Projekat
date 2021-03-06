package ktsnvt.tim1.controllers;

import ktsnvt.tim1.DTOs.ReportRequestDTO;
import ktsnvt.tim1.exceptions.BadParametersException;
import ktsnvt.tim1.exceptions.EntityNotFoundException;
import ktsnvt.tim1.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/reports")
@CrossOrigin
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> getReport(@Valid ReportRequestDTO reportRequest) {
        try {
            return new ResponseEntity<>(reportService.getReport(reportRequest), HttpStatus.OK);
        } catch (BadParametersException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
