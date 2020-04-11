package com.jazasoft.taskmanager.service;

import com.jazasoft.taskmanager.entity.Label;
import com.jazasoft.taskmanager.repository.LabelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LabelService {
    private LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Collection<Label> findAll() {
        return labelRepository.findAll();
    }

    public Label findOne(Long id) {
        return labelRepository.findById(id).orElse(null);
    }

    @Transactional
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Transactional
    public Label update(Label label) {
        Label mLabel = labelRepository.findById(label.getId()).orElseThrow();
        mLabel.setName(label.getName());
        return mLabel;
    }

    @Transactional
    public void delete(Long id) {
        labelRepository.deleteById(id);
    }

    public boolean exists(Long id) {

        return labelRepository.existsById(id);
    }

}
