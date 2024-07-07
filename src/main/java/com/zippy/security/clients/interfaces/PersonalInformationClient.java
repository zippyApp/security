package com.zippy.security.clients.interfaces;

import com.zippy.security.DTO.PersonalInformationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "users", url = "http://localhost:8090/api/v1/users")
public interface PersonalInformationClient {

    @PostMapping("/new")
    Optional<PersonalInformationDTO> create(PersonalInformationDTO personalInformation);

    @GetMapping("/{id}")
    Optional<PersonalInformationDTO> get(@PathVariable Long id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
