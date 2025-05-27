package com.cloud.cloudtrigger;

import lombok.Data;

@Data
public class JobRequest {
    private String zipUrl;
    private String jarNameToRun;
}
