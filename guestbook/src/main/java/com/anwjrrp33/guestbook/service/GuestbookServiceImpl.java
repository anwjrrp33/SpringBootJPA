package com.anwjrrp33.guestbook.service;

import com.anwjrrp33.guestbook.dto.GuestbookDTO;
import com.anwjrrp33.guestbook.entity.Guestbook;
import com.anwjrrp33.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {
    
    private final GuestbookRepository repository; // 반드시 final로 선언

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO------------------------");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getGno();
    }
}
