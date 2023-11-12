package com.example.medatlas.dto;

import com.example.medatlas.model.Study;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Series")
public class SeriesDTO extends SeriesDTOWithoutStudy {
    private UUID id;
    private int number;
    private String name;
    private UUID studyId;
    private String previewFrame;
    private int instanceCount;
    private String sagitalFrame;
    private String coronalFrame;
}