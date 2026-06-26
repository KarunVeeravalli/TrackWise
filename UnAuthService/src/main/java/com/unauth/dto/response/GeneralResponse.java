package com.unauth.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.unauth.util.Header;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponse {
	private List<Error> errors = new ArrayList<>();
	private String exceptionMsg;
	private List<Exception> exceptions = new ArrayList<>();
	private Object data;
	private String responseCode;
	private String responseMessage;
	private Header header;
	
    public static GeneralResponse success(Object data, Header header) {
        GeneralResponse response = new GeneralResponse();
        response.responseCode = "0000";
        response.responseMessage = "Success";
        response.data = data;
        response.header = header;
        return response;
    }

    public static GeneralResponse failure(String exceptionMsg) {
        GeneralResponse response = new GeneralResponse();
        response.responseCode = "9999";
        response.responseMessage = "Failure";
        response.exceptionMsg = exceptionMsg;
        return response;
    }
    
    public static GeneralResponse unauthenticated() {
        GeneralResponse response = new GeneralResponse();
        response.responseCode = "9999";
        response.responseMessage = "Failure";
        response.exceptionMsg = "Un Authenticated";
        return response;
    }
}
