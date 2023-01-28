package com.sectors.test.dto;

import com.sectors.test.model.Sector;
import com.sectors.test.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserSectorDto {
    private long id;
    private String name;
    private List<Long> sectors;

    public UserSectorDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.sectors = user.getSectors().stream().map(Sector::getId).collect(Collectors.toList());
    }
}
