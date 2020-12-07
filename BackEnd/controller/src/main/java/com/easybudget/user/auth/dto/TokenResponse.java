package com.easybudget.user.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class TokenResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private final String token;

}