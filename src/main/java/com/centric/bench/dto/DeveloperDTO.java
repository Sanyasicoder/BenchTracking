package com.centric.bench.dto;

import lombok.Data;

@Data
public class DeveloperDTO {
	private Long id;
    private String name;
    private boolean onBench;
    private String lastProjectName;

}
