package com.fykj.scaffold.security.business.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2ErrorDto {
    private String error;
    private String error_description;
}
