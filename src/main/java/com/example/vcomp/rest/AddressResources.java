package com.example.vcomp.rest;

import com.example.vcomp.dto.AddressDto;
import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressResources {
    private final AddressService addressService;

    @Operation(
            method = "Add new address",
            description = "Need to send AddressDto to this endpoint to create new address",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Address info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseEntity<ResponseDto<AddressDto>> add(@RequestBody AddressDto addressDto){
        return addressService.add(addressDto);
    }

    @Operation(
            method = "Get all user's addresses",
            description = "Need to send User's id to return all user's addresses",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Address info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseEntity<ResponseDto<List<AddressDto>>> getAll(@RequestParam Integer id){
        return addressService.getAllByUserId(id);
    }

    @Operation(
            method = "Get address by id",
            description = "Need to send id to this endpoint to get address",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Address info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<AddressDto>> getById(@PathVariable Integer id){
        return addressService.getById(id);
    }

    @Operation(
            method = "Delete address",
            description = "Need to send address id to this endpoint to delete this address",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Address info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> delete(@PathVariable Integer id){
        return addressService.delete(id);
    }
}
