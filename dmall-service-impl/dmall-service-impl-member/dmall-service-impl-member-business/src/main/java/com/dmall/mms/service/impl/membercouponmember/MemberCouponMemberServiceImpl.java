package com.dmall.mms.service.impl.membercouponmember;

import com.dmall.mms.api.dto.membercouponmember.request.SaveMemberCouponMemberRequestDTO;
import com.dmall.mms.api.dto.membercouponmember.request.UpdateMemberCouponMemberRequestDTO;
import com.dmall.mms.api.dto.membercouponmember.request.ListMemberCouponMemberRequestDTO;
import com.dmall.mms.api.dto.membercouponmember.request.PageMemberCouponMemberRequestDTO;
import com.dmall.mms.api.dto.membercouponmember.common.CommonMemberCouponMemberResponseDTO;
import com.dmall.mms.api.service.MemberCouponMemberService;
import com.dmall.mms.service.impl.membercouponmember.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员-优惠券服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberCouponMemberServiceImpl implements MemberCouponMemberService {

    @Autowired
    protected SaveMemberCouponMemberHandler saveMemberCouponMemberHandler;

    @Autowired
    private DeleteMemberCouponMemberHandler deleteMemberCouponMemberHandler;

    @Autowired
    private UpdateMemberCouponMemberHandler updateMemberCouponMemberHandler;

    @Autowired
    private GetMemberCouponMemberHandler getMemberCouponMemberHandler;

    @Autowired
    private ListMemberCouponMemberHandler listMemberCouponMemberHandler;

    @Autowired
    private PageMemberCouponMemberHandler pageMemberCouponMemberHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberCouponMemberRequestDTO requestDTO) {
        return saveMemberCouponMemberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberCouponMemberHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberCouponMemberRequestDTO requestDTO) {
        return updateMemberCouponMemberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberCouponMemberResponseDTO> get(Long id) {
        return getMemberCouponMemberHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberCouponMemberResponseDTO>> list(@RequestBody ListMemberCouponMemberRequestDTO requestDTO) {
        return listMemberCouponMemberHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberCouponMemberResponseDTO>> page(@RequestBody PageMemberCouponMemberRequestDTO requestDTO) {
        return pageMemberCouponMemberHandler.handler(requestDTO);
    }

}
