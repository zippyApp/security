package com.zippy.security.clients.interfaces;

import com.zippy.security.DTO.PersonalInformationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "users", url = "http://localhost:8090/api/v1/users")
public interface PersonalInformationClient {

    @RequestMapping(method = RequestMethod.POST, value = "/new", consumes = "application/json", produces = "application/json")
    PersonalInformationDTO create(PersonalInformationDTO personalInformation);
}
