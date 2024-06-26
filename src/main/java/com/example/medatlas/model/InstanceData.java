package com.example.medatlas.model;

import com.example.medatlas.model.enums.InstanceDataStatus;
import com.example.medatlas.model.enums.InstanceDataType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "instance_data", uniqueConstraints = {
        @UniqueConstraint(name = "uk_instance_number_per_structure", columnNames = {"anatomical_structure_id", "instance_number"})
})
@NoArgsConstructor
@AllArgsConstructor
public class InstanceData {
    /**
     * Id	GUID	Идентификатор
     * Study*	Study (Исследование)	Ссылка на объект Исследование
     * Series*	Series (Серия исследования)	Ссылка на объект Серия исследования
     * Structure*	"Anatomical structure (Анатомическая структура)
     * "	Ссылка на объект Анатомическая структура
     * InstanceNumber*	int	Номер кадра
     * Type*	Enum	Варианты: * Point = 0 * Area = 1
     * X	int	Позиция по оси Х, используется если Type = Point
     * Y	int	Позиция по оси Y, используется если Type = Point
     * Path	string	Строка содержащая JSON данные в виде массива с полями “x”, “y” описывающие замкнутую фигуру, используется если Type = Area
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(targetEntity = Study.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", referencedColumnName = "id")
    private Study study;
    @Column(name = "study_name")
    private String studyName;

    @ManyToOne(targetEntity = Series.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id", referencedColumnName = "id")
    private Series series;
    @Column(name = "series_name")
    private String seriesName;
    @Column(name = "series_number")
    private int seriesNumber;

    @ManyToOne(targetEntity = AnatomicalStructure.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "anatomical_structure_id", referencedColumnName = "id")
    private AnatomicalStructure structure;
    @Column(name = "structure_name")
    private String structureName;

    @Column(name = "instance_number")
    private int instanceNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 10)
    private InstanceDataType type;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "path")
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InstanceDataStatus status;

    @PrePersist
    protected void onCreate() {
        this.status = InstanceDataStatus.UNVERIFIED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstanceData that = (InstanceData) o;
        return instanceNumber == that.instanceNumber &&
                type == that.type &&
                Objects.equals(study, that.study) &&
                Objects.equals(series, that.series) &&
                Objects.equals(structure, that.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(study, series, structure, instanceNumber, type);
    }
}