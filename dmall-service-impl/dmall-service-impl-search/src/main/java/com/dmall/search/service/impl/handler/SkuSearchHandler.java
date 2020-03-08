package com.dmall.search.service.impl.handler;

import cn.hutool.core.util.StrUtil;
import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.util.BeanUtil;
import com.dmall.common.util.ResultUtil;
import com.dmall.component.elasticsearch.ESDao;
import com.dmall.component.elasticsearch.entity.*;
import com.dmall.component.web.handler.AbstractCommonHandler;
import com.dmall.search.api.dto.*;
import com.dmall.search.service.impl.EsConstants;
import com.dmall.search.service.impl.es.SkuEsDTO;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 搜索sku处理器
 * @author: created by hang.yu on 2020/3/7 21:07
 */
@Slf4j
@Component
public class SkuSearchHandler extends AbstractCommonHandler<SearchRequestDTO, SkuEsDTO, SearchResponseDTO> {

    @Autowired
    private ESDao esDao;

    @Override
    public BaseResult<SearchResponseDTO> processor(SearchRequestDTO requestDTO) {
        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ResponsePage<SkuEsDTO> page = esDao.search(getSkuEs(requestDTO));
        // 品牌列表
        responseDTO.setBrandList(getBrandList(page.getData()));
        // 属性列表
        if (requestDTO.getCat() != null) {
            responseDTO.setAttributeList(getAttributeList(page.getData()));
        }
        // 分页数据
        responseDTO.setPage(getPage(page, requestDTO.getFrom(), requestDTO.getSize()));
        return ResultUtil.success(responseDTO);
    }

    /**
     * 获取es的搜索实体
     */
    private ESSearch<SkuEsDTO> getSkuEs(SearchRequestDTO requestDTO) {
        ESSearch<SkuEsDTO> esSearch = new ESSearch<SkuEsDTO>();
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
     * 获取全部去重的品牌列表
     */
    private List<BrandDTO> getBrandList(List<SkuEsDTO> data) {
        List<BrandDTO> collect = data.stream().map(SkuEsDTO::getBrandDTO).collect(Collectors.toList());
        return collect.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                new TreeSet<>(Comparator.comparing(BrandDTO::getBrandId))), ArrayList::new));

    }

    /**
     * 获取符合条件的属性列表
     */
    private List<AttributeDTO> getAttributeList(List<SkuEsDTO> data) {
        List<AttributeDTO> result = Lists.newArrayList();
        for (SkuEsDTO esDTO : data) {
            List<AttributeDTO> attributes = esDTO.getAttributes();
            for (AttributeDTO attribute : attributes) {
                if (!result.contains(attribute)) {
                    result.add(attribute);
                } else {
                    for (AttributeValueDTO attributeValue : attribute.getAttributeValues()) {
                        if (!attribute.getAttributeValues().contains(attributeValue)) {
                            attribute.getAttributeValues().add(attributeValue);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 获取分页实体
     */
    private ResponsePage<SkuEsResponseDTO> getPage(ResponsePage<SkuEsDTO> page, Integer from, Integer size) {
        int start = from * size;
        int end = start + size;
        // 分页数据
        if (end > page.getCount()) {
            end = page.getCount().intValue();
        }
        page.setData(page.getData().subList(start, end));
        List<SkuEsResponseDTO> collect = page.getData().stream()
                .map(skuEsDTO -> BeanUtil.copyProperties(skuEsDTO, SkuEsResponseDTO.class))
                .collect(Collectors.toList());
        return new ResponsePage<>(page.getCount(), collect);
    }

}
