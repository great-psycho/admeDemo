package com.adme.admedemo.repository;

import com.adme.admedemo.domain.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoFile, Long> {
}
