package com.dmall.pms.service.impl.category.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.dtree.DTreeResponseDTO;
import com.dmall.common.dto.dtree.TreeDisabledTypeEnum;
import com.dmall.common.model.DTreeUtil;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.api.enums.LevelEnum;
import com.dmall.pms.api.enums.PmsErrorEnum;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.CategoryMapper;
import com.dmall.pms.api.dto.category.request.CategoryTreeDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: zTree树处理器
 * @author: created by hang.yu on 2019/11/24 18:36
 */
@Component
public class CategoryTreeHandler extends AbstractCommonHandler<CategoryTreeDTO, CategoryDO, DTreeResponseDTO> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public BaseResult<List<DTreeResponseDTO>> processor(CategoryTreeDTO requestDTO) {
        Long parentId = requestDTO.getParentId();
        CategoryDO categoryDO = categoryMapper.selectById(parentId);
        // 一级分类或父id必须存在
        if (parentId != 0 && categoryDO == null) {
            return ResultUtil.fail(PmsErrorEnum.PARENT_CATEGORY_NOT_EXIST);
        }
        // 获取树的源数据
        List<CategoryDO> categoryDOList;
        if (parentId == 0L) {
            categoryDOList = categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery().orderByAsc(CategoryDO::getSort));
        } else {
            categoryDOList = categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery()
                    .like(CategoryDO::getPath, categoryDO.getPath())
                    .orderByAsc(CategoryDO::getSort));
        }

        List<DTreeResponseDTO> treeList = categoryDOList.stream()
                .map(doo -> buildTreeResponse(doo, requestDTO.getType()))
                .collect(Collectors.toList());

        return ResultUtil.success(DTreeUtil.build(treeList, parentId));
    }

    /**
     * 构建DTree树
     */
    private DTreeResponseDTO buildTreeResponse(CategoryDO doo, Integer type) {
        DTreeResponseDTO responseDTO = new DTreeResponseDTO();
        responseDTO.setId(doo.getId());
        responseDTO.setParentId(doo.getParentId());
        responseDTO.setTitle(doo.getName());
        // 默认不展开
        responseDTO.setSpread(false);
        responseDTO.setParent(LevelEnum.ONE.getCode().equals(doo.getLevel())
                || LevelEnum.TWO.getCode().equals(doo.getLevel()));
        TreeDisabledTypeEnum typeEnum = EnumUtil.getCodeDescEnum(TreeDisabledTypeEnum.class, type);
        if (typeEnum != null) {
            switch (typeEnum) {
                case ZERO: {
                    responseDTO.setDisabled(false);
                    break;
                }
                case ONE: {
                    responseDTO.setDisabled(LevelEnum.ONE.getCode().equals(doo.getLevel()));
                    break;
                }
                case TWO: {
                    responseDTO.setDisabled(LevelEnum.TWO.getCode().equals(doo.getLevel()));
                    break;
                }
                case THREE: {
                    responseDTO.setDisabled(LevelEnum.THREE.getCode().equals(doo.getLevel()));
                    break;
                }
                case ONE_TWO: {
                    responseDTO.setDisabled(LevelEnum.ONE.getCode().equals(doo.getLevel())
                            || LevelEnum.TWO.getCode().equals(doo.getLevel()));
                    break;
                }
                case ONE_THREE: {
                    responseDTO.setDisabled(LevelEnum.ONE.getCode().equals(doo.getLevel())
                            || LevelEnum.THREE.getCode().equals(doo.getLevel()));
                    break;
                }
                case TWO_THREE: {
                    responseDTO.setDisabled(LevelEnum.TWO.getCode().equals(doo.getLevel())
                            || LevelEnum.THREE.getCode().equals(doo.getLevel()));
                    break;
                }
                case ONE_TWO_THREE: {
                    responseDTO.setDisabled(true);
                    break;
                }
                default: {
                    responseDTO.setDisabled(false);
                }
            }
        } else {
            responseDTO.setDisabled(false);
        }
        Map<String, Object> map = Maps.newHashMap();
        // 排序和描述
        map.put("sort", doo.getSort());
        map.put("description", doo.getDescription());
        responseDTO.setBasicData(map);
        responseDTO.setChildren(Lists.newArrayList());
        return responseDTO;
    }

}
