package org.tattour.server.domain.custom.service;

import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.model.Custom;
import org.tattour.server.domain.custom.model.CustomProcess;
import org.tattour.server.domain.user.model.User;

public interface CustomService {

    Custom save(Custom custom);

    Custom createInitCustom(User user, Boolean haveDesign);

    Custom updateCustom(Custom custom, Custom updateCustomInfo);

    Custom updateCustomProcess(Custom updateCustom, CustomProcess customProcess);

    void setHandDrawingImage(Custom updateCustom, MultipartFile handDrawingImage);

    void setMainImageUrl(Custom updateCustom, MultipartFile mainImage);
}
