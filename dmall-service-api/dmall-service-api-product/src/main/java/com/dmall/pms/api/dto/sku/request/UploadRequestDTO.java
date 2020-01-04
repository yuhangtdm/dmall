package com.dmall.pms.api.dto.sku.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 12:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadRequestDTO {

    private Long id;

    private MultipartFile[] files;
}
