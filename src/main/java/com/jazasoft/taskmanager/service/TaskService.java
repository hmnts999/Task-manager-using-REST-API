package com.jazasoft.taskmanager.service;

import com.jazasoft.taskmanager.entity.Label;
import com.jazasoft.taskmanager.entity.Task;
import com.jazasoft.taskmanager.repository.LabelRepository;
import com.jazasoft.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {
    private TaskRepository taskRepository;
    private LabelRepository labelRepository;

    public TaskService(TaskRepository taskRepository, LabelRepository labelRepository) {
        this.taskRepository = taskRepository;
        this.labelRepository = labelRepository;
    }

    public Collection<Task> findAll() {
        List<Task> taskList = taskRepository.findAll();
        taskList.forEach(task -> task.setLabelId(task.getLabel() != null ? task.getLabel().getId() : null));
        return taskList;
    }

    public Optional<Task> findOne(Long id) {
        Optional<Task> mTask = taskRepository.findById(id);
        mTask.ifPresent(task -> task.setLabelId(task.getLabel().getId()));
        return mTask;
    }

    @Transactional
    public Task save(Task task) {
        Label label = labelRepository.findById(task.getLabelId()).orElse(null);
        task.setLabel(label);
        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Task task) {
        Task mTask = taskRepository.findById(task.getId()).orElse(null);
        assert mTask != null;
        mTask.setName(task.getName());
        if (!mTask.getLabel().getId().equals(task.getLabelId())) {
            Label label = labelRepository.getOne(task.getLabelId());
            mTask.setLabel(label);
        }
        return mTask;
    }

    @Transactional
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return taskRepository.existsById(id);
    }

}