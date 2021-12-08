package com.templates.usecases.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableDataDto<T> {
		
	// Current page index ( 0 ~ x )
	private int pageIndex;
	
	// The size of page ( elements TO BE displayed in 1 page )
	private int pageSize;
	
	// Actual element counts in this page
	private int pageElementCount;
	
	private int totalPages;
	
	private long totalElements;
	
	// Fetched elements of current Page
	private List<T> elementList;
}
