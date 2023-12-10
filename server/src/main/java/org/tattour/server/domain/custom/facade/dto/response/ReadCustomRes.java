package org.tattour.server.domain.custom.facade.dto.response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.custom.model.Custom;
import org.tattour.server.domain.custom.model.CustomImage;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadCustomRes {

    private Integer id;
    private Integer userId;
    private Integer stickerId;
    private List<String> themes;
    private List<String> styles;
    private String mainImageUrl;
    private String handDrawingImageUrl;
    private List<String> images;
    private Boolean haveDesign;
    private String size;
    private String name;
    private String description;
    private String demand;
    private Integer count;
    private Boolean isColored;
    private Boolean isPublic;
    private Boolean isCompleted;
    private Integer price;
    private String process;
    private Integer viewCount;

    public static ReadCustomRes from(Custom custom) {
        List<String> themes = getThemeNameList(custom);
        List<String> styles = getStyleList(custom);
        List<String> images = getImageList(custom);
        return ReadCustomRes.builder()
                .id(custom.getId())
                .userId(custom.getUser().getId())
                .themes(themes)
                .styles(styles)
                .mainImageUrl(custom.getMainImageUrl())
                .images(images)
                .handDrawingImageUrl(custom.getHandDrawingImageUrl())
                .haveDesign(custom.getHaveDesign())
                .size(getSizeString(custom))
                .name(custom.getName())
                .description(custom.getDescription())
                .demand(custom.getDemand())
                .count(custom.getCount())
                .isColored(custom.getIsColored())
                .isPublic(custom.getIsPublic())
                .isCompleted(custom.getIsCompleted())
                .price(custom.getPrice())
                .process(getProcessString(custom))
                .viewCount(custom.getViewCount())
                .build();
    }

    private static List<String> getImageList(Custom custom) {
        return custom
                .getImages()
                .stream()
                .map(CustomImage::getImageUrl)
                .collect(Collectors.toList());
    }

    private static List<String> getStyleList(Custom custom) {
        return custom
                .getCustomStyles()
                .stream()
                .map(customStyle -> customStyle.getStyle().getName())
                .collect(Collectors.toList());
    }

    private static List<String> getThemeNameList(Custom custom) {
        return custom
                .getCustomThemes()
                .stream()
                .map(customTheme -> customTheme.getTheme().getName())
                .collect(Collectors.toList());
    }

    private static String getSizeString(Custom custom) {
        if (Objects.isNull(custom.getSize())) {
            return null;
        }
        return custom.getSize().getSize();
    }

    private static String getProcessString(Custom custom) {
        if (Objects.isNull(custom.getProcess())) {
            return null;
        }
        return custom.getProcess().getValue();
    }
}