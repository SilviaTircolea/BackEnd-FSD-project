package com.twitter.app.model.response;

import com.twitter.app.utill.CommonVarList;
import lombok.Data;

@Data
public class Response {

    private String responseCode;
    private String responseMessage;
    private Object responseData;
}
