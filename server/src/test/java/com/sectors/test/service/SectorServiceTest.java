package com.sectors.test.service;

import com.sectors.test.dto.SectorDto;
import com.sectors.test.dto.UserSectorDto;
import com.sectors.test.model.Sector;
import com.sectors.test.model.User;
import com.sectors.test.repository.SectorRepository;
import com.sectors.test.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * tests for class:
 * package com.sectors.test.service;
 *
 * import com.sectors.test.dto.SectorDto;
 * import com.sectors.test.dto.UserSectorDto;
 * import com.sectors.test.model.Sector;
 * import com.sectors.test.model.User;
 * import com.sectors.test.repository.SectorRepository;
 * import com.sectors.test.repository.UserRepository;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.stereotype.Service;
 *
 * import java.util.ArrayList;
 * import java.util.List;
 * import java.util.Objects;
 *
 * @Service
 * public class SectorService {
 *
 *     @Autowired
 *     private SectorRepository sectorRepository;
 *     @Autowired
 *     private UserRepository userRepository;
 *
 *     public List<SectorDto> findAll() {
 *         return createSectorTree(sectorRepository.findAll()) ;
 *     }
 *
 *
 *     public List<SectorDto> createSectorTree(List<Sector> sectors) {
 *         List<SectorDto> sectorDtos = new ArrayList<>();
 *         for (Sector sector : sectors) {
 *             if (sector.getParent() == null) {
 *                 SectorDto sectorDto = new SectorDto();
 *                 sectorDto.setId(sector.getId());
 *                 sectorDto.setName(sector.getName());
 *                 sectorDto.setChildren(getChildren(sector, sectors));
 *                 sectorDtos.add(sectorDto);
 *             }
 *         }
 *         return sectorDtos;
 *     }
 *
 *     private List<SectorDto> getChildren(Sector sector, List<Sector> sectors) {
 *         List<SectorDto> children = new ArrayList<>();
 *         for (Sector child : sectors) {
 *             if (child.getParent() != null && Objects.equals(child.getParent().getId(), sector.getId())) {
 *                 SectorDto sectorDto = new SectorDto();
 *                 sectorDto.setId(child.getId());
 *                 sectorDto.setName(child.getName());
 *                 sectorDto.setChildren(getChildren(child, sectors));
 *                 children.add(sectorDto);
 *             }
 *         }
 *         return children;
 *     }
 *
 *
 *     public UserSectorDto saveUserData(UserSectorDto userSectorDto) {
 *         User user = userRepository.save(new User(userSectorDto));
 *         return new UserSectorDto(user);
 *     }
 * }
 * */
@RunWith(MockitoJUnitRunner.class)
public class SectorServiceTest {

    @InjectMocks
    SectorService sectorService;

    @Mock
    SectorRepository sectorRepository;
    @Mock
    UserRepository userRepository;

    @Test
    public void findAll() {
        var sector = new Sector();
        sector.setId(1L);
        sector.setName("sector");

        var sector2 = new Sector();
        sector2.setId(2L);
        sector2.setName("sector");
        sector2.setParent(sector);

        when(sectorRepository.findAll()).thenReturn(List.of(sector, sector2));

        var result = sectorService.findAll();
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getChildren().size());
    }



    @Test
    public void saveUserData() {
        var sectorDto = new UserSectorDto();
        sectorDto.setId(1L);
        sectorDto.setName("user");
        sectorDto.setSectors(List.of(1L, 2L));

        var user = new User();
        user.setId(1L);
        user.setName("user");
        user.setSectors(List.of(new Sector(1L), new Sector(2L)));

        when(userRepository.save(any())).thenReturn(user);
        sectorService.saveUserData(sectorDto);
        verify(userRepository).save(any());
    }
}
