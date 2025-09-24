package com.fittrack.service;


import com.fittrack.dto.WaterDto;
import com.fittrack.model.User;
import com.fittrack.model.Water;
import com.fittrack.repository.UserRepository;
import com.fittrack.repository.WaterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class WaterService {
    private final WaterRepository waterRepository;
    private final UserRepository userRepository;


    public WaterService(WaterRepository waterRepository, UserRepository userRepository) {
        this.waterRepository = waterRepository;
        this.userRepository = userRepository;
    }


    public WaterDto create(WaterDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Water w = new Water();
        w.setMl(dto.getMl());
        w.setLoggedAt(dto.getLoggedAt() == null ? LocalDateTime.now() : dto.getLoggedAt());
        w.setUser(user);
        Water saved = waterRepository.save(w);
        dto.setId(saved.getId());
        return dto;
    }


    public List<WaterDto> listByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return waterRepository.findByUserOrderByLoggedAtDesc(user).stream().map(w -> {
            WaterDto d = new WaterDto();
            d.setId(w.getId());
            d.setMl(w.getMl());
            d.setLoggedAt(w.getLoggedAt());
            d.setUserId(userId);
            return d;
        }).collect(Collectors.toList());
    }


    public void delete(Long id) {
        waterRepository.deleteById(id);
    }
}