package br.com.findfoodtosave.storemanagement.application.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.findfoodtosave.storemanagement.application.mapper.ApplicationAddressMapper;
import br.com.findfoodtosave.storemanagement.application.mapper.ApplicationStoreMapper;
import br.com.findfoodtosave.storemanagement.application.request.CreateStore;
import br.com.findfoodtosave.storemanagement.application.request.UpdateStore;
import br.com.findfoodtosave.storemanagement.application.response.AddressDTO;
import br.com.findfoodtosave.storemanagement.application.response.StoreDTO;
import br.com.findfoodtosave.storemanagement.domain.usecase.CreateStoreUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.DeleteStoreUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.FindStoreByAddressUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.UpdateStoreUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.FindStoreByIdUserUseCase;
import jakarta.validation.Valid;

@RestController
public class StoreController {
    
    private final ApplicationStoreMapper applicationStoreMapper;
    private final ApplicationAddressMapper applicationAddressMapper;
    private final CreateStoreUseCase createStoreUseCase;
    private final UpdateStoreUseCase updateStoreUseCase;
    private final FindStoreByAddressUseCase findStoreByAddressUseCase;
    private final FindStoreByIdUserUseCase findStoreByIdUserUseCase;
    private final DeleteStoreUseCase deleteStoreUseCase;

    public StoreController(
        ApplicationStoreMapper applicationStoreMapper,
        ApplicationAddressMapper applicationAddressMapper,
        CreateStoreUseCase createStoreUseCase,
        UpdateStoreUseCase updateStoreUseCase,
        FindStoreByAddressUseCase findStoreByAddressUseCase,
        FindStoreByIdUserUseCase findStoreByIdUserUseCase,
        DeleteStoreUseCase deleteStoreUseCase
    ){
        this.applicationStoreMapper = applicationStoreMapper;
        this.createStoreUseCase = createStoreUseCase;
        this.updateStoreUseCase = updateStoreUseCase;
        this.findStoreByAddressUseCase = findStoreByAddressUseCase;
        this.applicationAddressMapper = applicationAddressMapper;
        this.findStoreByIdUserUseCase = findStoreByIdUserUseCase;
        this.deleteStoreUseCase = deleteStoreUseCase;
    }

    @PostMapping(path = "/api/v1/users/{id_user}/stores")
    public StoreDTO save(
        @PathVariable(name = "id_user", required = true) String idUser, 
        @Valid @RequestBody CreateStore createStore
    ){
        return this.applicationStoreMapper.toDTO(this.createStoreUseCase.create(idUser, this.applicationStoreMapper.toModel(createStore)));
    }

    @PutMapping(path = "/api/v1/users/{id_user}/stores/{id_store}")
    public StoreDTO update(
        @PathVariable(name = "id_user", required = true) String idUser, 
        @PathVariable(name = "id_store", required = true) String idStore,
        @Valid @RequestBody UpdateStore updateStore
    ){
        return this.applicationStoreMapper.toDTO(this.updateStoreUseCase.update(idStore, idUser, this.applicationStoreMapper.toModel(updateStore)));
    }

    @DeleteMapping(path = "/api/v1/users/{id_user}/stores/{id_store}")
    public void delete(
        @PathVariable(name = "id_user", required = true) String idUser, 
        @PathVariable(name = "id_store", required = true) String idStore
    ){
        this.deleteStoreUseCase.deleteByIdAndIdUser(idStore, idUser);
    }

    @GetMapping(path = "/api/v1/users/{id_user}/stores")
    public StoreDTO findByIdUser(
        @PathVariable(name = "id_user", required = true) String idUser
    ){
        return this.applicationStoreMapper.toDTO(this.findStoreByIdUserUseCase.findByIdUser(idUser));
    }

    @GetMapping(path = "/api/v1/stores")
    public List<StoreDTO> findByAddress(
        @RequestParam(name = "state", required = true) String state,
        @RequestParam(name = "city", required = false) String city,
        @RequestParam(name = "district", required = false) String district
    ){
        var addressDTO = AddressDTO.builder().state(state).city(city).district(district).build();
        return this.applicationStoreMapper.toDTO(this.findStoreByAddressUseCase.findAllByAddress(this.applicationAddressMapper.toModel(addressDTO)));
    }
}
