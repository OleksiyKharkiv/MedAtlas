package com.example.medatlas.dto;

import com.example.medatlas.model.enums.InstanceDataStatus;
import com.example.medatlas.model.enums.InstanceDataType;
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
@ApiModel(description = "Instance Data")
public class InstanceDataDTO {
    private UUID id;
    private UUID studyId;
    private String studyName;
    private UUID seriesId;
    private String seriesName;
    private int seriesNumber;
    private String subjectColor;
    private UUID structureId;
    private String structureName;
    private int instanceNumber;
    private InstanceDataType type;
    private double x;
    private double y;
    private String path;
    private InstanceDataStatus status;
}