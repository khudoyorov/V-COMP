package com.example.vcomp.service.mapper;

import com.example.vcomp.dto.PaymentDetailsDto;
import com.example.vcomp.model.PaymentDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentDetailsMapper extends CommonMapper<PaymentDetailsDto, PaymentDetails> {

}
