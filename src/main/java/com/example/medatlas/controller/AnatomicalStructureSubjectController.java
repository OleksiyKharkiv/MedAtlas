package com.example.medatlas.controller;

import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
import com.example.medatlas.mapper.AnatomicalStructureSubjectMapper;
import com.example.medatlas.service.AnatomicalStructureSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/anatomical-structure-subjects")
public class AnatomicalStructureSubjectController {

    private final AnatomicalStructureSubjectService subjectService;
    private final AnatomicalStructureSubjectMapper subjectMapper;

    @Autowired
    public AnatomicalStructureSubjectController(AnatomicalStructureSubjectService subjectService, AnatomicalStructureSubjectMapper subjectMapper) {
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<AnatomicalStructureSubjectDTO> createSubject(@RequestBody AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubjectDTO createdSubject = subjectService.createAnatomicalStructureSubject(subjectDTO);
        return ResponseEntity.ok(createdSubject);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AnatomicalStructureSubjectDTO> getSubjectById(@PathVariable UUID id) {
        AnatomicalStructureSubjectDTO subjectDTO = subjectService.getAnatomicalStructureSubjectById(id);
        if (subjectDTO != null) {
            return ResponseEntity.ok(subjectDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnatomicalStructureSubjectDTO> updateSubject(@PathVariable UUID id, @RequestBody AnatomicalStructureSubjectDTO subjectDTO) {
        AnatomicalStructureSubjectDTO updatedSubject = subjectService.updateAnatomicalStructureSubject(id, subjectDTO);
        if (updatedSubject != null) {
            return ResponseEntity.ok(updatedSubject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable UUID id) {
        subjectService.deleteAnatomicalStructureSubject(id);
        return ResponseEntity.ok().build();
    }
}