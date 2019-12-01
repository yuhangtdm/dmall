package com.dmall.mms.service.impl.memberloginlog;

import com.dmall.mms.api.dto.memberloginlog.request.SaveMemberLoginLogRequestDTO;
import com.dmall.mms.api.dto.memberloginlog.request.UpdateMemberLoginLogRequestDTO;
import com.dmall.mms.api.dto.memberloginlog.request.ListMemberLoginLogRequestDTO;
import com.dmall.mms.api.dto.memberloginlog.request.PageMemberLoginLogRequestDTO;
import com.dmall.mms.api.dto.memberloginlog.common.CommonMemberLoginLogResponseDTO;
import com.dmall.mms.api.service.MemberLoginLogService;
import com.dmall.mms.service.impl.memberloginlog.handler.*;
import com.dmall.common.model.result.BaseResult;
import com.dmall.common.model.result.LayuiPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 会员登录记录服务实现
 * @author: created by hang.yu on 2019-12-01 22:56:08
 */
@RestController
public class  MemberLoginLogServiceImpl implements MemberLoginLogService {

    @Autowired
    protected SaveMemberLoginLogHandler saveMemberLoginLogHandler;

    @Autowired
    private DeleteMemberLoginLogHandler deleteMemberLoginLogHandler;

    @Autowired
    private UpdateMemberLoginLogHandler updateMemberLoginLogHandler;

    @Autowired
    private GetMemberLoginLogHandler getMemberLoginLogHandler;

    @Autowired
    private ListMemberLoginLogHandler listMemberLoginLogHandler;

    @Autowired
    private PageMemberLoginLogHandler pageMemberLoginLogHandler;


    @Override
    public BaseResult<Long> save(@RequestBody SaveMemberLoginLogRequestDTO requestDTO) {
        return saveMemberLoginLogHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> delete(@PathVariable("id") Long id) {
        return deleteMemberLoginLogHandler.handler(id);
    }

    @Override
    public BaseResult<Long> update(@Valid UpdateMemberLoginLogRequestDTO requestDTO) {
        return updateMemberLoginLogHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<CommonMemberLoginLogResponseDTO> get(Long id) {
        return getMemberLoginLogHandler.handler(id);
    }

    @Override
    public BaseResult<List<CommonMemberLoginLogResponseDTO>> list(@RequestBody ListMemberLoginLogRequestDTO requestDTO) {
        return listMemberLoginLogHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<LayuiPage<CommonMemberLoginLogResponseDTO>> page(@RequestBody PageMemberLoginLogRequestDTO requestDTO) {
        return pageMemberLoginLogHandler.handler(requestDTO);
    }

}
