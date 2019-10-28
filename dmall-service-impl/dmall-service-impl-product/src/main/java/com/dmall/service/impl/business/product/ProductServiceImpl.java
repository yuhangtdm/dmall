package com.dmall.service.impl.business.product;
import java.math.BigDecimal;

import com.dmall.service.api.member.dto.request.MemberRequestDTO;
import com.dmall.service.api.member.dto.response.MemberResponseDTO;
import com.dmall.service.api.product.ProductService;
import com.dmall.service.api.product.dto.request.ProductRequestDTO;
import com.dmall.service.api.product.dto.response.ProductResponseDTO;
import com.dmall.service.impl.feign.member.MemberFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商品服务实现
 * @author: created by yuhang on 2019/10/14 22:10
 */
@RestController
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MemberFeignService memberFeignService;

//    @Value("${product.name}")
    private String productName = "小米9";

    @Override
    public ProductResponseDTO getProduct(@RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setProductNo(requestDTO.getProductNo());
        responseDTO.setName(productName);
        responseDTO.setPrice(BigDecimal.ONE);

        MemberRequestDTO memberRequestDTO = new MemberRequestDTO();
        memberRequestDTO.setId(1L);
        memberRequestDTO.setName(memberRequestDTO.getName());
        MemberResponseDTO memberResponseDTO = memberFeignService.getMember(memberRequestDTO);
        responseDTO.setMemberResponseDTO(memberResponseDTO);
        return responseDTO;
    }
}
