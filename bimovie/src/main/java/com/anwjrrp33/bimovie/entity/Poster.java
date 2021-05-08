package com.anwjrrp33.bimovie.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
@Table(name = "tbl_poster")
public class Poster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ino;

	private String fname;

	// 포스터 순번
	private int idx;

	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
