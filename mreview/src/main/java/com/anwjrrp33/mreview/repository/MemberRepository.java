package com.anwjrrp33.mreview.repository;

import com.anwjrrp33.mreview.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
