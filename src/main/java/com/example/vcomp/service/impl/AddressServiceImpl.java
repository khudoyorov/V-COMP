package com.example.vcomp.service.impl;

import com.example.vcomp.dto.AddressDto;
import com.example.vcomp.dto.ResponseDto;
import com.example.vcomp.model.AddressModel;
import com.example.vcomp.repository.AddressRepository;
import com.example.vcomp.service.AddressService;
import com.example.vcomp.service.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public ResponseEntity<ResponseDto<AddressDto>> add(AddressDto addressDto) {
        try{
            AddressModel address = addressMapper.toModel(addressDto);
            addressRepository.save(address);

            return ResponseEntity.ok().body(
                    ResponseDto.<AddressDto>builder()
                            .message("OK")
                            .success(true)
                            .data(addressDto)
                            .build()
                    );
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<AddressDto>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<AddressDto>> getById(Integer id) {
        if (id == null){
            return ResponseEntity.badRequest().body(
                    ResponseDto.<AddressDto>builder()
                            .message("Id is null")
                            .build()
            );
        }
        try {
            Optional<AddressModel> addressModel = addressRepository.findById(id);

            return addressModel.map(model -> ResponseEntity.ok(
                    ResponseDto.<AddressDto>builder()
                            .message("OK")
                            .success(true)
                            .data(addressMapper.toDto(model))
                            .build()
            )).orElseGet(() -> ResponseEntity.badRequest().body(
                    ResponseDto.<AddressDto>builder()
                            .message("User not found")
                            .build()
            ));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<AddressDto>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<List<AddressDto>>> getAllByUserId(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(ResponseDto.<List<AddressDto>>builder()
                    .message("Id is null")
                    .build()
            );
        }
        try{
            return ResponseEntity.ok().body(
                    ResponseDto.<List<AddressDto>>builder()
                            .message("OK")
                            .success(true)
                            .data(addressRepository.findAllByUserId(id).stream().map(addressMapper::toDto).toList())
                            .build()
            );
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<List<AddressDto>>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> delete(Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(ResponseDto.<Void>builder()
                    .message("Id is null")
                    .build()
            );
        }
        try{
            Optional<AddressModel> byId = addressRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseEntity.badRequest().body(
                        ResponseDto.<Void>builder()
                        .message("Not found")
                        .build()
                );
            }

            addressRepository.deleteById(id);

            return ResponseEntity.ok().body(
                    ResponseDto.<Void>builder()
                            .message("OK")
                            .success(true)
                            .build()
            );
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(
                    ResponseDto.<Void>builder()
                            .message("Database error : " + e.getMessage())
                            .build()
            );
        }
    }
}
