package com.anwjrrp33.board.service;

import com.anwjrrp33.board.dto.BoardDTO;
import com.anwjrrp33.board.dto.PageRequestDTO;
import com.anwjrrp33.board.dto.PageResultDTO;
import com.anwjrrp33.board.entity.Board;
import com.anwjrrp33.board.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder().email(dto.getWriterEmail()).
                build();
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    // BoardService 인터페이스에 주가하는 entityToDTO( )
    default BoardDTO entityToDTO(Board board, Member member, Long replyCount){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName (member.getName())
                .replyCount(replyCount.intValue()) // long으로 나오므로 int로 저리 하도록
                .build();
        return boardDTO;
    }

    PageResultDTO<BoardDTO, Object[] > getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);
}
