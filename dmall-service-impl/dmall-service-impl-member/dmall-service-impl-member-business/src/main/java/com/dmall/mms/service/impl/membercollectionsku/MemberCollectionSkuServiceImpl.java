package com.dmall.mms.service.impl.membercollectionsku;

import com.dmall.mms.api.dto.membercollectionsku.request.SaveMemberCollectionSkuRequestDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.UpdateMemberCollectionSkuRequestDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.ListMemberCollectionSkuRequestDTO;
import com.dmall.mms.api.dto.membercollectionsku.request.PageMemberCollectionSkuRequestDTO;
import com.dmall.mms.api.dto.membercollectionsku.common.CommonMemberCollectionSkuResponseDTO;
import com.dmall.mms.api.service.MemberCollectionSkuService;
import com.dmall.mms.service.impl.membercollectionsku.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员收藏sku服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberCollectionSkuServiceImpl implements MemberCollectionSkuService {

    @Autowired
    protected SaveMemberCollectionSkuHandler saveMemberCollectionSkuHandler;

    @Autowired
    private DeleteMemberCollectionSkuHandler deleteMemberCollectionSkuHandler;

    @Autowired
    private UpdateMemberCollectionSkuHandler updateMemberCollectionSkuHandler;

    @Autowired
    private GetMemberCollectionSkuHandler getMemberCollectionSkuHandler;

    @Autowired
    private ListMemberCollectionSkuHandler listMemberCollectionSkuHandler;

    @Autowired
    private PageMemberCollectionSkuHandler pageMemberCollectionSkuHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberCollectionSkuRequestDTO requestDTO) {
        return saveMemberCollectionSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberCollectionSkuHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberCollectionSkuRequestDTO requestDTO) {
        return updateMemberCollectionSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberCollectionSkuResponseDTO> get(Long id) {
        return getMemberCollectionSkuHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberCollectionSkuResponseDTO>> list(@RequestBody ListMemberCollectionSkuRequestDTO requestDTO) {
        return listMemberCollectionSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberCollectionSkuResponseDTO>> page(@RequestBody PageMemberCollectionSkuRequestDTO requestDTO) {
        return pageMemberCollectionSkuHandler.handler(requestDTO);
    }

}
