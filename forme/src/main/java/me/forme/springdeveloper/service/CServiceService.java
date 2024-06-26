package me.forme.springdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.forme.springdeveloper.domain.CService;
import me.forme.springdeveloper.domain.Checklist;
import me.forme.springdeveloper.dto.AddCServiceRequest;
import me.forme.springdeveloper.dto.UpdateCServiceRequest;
import me.forme.springdeveloper.repository.CServiceRepository;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CServiceService {
    private final CServiceRepository cServiceRepository;

    // 고객센터 글 등록 메서드
    public CService save(AddCServiceRequest request, String userId) {
        return cServiceRepository.save(request.toEntity(userId));
    }

    // 고객센터 글 목록 조회 메서드
    public List<CService> findAll() {return cServiceRepository.findAll();}

    // 고객센터 글 조회 (1개) 메서드
    public CService findById(long id) {
        return cServiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    // 고객센터 글 삭제 메서드
    public void delete(long id) {
        cServiceRepository.deleteById(id);
    }

    // 고객센터 글 수정 메서드
    @Transactional
    public CService update(long id, UpdateCServiceRequest request) {
        CService service = cServiceRepository.findById(id).orElse(null);
        if(service != null) {
            service.update(request.getTitle(), request.getContent(), LocalTime.now());
            cServiceRepository.save(service);
        }
        return service;
    }
}
