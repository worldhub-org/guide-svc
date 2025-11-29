package com.worldhub.guide.web.dto.tags;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TagsCollectionResponse {

    List<TagResponse> tags;
}
