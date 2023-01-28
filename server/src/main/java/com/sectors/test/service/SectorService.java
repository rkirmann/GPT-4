package com.sectors.test.service;

import com.sectors.test.dto.SectorDto;
import com.sectors.test.dto.UserSectorDto;
import com.sectors.test.model.Sector;
import com.sectors.test.model.User;
import com.sectors.test.repository.SectorRepository;
import com.sectors.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private UserRepository userRepository;

    public List<SectorDto> findAll() {
        return createSectorTree(sectorRepository.findAll()) ;
    }


    private List<SectorDto> createSectorTree(List<Sector> sectors) {
        List<SectorDto> sectorDtos = new ArrayList<>();
        for (Sector sector : sectors) {
            if (sector.getParent() == null) {
                SectorDto sectorDto = new SectorDto();
                sectorDto.setId(sector.getId());
                sectorDto.setName(sector.getName());
                sectorDto.setChildren(getChildren(sector, sectors));
                sectorDtos.add(sectorDto);
            }
        }
        return sectorDtos;
    }

    private List<SectorDto> getChildren(Sector sector, List<Sector> sectors) {
        List<SectorDto> children = new ArrayList<>();
        for (Sector child : sectors) {
            if (child.getParent() != null && Objects.equals(child.getParent().getId(), sector.getId())) {
                SectorDto sectorDto = new SectorDto();
                sectorDto.setId(child.getId());
                sectorDto.setName(child.getName());
                sectorDto.setChildren(getChildren(child, sectors));
                children.add(sectorDto);
            }
        }
        return children;
    }


    public UserSectorDto saveUserData(UserSectorDto userSectorDto) {
        User user = userRepository.save(new User(userSectorDto));
        return new UserSectorDto(user);
    }
}
