package com.centric.bench.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
public class ResponseDTO<T> {

    private int responseCode;
    private String responseMessage;
    private List<String> errors;
    private T data;

}
