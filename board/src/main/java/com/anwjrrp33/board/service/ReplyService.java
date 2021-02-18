package com.anwjrrp33.board.service;

import com.anwjrrp33.board.dto.ReplyDTO;
import com.anwjrrp33.board.entity.Board;
import com.anwjrrp33.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO); // 댓글의 등록

    List<ReplyDTO> getList(Long bno); // 특정 게시물의 댓글 목록

    void modify(ReplyDTO replyDTO); // 댓글 수정

    void remove(Long rno); // 댓글 삭제

    // ReplyDTO를 Reply객제로 변환 Board 객제의 저리가 수반됨
    default Reply dtoToEntity(ReplyDTO replyDTO){
        Board board = Board.builder().bno(replyDTO.getBno()).build();
        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build () ;
        return reply;
    }

    // Reply객제를 ReplyDTO로 변환 Board 객제가 필요하지 않으므로 게시물 번호만
    default ReplyDTO entityToDTO(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }
}
