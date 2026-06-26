package com.unauth.util;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request<T> implements Serializable{

	private static final long serialVersionUID = 6541910013577175536L;
	
	private Header requestHeader;
	
	private T requestBody;
}
