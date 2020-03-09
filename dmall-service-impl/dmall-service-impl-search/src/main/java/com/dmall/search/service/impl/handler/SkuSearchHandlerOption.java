package com.dmall.search.service.impl.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.elasticsearch.ESDao;
import com.dmall.component.elasticsearch.entity.*;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.dataobject.ProductAttributeValueDO;
import com.dmall.pms.generator.mapper.AttributeMapper;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.generator.mapper.CategoryAttributeMapper;
import com.dmall.pms.generator.mapper.ProductAttributeValueMapper;
import com.dmall.search.api.dto.*;
import com.dmall.search.service.impl.es.EsConstants;
import com.dmall.search.service.impl.es.SkuEsDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 搜索sku处理器 第一个方案 已过时
 * @author: created by hang.yu on 2020/3/7 21:07
 */
@Slf4j
//@Component
@Deprecated
public class SkuSearchHandlerOption extends AbstractCommonHandler<SearchRequestDTO, SkuEsDTO, SearchResponseDTO> {

    @Autowired
    private ESDao esDao;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;


    @Override
    public BaseResult<SearchResponseDTO> processor(SearchRequestDTO requestDTO) {
        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ResponsePage<SkuEsDTO> page = esDao.search(getSkuEs(requestDTO));
        // 品牌列表
        responseDTO.setBrandList(getBrandList(page.getData()));
        // 属性列表
        responseDTO.setAttributeList(getAttributeList(page.getData(), requestDTO.getCat(), requestDTO.getEv()));
        int start = requestDTO.getFrom() * requestDTO.getSize();
        int end = start + requestDTO.getSize();
        // 分页数据
        if (end > page.getCount()) {
            end = page.getCount().intValue();
        }
        page.setData(page.getData().subList(start, end));
        List<SkuEsResponseDTO> collect = page.getData().stream()
                .map(skuEsDTO -> BeanUtil.copyProperties(skuEsDTO, SkuEsResponseDTO.class))
                .collect(Collectors.toList());
        ResponsePage<SkuEsResponseDTO> resultPage = new ResponsePage<>(page.getCount(), collect);
        responseDTO.setPage(resultPage);
        return ResultUtil.success(responseDTO);
    }

    /**
     * 获取es的搜索实体
     */
    private ESSearch<SkuEsDTO> getSkuEs(SearchRequestDTO requestDTO) {
        ESSearch<SkuEsDTO> esSearch = new ESSearch<>();
        esSearch.setIndexName(EsConstants.INDEX_NAME);
        esSearch.setTypeName(EsConstants.TYPE_NAME);
        // 搜索
        if (StrUtil.isNotBlank(requestDTO.getKeyword())) {
            List<SearchField> searchFields = Arrays.stream(EsConstants.SEARCH_FIELDS).map(key ->
                    new SearchField(key, requestDTO.getKeyword())).collect(Collectors.toList());
            esSearch.setSearchFields(searchFields);
        }
        // 过滤
        List<FilterField> filterFields = Lists.newArrayList();
        // 分类
        if (requestDTO.getCat() != null) {
            FilterField filterField = new FilterField(EsConstants.FILTER_CATEGORY, new Object[]{requestDTO.getCat()});
            filterFields.add(filterField);
        }
        if (StrUtil.isNotBlank(requestDTO.getEv())) {
            // 属性值
            String[] attrs = requestDTO.getEv().split(EsConstants.PERCENT_SIGN);
            List<Object> list = Lists.newArrayList();
            for (String attr : attrs) {
                String[] attrValues = attr.split(StrUtil.UNDERLINE);
                for (String attrValue : attrValues) {
                    String[] values = attrValue.split(StrUtil.COMMA);
                    list.addAll(Arrays.asList(values));
                }
            }
            // 将属性值放入一个对象数组里
            FilterField filterField = new FilterField(EsConstants.FILTER_ATTR_VALUE, list.toArray());
            filterFields.add(filterField);
        }

        // 品牌
        if (StrUtil.isNotBlank(requestDTO.getBra())) {
            String[] brandIds = requestDTO.getBra().split(StrUtil.COMMA);
            FilterField filterField = new FilterField(EsConstants.FILTER_BRAND, brandIds);
            filterFields.add(filterField);
        }
        esSearch.setFilterFields(filterFields);

        // 对sku名称进行高亮
        esSearch.setHighLightField(EsConstants.SEARCH_FIELDS[0]);
        // 设置分页 查询所有数据进行后期的聚合
        // esSearch.setEsPage(new ESPage(requestDTO.getFrom(), requestDTO.getSize()));
        esSearch.setEsPage(null);
        // 设置排序
        if (StrUtil.isNotBlank(requestDTO.getSort())) {
            String[] sorts = requestDTO.getSort().split(StrUtil.UNDERLINE);
            esSearch.setSortField(new SortField(sorts[0], SortOrder.fromString(sorts[1])));
        }
        // 设置范围
        if (requestDTO.getPriceStart() != null || requestDTO.getPriceEnd() != null) {
            RangeField rangeField = new RangeField();
            rangeField.setFieldName(EsConstants.RANGE_PRICE);
            if (requestDTO.getPriceStart() != null) {
                rangeField.setStartValue(requestDTO.getPriceStart());
            }
            if (requestDTO.getPriceEnd() != null) {
                rangeField.setEndValue(requestDTO.getPriceEnd());
            }
        }

        esSearch.setClazz(SkuEsDTO.class);
        return esSearch;
    }

    /**
     * 构建品牌列表
     */
    private List<BrandDTO> getBrandList(List<SkuEsDTO> data) {

//        Set<Long> brandIds = data.stream().map(SkuEsDTO::getBrandId).collect(Collectors.toSet());
        Set<Long> brandIds = Sets.newHashSet();
        return brandMapper.selectBatchIds(brandIds).stream().map(brandDO -> {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setBrandId(brandDO.getId());
            brandDTO.setBrandName(brandDO.getName());
            brandDTO.setBrandEnglishName(brandDO.getEnglishName());
            brandDTO.setBrandLogo(brandDO.getLogo());
            return brandDTO;
        }).collect(Collectors.toList());
    }

    /**
     * 构建属性列表 查库较多 性能差
     * ev : 属性id_属性值id,属性值id%属性id_属性值id
     */
    @Deprecated
    private List<AttributeDTO> getAttributeList(List<SkuEsDTO> data, Long categoryId, String ev) {
        List<AttributeDTO> result = Lists.newArrayList();
        List<Long> selectAttrs = Lists.newArrayList();
        if (StrUtil.isNotBlank(ev)) {
            String[] attrs = ev.split(EsConstants.PERCENT_SIGN);
            for (String attr : attrs) {
                String[] attrValues = attr.split(StrUtil.UNDERLINE);
                selectAttrs.add(Long.valueOf(attrValues[0]));
            }
        }

        // 得出商品属性值id的去重列表
       /* Set<Long> productValueIds = data.stream().flatMap(skuEsDTO -> skuEsDTO.getAttributeValues().stream())
                .collect(Collectors.toSet());*/
        Set<Long> productValueIds = Sets.newHashSet();
        // 去数据库搜索
        List<ProductAttributeValueDO> productAttributeValues = productAttributeValueMapper.selectBatchIds(productValueIds);
        // 按照属性分组
        Map<Long, List<ProductAttributeValueDO>> attributeMap =
                productAttributeValues.stream().collect(Collectors.groupingBy(ProductAttributeValueDO::getAttributeId));

        for (Map.Entry<Long, List<ProductAttributeValueDO>> entry : attributeMap.entrySet()) {
            Long k = entry.getKey();
            List<ProductAttributeValueDO> v = entry.getValue();
            // 去数据库查询是否可选
            if (categoryId != null) {
                Integer canScreen = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                        .eq(CategoryAttributeDO::getCategoryId, categoryId)
                        .eq(CategoryAttributeDO::getAttributeId, k)).getCanScreen();
                // 可搜索
                if (!canScreen.equals(1)) {
                    // 单选 过滤已经选中的了属性
                    if (canScreen.equals(2)
                            && !selectAttrs.contains(k)) {
                        continue;
                    }
                    AttributeDTO attributeDTO = new AttributeDTO();
                    attributeDTO.setAttributeId(k);
                    // 调用feign接口查询属性名称
//                attributeDTO.setAttributeName(attributeServiceFeign.get(k).getData().getShowName());
                    attributeDTO.setAttributeName(attributeMapper.selectById(k).getShowName());
                    attributeDTO.setCanScreen(canScreen);
                    List<AttributeValueDTO> attributeValues = Lists.newArrayList();
                    for (ProductAttributeValueDO productAttributeValueDO : v) {
                        AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
                        attributeValueDTO.setAttributeValueId(productAttributeValueDO.getId());
                        attributeValueDTO.setAttributeValueName(productAttributeValueDO.getAttributeValue());
                        attributeValues.add(attributeValueDTO);

                    }
                    attributeDTO.setAttributeValues(attributeValues);
                    result.add(attributeDTO);
                }
            }
        }

        return result;
    }

}
