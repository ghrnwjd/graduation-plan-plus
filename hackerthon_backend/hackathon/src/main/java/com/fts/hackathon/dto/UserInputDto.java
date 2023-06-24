package com.fts.hackathon.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDto {
    String userInput;

    String professorName;

    String subjectName;
}
