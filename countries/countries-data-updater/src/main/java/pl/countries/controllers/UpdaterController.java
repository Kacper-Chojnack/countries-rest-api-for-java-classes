package pl.countries.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.countries.updater.mappers.IUpdate;

import java.time.LocalDate;


@Controller
public class UpdaterController {

    private final IUpdate updater;

    public UpdaterController(IUpdate updater) {
        this.updater = updater;
    }

    @GetMapping("update")
    public ResponseEntity update(){
        new Thread(updater::update).start();
        return ResponseEntity.ok("Data download has started at: "+ LocalDate.now());
    }

}
