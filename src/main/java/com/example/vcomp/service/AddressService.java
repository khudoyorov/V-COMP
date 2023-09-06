package com.example.vcomp.service;

import com.example.vcomp.dto.AddressDto;
import com.example.vcomp.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    ResponseEntity<ResponseDto<AddressDto>> add(AddressDto addressDto);
    ResponseEntity<ResponseDto<AddressDto>> getById(Integer id);
    ResponseEntity<ResponseDto<List<AddressDto>>> getAllByUserId(Integer id);
    ResponseEntity<ResponseDto<Void>> delete(Integer id);
}
