package com.jazasoft.taskmanager.repository;

import com.jazasoft.taskmanager.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
