package com.templates.usecases.dto;

import com.templates.usecases.entities.SimpleRecordEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleRecordDto {
	long id;
	String name;
	
	
	public static SimpleRecordDto asSimpleRecordDto(SimpleRecordEntity entity)  {
		return SimpleRecordDto.builder()
					.id(entity.getId())
					.name(entity.getName())
					.build();
	}
}
