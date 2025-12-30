package com.worldhub.guide.web.dto.guide;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CollectionResponse <T>{

    private List<T> collection;
}
