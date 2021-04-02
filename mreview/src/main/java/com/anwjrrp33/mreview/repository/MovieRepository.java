package com.anwjrrp33.mreview.repository;

import com.anwjrrp33.mreview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

//    @Query("select m, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
//            "left outer join Review r on r.movie = m group by m")
//    @Query("select m, max(mi), avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
//            "left outer join MovieImage mi on mi.movie = m " +
//            "left outer join Review r on r.movie = m group by m")
//    @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) from Movie m " +
//            "left outer join MovieImage mi on mi.movie = m " +
//            "left outer join Review r on r.movie = m group by m")
    // H2 database로 실습을 진행할 경우 MariaDB와 다른 쿼리로 실행해야한다.
//    @Query("select m, min(mi.inum), min(mi.imgName), avg(coalesce(r.grade,0)), count(distinct r.reviewnum) from Movie m " +
//            "left outer join MovieImage mi on mi.movie = m "+
//            "left outer join Review r on r.movie = m group by m.mno, m.title, m.regDate, m.modDate, mi.movie , r.movie")
    @Query("select m, min(mi.inum), min(mi.imgName) , avg(coalesce(r.grade,0)), count(distinct r.reviewnum) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m "+
            "left outer join Review r on r.movie = m " +
            "where mi.inum in (select min(mi2.inum) from MovieImage mi2 group by mi2.movie) " +
            "group by m.mno, m.title, m.regDate, m.modDate, mi.movie , r.movie")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m, mi ,avg(coalesce(r.grade,0)), count(r)" +
            " from Movie m left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review  r on r.movie = m "+
            " where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(Long mno);
}
