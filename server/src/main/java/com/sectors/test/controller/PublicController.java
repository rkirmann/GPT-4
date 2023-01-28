package com.sectors.test.controller;


import com.sectors.test.dto.SectorDto;
import com.sectors.test.dto.UserSectorDto;
import com.sectors.test.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {
    @Autowired
    private SectorService sectorService;

    @GetMapping(value = "sectors")
    public List<SectorDto> getSectors() {
        return sectorService.findAll();
    }

    @PostMapping(value = "sectors")
    public UserSectorDto addSector(@RequestBody UserSectorDto userSectorDto) {
        return sectorService.saveUserData(userSectorDto);
    }

}
