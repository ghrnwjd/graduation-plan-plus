package com.fts.hackathon.dto;


import com.fts.hackathon.model.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ResponseDto {
    private ResponseStatus responseStatus;

}
