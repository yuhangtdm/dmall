package com.dmall.oms.service.impl.order.handler.seller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmall.common.constants.RoleNameConstants;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.model.admin.AdminUserContextHolder;
import com.dmall.common.model.admin.AdminUserDTO;
import com.dmall.common.util.EnumUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.rbac.shiro.feign.AdminPermissionFeign;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageRequestDTO;
import com.dmall.oms.api.dto.deliverpage.DeliverOrderPageResponseDTO;
import com.dmall.oms.api.enums.SubOrderStatusEnum;
import com.dmall.oms.service.impl.order.mapper.DeliverOrderPageMapper;
import com.dmall.oms.service.impl.order.mapper.dto.DeliverOrderPageDbDTO;
import com.dmall.sso.api.dto.admin.RoleResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 发货分页处理器
 * @author: created by hang.yu on 2020/4/5 11:29
 */
@Component
public class DeliverOrderPageHandler
    extends AbstractCommonHandler<DeliverOrderPageRequestDTO, DeliverOrderPageDbDTO, DeliverOrderPageResponseDTO> {

    @Autowired
    private AdminPermissionFeign roleFeign;

    @Autowired
    private DeliverOrderPageMapper deliverOrderPageMapper;

    @Override
    public BaseResult<ResponsePage<DeliverOrderPageResponseDTO>> processor(DeliverOrderPageRequestDTO requestDTO) {
        // 获取当前登录人的角色
        AdminUserDTO adminUser = AdminUserContextHolder.get();
        BaseResult<List<RoleResponseDTO>> listBaseResult = roleFeign.listRoles(adminUser.getPhone());
        if (listBaseResult.getResult()) {
            return ResultUtil.fail(listBaseResult.getCode(), listBaseResult.getMsg());
        }
        List<RoleResponseDTO> data = listBaseResult.getData();
        for (RoleResponseDTO role : data) {
            // 发货员角色默认看自己的发货列表
            if (RoleNameConstants.DELIVER.equals(role.getName())) {
                requestDTO.setDeliverId(adminUser.getId());
            }
        }
        Page<DeliverOrderPageDbDTO> page = new Page(requestDTO.getCurrent(), requestDTO.getSize());
        List<DeliverOrderPageDbDTO> deliverOrderPageDbDTOS = deliverOrderPageMapper.deliverOrderPage(page, requestDTO);
        List<DeliverOrderPageResponseDTO> collect = deliverOrderPageDbDTOS.stream()
            .map(deliverOrderPageDbDTO -> doConvertDto(deliverOrderPageDbDTO, DeliverOrderPageResponseDTO.class))
            .collect(Collectors.toList());

        return ResultUtil.success(new ResponsePage(page.getTotal(), collect));
    }

    @Override
    protected void customerConvertDto(DeliverOrderPageResponseDTO result, DeliverOrderPageDbDTO doo) {
        result.setSubOrderStatus(EnumUtil.getCodeDescEnum(SubOrderStatusEnum.class, doo.getDeliverStatus()));
    }
}
