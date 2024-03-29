package com.example.medatlas.service.impl;

import com.example.medatlas.dto.InstanceDataDTO;
import com.example.medatlas.dto.SeriesDTO;
import com.example.medatlas.dto.SeriesDTOWithoutStudy;
import com.example.medatlas.dto.StudyDTO;
import com.example.medatlas.mapper.InstanceDataMapper;
import com.example.medatlas.mapper.SeriesMapper;
import com.example.medatlas.model.AnatomicalStructure;
import com.example.medatlas.model.AnatomicalStructureSubject;
import com.example.medatlas.model.InstanceData;
import com.example.medatlas.model.Series;
import com.example.medatlas.repository.AnatomicalStructureRepository;
import com.example.medatlas.repository.InstanceDataRepository;
import com.example.medatlas.repository.SeriesRepository;
import com.example.medatlas.service.SeriesService;
import com.example.medatlas.service.StudyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final SeriesMapper seriesMapper;
    private final StudyService studyService;
    private final InstanceDataRepository instanceDataRepository;
    private final InstanceDataMapper instanceDataMapper;
    private final AnatomicalStructureRepository anatomicalStructureRepository;

    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository, SeriesMapper seriesMapper, StudyService studyService,
                             InstanceDataRepository instanceDataRepository, InstanceDataMapper instanceDataMapper, AnatomicalStructureRepository anatomicalStructureRepository) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
        this.studyService = studyService;
        this.instanceDataRepository = instanceDataRepository;
        this.instanceDataMapper = instanceDataMapper;
        this.anatomicalStructureRepository = anatomicalStructureRepository;
    }

    @Override
    public SeriesDTO createSeries(SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());
        if (parentStudy == null) {
            throw new EntityNotFoundException("Study not found with id: " + seriesDTO.getStudyId());
        }

        Series series = seriesMapper.toEntity(seriesDTO);
        series = seriesRepository.save(series);
        return seriesMapper.toDTO(series);
    }

    @Override
    public SeriesDTO getSeriesById(UUID id) {
        Series series = seriesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));

        SeriesDTO seriesDTO = seriesMapper.toDTO(series);

        // Здесь получаем данные InstanceData для указанной серии
        List<InstanceDataDTO> instanceDataDTOList = getInstanceDataForSeries(id);

        // Здесь выполняем необходимые операции с InstanceDataDTOList
        // Например, добавим информацию о цвете темы в каждый InstanceDataDTO
        List<InstanceDataDTO> modifiedInstanceDataDTOList = instanceDataDTOList.stream()
                .peek(instanceDataDTO -> {
                    // Получаем structureId из InstanceDataDTO
                    UUID structureId = instanceDataDTO.getStructureId();

                    // Получаем цвет темы (subjectColor) по structureId
                    String subjectColor = getSubjectColorByStructureId(structureId);

                    // Устанавливаем subjectColor в InstanceDataDTO
                    instanceDataDTO.setSubjectColor(subjectColor);
                })
                .collect(Collectors.toList());

        // Устанавливаем модифицированный InstanceDataDTOList в серию
        seriesDTO.setInstanceDataList(modifiedInstanceDataDTOList);

        // Получаем родительское исследование (study) для серии
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());

        if (parentStudy != null) {
            seriesDTO.setStudyExternalId(parentStudy.getExternalId());
        }

        return seriesDTO;
    }

    @Override
    public List<SeriesDTOWithoutStudy> getAllSeries() {
        List<Series> seriesList = seriesRepository.findAll();
        return seriesList.stream().map(seriesMapper::toDTOWithoutStudy).collect(Collectors.toList());
    }

    @Override
    public SeriesDTO updateSeries(UUID id, SeriesDTO seriesDTO) {
        StudyDTO parentStudy = studyService.getStudyById(seriesDTO.getStudyId());
        if (parentStudy == null) {
            throw new EntityNotFoundException("Study not found with id: " + seriesDTO.getStudyId());
        }

        Series existingSeries = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        Series updatedSeries = seriesMapper.toEntity(seriesDTO);
        updatedSeries.setId(existingSeries.getId());
        updatedSeries = seriesRepository.save(updatedSeries);
        return seriesMapper.toDTO(updatedSeries);
    }

    @Override
    public void deleteSeries(UUID id) {
        Series series = seriesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Series not found with id: " + id));
        seriesRepository.delete(series);
    }

    @Override
    public List<InstanceDataDTO> getInstanceDataForSeries(UUID id) {
        List<InstanceData> instanceDataList = instanceDataRepository.findBySeriesId(id);
        return instanceDataList.stream()
                .map(instanceDataMapper::toDTO)
                .collect(Collectors.toList());
    }
    private String getSubjectColorByStructureId(UUID structureId) {
        AnatomicalStructure anatomicalStructure = anatomicalStructureRepository.findById(structureId)
                .orElseThrow(() -> new EntityNotFoundException("AnatomicalStructure not found with id: " + structureId));

        AnatomicalStructureSubject anatomicalStructureSubject = anatomicalStructure.getAnatomicalStructureSubject();

        if (anatomicalStructureSubject != null) {
            return anatomicalStructureSubject.getColor();
        } else {
            return null; // Или верните дефолтное значение, если цвет не найден
        }
    }
}