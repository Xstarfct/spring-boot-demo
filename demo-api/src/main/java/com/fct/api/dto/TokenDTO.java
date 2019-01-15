package com.fct.api.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author xstarfct
 * @version 2019/1/11 4:11 PM
 */
@Data
public class TokenDTO implements Serializable {

    private static final long serialVersionUID = -8855656882693993026L;
    private String token;
    private String tokenHead;

    public TokenDTO(String token, String tokenHead) {
        this.token = token;
        this.tokenHead = tokenHead;
    }
}
