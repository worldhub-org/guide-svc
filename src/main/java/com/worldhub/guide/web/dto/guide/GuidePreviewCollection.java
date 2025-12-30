package com.worldhub.guide.web.dto.guide;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GuidePreviewCollection {

    private List<GuidePreviewResponse> previews;
}
