package com.sectors.test.dto;

import com.sectors.test.model.Sector;
import lombok.Data;

import java.util.List;

@Data
public class SectorDto {
    private long id;
    private String name;
    private List<SectorDto> children;


}
