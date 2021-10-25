package com.sessionmanager.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorVO {

    private int code;

    private String errorMessage;

    private List<String> errorList;
}
