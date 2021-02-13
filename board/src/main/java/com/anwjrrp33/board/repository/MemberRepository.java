package com.anwjrrp33.board.repository;

import com.anwjrrp33.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
