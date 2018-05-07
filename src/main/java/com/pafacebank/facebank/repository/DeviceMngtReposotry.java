package com.pafacebank.facebank.repository;

import com.pafacebank.facebank.entity.DeviceMngtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceMngtReposotry extends JpaRepository<DeviceMngtEntity, String> {
}
