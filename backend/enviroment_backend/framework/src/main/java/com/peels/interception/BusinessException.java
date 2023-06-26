package com.peels.interception;

import lombok.Data;

/**
 * @Author peelsannaw
 * @create 26/06/2023 15:13
 */
@Data
public class BusinessException extends RuntimeException{

    String code;
    String errorMessage;


}
