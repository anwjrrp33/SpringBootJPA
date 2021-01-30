package com.anwjrrp33.guestbook.service;

import com.anwjrrp33.guestbook.dto.GuestbookDTO;
import com.anwjrrp33.guestbook.dto.PageRequestDTO;
import com.anwjrrp33.guestbook.dto.PageResultDTO;
import com.anwjrrp33.guestbook.entity.Guestbook;

public interface GuestbookService {

    public Long register(GuestbookDTO dto);

    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    public GuestbookDTO read(Long gno);

    public void remove(Long gno);

    public void modify(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto  = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
