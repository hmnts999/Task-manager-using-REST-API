package com.jazasoft.taskmanager.restcontroller;

import com.jazasoft.taskmanager.entity.Label;
import com.jazasoft.taskmanager.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/labels")
public class LabelRestController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        Collection<Label> labels = labelService.findAll();
        return ResponseEntity.ok(labels);
    }

    @GetMapping("/{labelId}")
    public ResponseEntity<?> findOne(@PathVariable("labelId") Long id) {
        Optional<Label> optionalLabel = Optional.ofNullable(labelService.findOne(id));
        if (optionalLabel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalLabel.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Label label) {
        label = labelService.save(label);
        return new ResponseEntity<>(label, HttpStatus.CREATED);
    }

    @PutMapping("/{labelId}")
    public ResponseEntity<?> update(@PathVariable("labelId") Long id, @RequestBody Label label) {
        if (!labelService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        label.setId(id);
        label = labelService.update(label);
        return ResponseEntity.ok(label);
    }

    @DeleteMapping("/{labelId}")
    public ResponseEntity<Label> delete(@PathVariable("labelId") Long id) {
        if (!labelService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        labelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}