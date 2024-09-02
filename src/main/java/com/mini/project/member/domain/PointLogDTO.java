package com.mini.project.member.domain;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PointLogDTO {
	private String pointWho;
	private String pointWhy;
}
