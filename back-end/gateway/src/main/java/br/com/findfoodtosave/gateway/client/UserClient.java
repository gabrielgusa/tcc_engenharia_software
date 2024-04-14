package br.com.findfoodtosave.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.findfoodtosave.gateway.dto.UserDTO;

@FeignClient(value = "userClient", url = "${back-end.api-user.uri}/")
public interface UserClient {
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/me")
    UserDTO getUser(@RequestHeader("Authorization") String token);
    
}
