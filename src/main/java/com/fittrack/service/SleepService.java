package com.fittrack.service;


import com.fittrack.dto.SleepDto;
import com.fittrack.model.Sleep;
import com.fittrack.model.User;
import com.fittrack.repository.SleepRepository;
import com.fittrack.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class SleepService {
    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;


    public SleepService(SleepRepository sleepRepository, UserRepository userRepository) {
        this.sleepRepository = sleepRepository;
        this.userRepository = userRepository;
    }


    public SleepDto create(SleepDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Sleep s = new Sleep();
        s.setDate(dto.getDate());
        s.setHours(dto.getHours());
        s.setUser(user);
        Sleep saved = sleepRepository.save(s);
        dto.setId(saved.getId());
        return dto;
    }


    public List<SleepDto> listByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return sleepRepository.findByUserOrderByDateDesc(user).stream().map(s -> {
            SleepDto d = new SleepDto();
            d.setId(s.getId());
            d.setDate(s.getDate());
            d.setHours(s.getHours());
            d.setUserId(userId);
            return d;
        }).collect(Collectors.toList());
    }


    public void delete(Long id) {
        sleepRepository.deleteById(id);
    }
}