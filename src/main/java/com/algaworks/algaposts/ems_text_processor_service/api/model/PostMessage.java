package com.algaworks.algaposts.ems_text_processor_service.api.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostMessage {

    private String id;
    private String body;

}
