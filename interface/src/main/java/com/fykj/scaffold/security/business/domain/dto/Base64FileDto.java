package com.fykj.scaffold.security.business.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import constants.Mark;
import lombok.Data;

/**
 * @author zhangzhi
 */
@Data
public class Base64FileDto {

    private String base64;

    @JsonIgnore
    private String fileName;

    public Base64FileDto buildFileName() {
        int start = base64.indexOf(Mark.SLASH);
        int end = base64.indexOf(Mark.SEMICOLON);
        if (start <= end) {
            String ext = base64.substring(start+1, end);
            this.fileName = System.currentTimeMillis() + Mark.DOT + ext;
        }
        return this;
    }
}
