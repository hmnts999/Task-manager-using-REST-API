package com.jazasoft.taskmanager.restcontroller;

import com.jazasoft.taskmanager.entity.Task;
import com.jazasoft.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        Collection<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> findOne(@PathVariable("taskId") Long id) {
        Optional<Task> optionalTask = taskService.findOne(id);
        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(optionalTask.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Task task ) {
        task = taskService.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> update(@PathVariable("taskId") Long id, @RequestBody @Valid Task task) {
        if (!taskService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        task.setId(id);
        task = taskService.update(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> delete(@PathVariable("taskId") Long id) {
        if (!taskService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}