package com.dmall.pms.service.impl.support;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.api.dto.sku.request.save.MediaRequestDTO;
import com.dmall.pms.api.dto.sku.response.MediaResponseDTO;
import com.dmall.pms.generator.dataobject.SkuMediaDO;
import com.dmall.pms.generator.service.ISkuMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: SkuMediaSupport
 * @author: created by hang.yu on 2019/12/29 15:42
 */
@Component
public class SkuMediaSupport {

    @Autowired
    private ISkuMediaService iSkuMediaService;

    /**
     * 新增或删除skuMedia
     */
    public void saveOrDeleteSkuMedia(Long productId, Long skuId, List<MediaRequestDTO> mediaList) {
        List<SkuMediaDO> mediaDoList = iSkuMediaService.list(Wrappers.<SkuMediaDO>lambdaQuery()
                .eq(SkuMediaDO::getSkuId, skuId));
        if (CollUtil.isEmpty(mediaDoList)) {
            List<SkuMediaDO> skuMediaList = mediaList.stream()
                    .map(mediaRequestDTO -> buildSkuMediaDO(productId, skuId, mediaRequestDTO))
                    .collect(Collectors.toList());
            iSkuMediaService.saveBatch(skuMediaList);
        } else {
            List<Long> oldMediaIds = mediaDoList.stream().map(SkuMediaDO::getId)
                    .collect(Collectors.toList());
            List<Long> newMediaIds = mediaList.stream().map(MediaRequestDTO::getSkuMediaId).collect(Collectors.toList());
            // 删除的id集合
            List<Long> deleteIdList = oldMediaIds.stream()
                    .filter(mediaId -> !newMediaIds.contains(mediaId))
                    .collect(Collectors.toList());
            // 新增的
            List<SkuMediaDO> skuMediaList = mediaList.stream()
                    .filter(mediaDTO -> mediaDTO.getSkuMediaId() == null)
                    .map(mediaDTO -> buildSkuMediaDO(productId, skuId, mediaDTO))
                    .collect(Collectors.toList());
            iSkuMediaService.saveBatch(skuMediaList);
            if (CollUtil.isNotEmpty(deleteIdList)) {
                iSkuMediaService.remove(Wrappers.<SkuMediaDO>lambdaQuery().in(SkuMediaDO::getId, deleteIdList));
            }
        }
    }

    /**
     * 获取skuMedia列表
     */
    public List<MediaResponseDTO> listBySkuId(Long skuId) {
        List<SkuMediaDO> list = iSkuMediaService.list(Wrappers.<SkuMediaDO>lambdaQuery().eq(SkuMediaDO::getSkuId, skuId));
        return list.stream().map(skuMediaDO -> {
            MediaResponseDTO mediaResponseDTO = new MediaResponseDTO();
            mediaResponseDTO.setSkuMediaId(skuMediaDO.getId());
            mediaResponseDTO.setKey(skuMediaDO.getMediaKey());
            mediaResponseDTO.setHash(skuMediaDO.getMediaHash());
            mediaResponseDTO.setUrl(skuMediaDO.getMediaUrl());
            mediaResponseDTO.setMediaType(skuMediaDO.getMediaType());
            mediaResponseDTO.setSort(skuMediaDO.getSort());
            return mediaResponseDTO;
        }).collect(Collectors.toList());
    }

    /**
     * 根据skuId删除
     */
    public void deleteBySkuId(Long skuId) {
        iSkuMediaService.remove(Wrappers.<SkuMediaDO>lambdaQuery().eq(SkuMediaDO::getSkuId, skuId));
    }

    /**
     * 根据productId删除
     */
    public void deleteByProductId(Long productId) {
        iSkuMediaService.remove(Wrappers.<SkuMediaDO>lambdaQuery().eq(SkuMediaDO::getProductId, productId));

    }

    /**
     * 构建SkuMediaDO
     */
    private SkuMediaDO buildSkuMediaDO(Long productId, Long skuId, MediaRequestDTO mediaDTO) {
        SkuMediaDO skuMediaDO = new SkuMediaDO();
        skuMediaDO.setProductId(productId);
        skuMediaDO.setSkuId(skuId);
        skuMediaDO.setMediaType(mediaDTO.getMediaType());
        skuMediaDO.setMediaKey(mediaDTO.getKey());
        skuMediaDO.setMediaHash(mediaDTO.getHash());
        skuMediaDO.setSort(mediaDTO.getSort());
        return skuMediaDO;
    }

}
