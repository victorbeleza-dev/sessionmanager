package com.sessionmanager.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AbleToVote implements Serializable {

    private String status;
}
