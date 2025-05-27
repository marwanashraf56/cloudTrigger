package com.cloud.cloudtrigger;

import lombok.Data;

@Data
public class JobRequest {
    private String jarUrl;
    private String excelUrl;
    private String resultUploadUrl;
}
