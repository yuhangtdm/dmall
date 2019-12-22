package com.dmall.pms;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.util.NoUtil;
import com.dmall.component.file.qiniu.QiNiuFileManager;
import com.dmall.component.web.util.HttpClientUtil;
import com.dmall.pms.generator.dataobject.*;
import com.dmall.pms.generator.mapper.*;
import com.dmall.pms.generator.service.ICategoryService;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 18:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class TestSpike {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Autowired
    private HttpClientUtil httpClientUtil;

    @Autowired
    private AttributeMapper attributeMapper;

    @Autowired
    private CategoryAttributeMapper categoryAttributeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private SkuMediaMapper skuMediaMapper;

    @Autowired
    private SkuExtMapper skuExtMapper;

    @Autowired
    private ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    private SkuAttributeValueMapper skuAttributeValueMapper;

    @Autowired
    private QiNiuFileManager qiNiuFileManager;

    /**
     * 商品分类
     */
    @Test
    public void testCategory() throws IOException {
        String html = "D:\\jd.html";
        Document document = Jsoup.parse(new File(html), "utf-8");
        Element j_cate = document.getElementById("J_cate");
        Elements li = j_cate.getElementsByTag("li");
        int k = 1;
        for (Element oneCategory : li) {
            CategoryDO oneCategoryDO = new CategoryDO();
            oneCategoryDO.setParentId(0L);
            oneCategoryDO.setName(oneCategory.text());
            oneCategoryDO.setLevel(1);
            oneCategoryDO.setSort(k);
            oneCategoryDO.setHotStatus("Y");
            oneCategoryDO.setNavStatus("Y");
            oneCategoryDO.setCreator(1L);
            oneCategoryDO.setModifier(1L);
            categoryMapper.insert(oneCategoryDO);
            oneCategoryDO.setPath("." + oneCategoryDO.getId() + ".");
            categoryMapper.updateById(oneCategoryDO);
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element twoCategory : twoLevels) {
                CategoryDO twoCategoryDO = new CategoryDO();
                twoCategoryDO.setParentId(oneCategoryDO.getId());
                twoCategoryDO.setName(twoCategory.text());
                twoCategoryDO.setLevel(2);
                twoCategoryDO.setSort(i);
                twoCategoryDO.setHotStatus("Y");
                twoCategoryDO.setNavStatus("Y");
                twoCategoryDO.setCreator(1L);
                twoCategoryDO.setModifier(1L);
                categoryMapper.insert(twoCategoryDO);
                twoCategoryDO.setPath(oneCategoryDO.getPath() + twoCategoryDO.getId() + ".");
                categoryMapper.updateById(twoCategoryDO);
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                List<CategoryDO> categoryDOList = Lists.newArrayList();
                for (Element threeCategory : threeLevel) {

                    Elements a = threeCategory.getElementsByTag("a");
                    int j = 1;
                    for (Element element : a) {
                        String caName = oneCategory.text().trim() + "_" + twoCategory.text().trim() + "_" + element.text().trim();
                        CategoryDO threeCategoryDO = new CategoryDO();
                        threeCategoryDO.setParentId(twoCategoryDO.getId());
                        threeCategoryDO.setName(caName);
                        threeCategoryDO.setLevel(3);
                        threeCategoryDO.setSort(j);
                        threeCategoryDO.setHotStatus("Y");
                        threeCategoryDO.setNavStatus("Y");
                        threeCategoryDO.setCreator(1L);
                        threeCategoryDO.setModifier(1L);
                        categoryDOList.add(threeCategoryDO);
                    }
                    iCategoryService.saveBatch(categoryDOList);
                }
                i++;
            }
            k++;
        }
    }

    /**
     * 更新商品分类
     */
    @Test
    public void testUpdate() {
        List<CategoryDO> categoryDOList = categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getLevel, 2));
        List<CategoryDO> update = Lists.newArrayList();
        for (CategoryDO categoryDO : categoryDOList) {
            List<CategoryDO> categoryDOList1 = categoryMapper.selectList(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getParentId, categoryDO.getId()));
            for (CategoryDO aDo : categoryDOList1) {
                aDo.setPath(categoryDO.getPath() + aDo.getId() + ".");
            }
            update.addAll(categoryDOList1);
            iCategoryService.updateBatchById(categoryDOList1);
        }
    }

    /**
     * 品牌
     * 品牌--商品分类
     */
    @Test
    public void testBrand() throws Exception {
        String html = "D:\\jd.html";
        Document document = Jsoup.parse(new File(html), "utf-8");
        Element j_cate = document.getElementById("J_cate");
        Elements li = j_cate.getElementsByTag("li");
        int k = 1;
        for (Element oneCategory : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element twoCategory : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element threeCategory : threeLevel) {
                    Elements a = threeCategory.getElementsByTag("a");
                    for (Element element : a) {
                        Document br = Jsoup.connect(element.attr("href")).get();
                        Element brandsArea = br.getElementById("brandsArea");
                        if (brandsArea != null) {
                            Elements brandElements = brandsArea.getElementsByTag("li");
                            int z = 1;
                            for (Element element1 : brandElements) {
                                Element brand = element1.getElementsByTag("a").first();
                                BrandDO nameBrand = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, brand.text()));
                                String caName = oneCategory.text().trim() + "_" + twoCategory.text().trim() + "_" + element.text().trim();
                                // 查询三级分类
                                CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                        .eq(CategoryDO::getLevel, 3)
                                );
                                if (nameBrand == null) {
                                    BrandDO brandDO = new BrandDO();
                                    brandDO.setName(brand.text());
                                    if (brand.text().contains("（")) {
                                        brandDO.setEnglishName(StrUtil.subBetween(brand.text(), "（", "）"));
                                    } else {
                                        brandDO.setEnglishName(brand.text());
                                        brandDO.setName(brand.text());
                                    }
                                    String attr = element1.attr("data-initial");
                                    if (attr != null) {
                                        if (attr.length() == 1) {
                                            brandDO.setFirstLetter(attr.toUpperCase());
                                        } else {
                                            brandDO.setFirstLetter(attr.substring(0, 1).toUpperCase());
                                        }
                                    }
                                    if (StrUtil.isNotBlank(brand.getElementsByTag("img").attr("src"))) {
                                        brandDO.setLogo("https:" + brand.getElementsByTag("img").attr("src"));
                                    }
                                    brandDO.setCreator(1L);
                                    brandDO.setModifier(1L);
                                    brandMapper.insert(brandDO);
                                    CategoryBrandDO categoryBrandDO = new CategoryBrandDO();
                                    categoryBrandDO.setCategoryId(categoryDO.getId());
                                    categoryBrandDO.setBrandId(brandDO.getId());
                                    categoryBrandDO.setCascadeCategoryId(categoryDO.getPath());
                                    categoryBrandDO.setSort(z++);
                                    categoryBrandDO.setCreator(1L);
                                    categoryBrandDO.setModifier(1L);
                                    categoryBrandMapper.insert(categoryBrandDO);
                                } else {
                                    CategoryBrandDO categoryBrandDO = new CategoryBrandDO();
                                    categoryBrandDO.setCategoryId(categoryDO.getId());
                                    categoryBrandDO.setBrandId(nameBrand.getId());
                                    categoryBrandDO.setCascadeCategoryId(categoryDO.getPath());
                                    categoryBrandDO.setSort(z++);
                                    categoryBrandDO.setCreator(1L);
                                    categoryBrandDO.setModifier(1L);
                                    categoryBrandMapper.insert(categoryBrandDO);
                                }
                            }
                        }
                    }
                }
                i++;
            }
            k++;
        }
    }

    /**
     * 属性分类
     */
    @Test
    public void testAttributeType() throws Exception {
        String html = "D:\\jd.html";
        Document document = Jsoup.parse(new File(html), "utf-8");
        Element j_cate = document.getElementById("J_cate");
        Elements li = j_cate.getElementsByTag("li");
        int k = 1;
        for (Element oneCategory : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element twoCategory : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element threeCategory : threeLevel) {
                    Elements a = threeCategory.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneCategory.text().trim() + "_" + twoCategory.text().trim() + "_" + element.text().trim();
                        CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                .eq(CategoryDO::getLevel, 3)
                        );
                        Document br = null;
                        try {
                            String href = httpClientUtil.get(element.attr("href"));
                            br = Jsoup.parse(href);
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/AttributeType.txt", true);
                                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                bufWrite.write("读取分类失败," + element.text());
                                bufWrite.newLine();
                                bufWrite.close();
                                outWriter.close();
                                out.close();
                            } catch (Exception e1) {
                            }
                            continue;
                        }
                        Elements elementsByClass = br.getElementsByClass("gl-i-wrap j-sku-item");
                        if (elementsByClass.size() == 0) {
                            if (br.getElementsByClass("gl-item").size() == 0) {
                                continue;
                            }
                            elementsByClass = br.getElementsByClass("gl-item").first().getElementsByClass("gl-i-wrap  j-sku-item");
                            if (elementsByClass.first() == null) {
                                continue;
                            }
                        }

                        // 读取第一个sku
                        String skuUrl = "https://item.jd.com/" + elementsByClass.first().attr("data-sku") + ".html#product-detail";
                        Document sku = null;
                        try {
                            sku = Jsoup.connect(skuUrl).get();
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/AttributeType.txt", true);
                                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                bufWrite.write("读取商品失败," + "" + "," + skuUrl);
                                bufWrite.newLine();
                                bufWrite.close();
                                outWriter.close();
                                out.close();
                            } catch (Exception e1) {
                            }
                            continue;
                        }
                        Elements Ptable = sku.getElementsByClass("Ptable-item");
                        int o = 1;
                        for (Element table : Ptable) {
                            String typeName = table.getElementsByTag("h3").first().text();
                            AttributeTypeDO attributeTypeDO = new AttributeTypeDO();
                            attributeTypeDO.setCategoryId(categoryDO.getId());
                            attributeTypeDO.setCascadeCategoryId(categoryDO.getPath());
                            attributeTypeDO.setName(element.text() + "_" + typeName);
                            attributeTypeDO.setShowName(typeName);
                            attributeTypeDO.setSort(o++);
                            attributeTypeDO.setCreator(1L);
                            attributeTypeDO.setModifier(1L);
                            attributeTypeMapper.insert(attributeTypeDO);
                        }
                    }
                }
                i++;
            }
            k++;

        }
    }

    /**
     * 属性
     * 分类-属性
     */
    @Test
    public void testAttribute() throws Exception {
        String html = "D:\\jd.html";
        Document document = Jsoup.parse(new File(html), "utf-8");
        Element j_cate = document.getElementById("J_cate");
        Elements li = j_cate.getElementsByTag("li");
        int k = 1;
        for (Element oneCategory : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element twoCategory : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element threeCategory : threeLevel) {
                    Elements a = threeCategory.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneCategory.text().trim() + "_" + twoCategory.text().trim() + "_" + element.text().trim();
                        CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                .eq(CategoryDO::getLevel, 3)
                        );

                        Document br = null;
                        try {
                            String href = httpClientUtil.get(element.attr("href"));
                            br = Jsoup.parse(href);
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/Attribute.txt", true);
                                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                bufWrite.write("读取分类失败," + caName);
                                bufWrite.newLine();
                                bufWrite.close();
                                outWriter.close();
                                out.close();
                            } catch (Exception e1) {
                            }
                            continue;
                        }
                        // 读取查询页的属性
                        Elements J_selectorFold = br.getElementsByClass("J_selectorLine s-line J_selectorFold");
                        for (Element selectorFold : J_selectorFold) {
                            String text = selectorFold.getElementsByClass("sl-key").first().text();
                            String substring = text.substring(0, text.length() - 1);
                            StringBuilder sb = new StringBuilder();
                            for (Element value : selectorFold.getElementsByTag("li")) {
                                sb.append(value.text()).append(",");
                            }

                            String attributeName = oneCategory.text().trim() + "_" + substring.trim();
                            AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, attributeName));
                            if (exists == null) {
                                // 插入属性表
                                exists = new AttributeDO();
                                // 属性名称
                                exists.setName(attributeName);
                                exists.setShowName(substring);
                                exists.setInputType(2);
                                exists.setInputList(sb.substring(0, sb.length() - 1));
                                exists.setHandAddStatus("Y");
                                exists.setCreator(1L);
                                exists.setModifier(1L);
                                attributeMapper.insert(exists);
                            }

                            // 插入分类属性表
                            CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
                            categoryAttributeDO.setCategoryId(categoryDO.getId());
                            categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
                            categoryAttributeDO.setAttributeId(exists.getId());
                            boolean b = selectorFold.getElementsByClass("sl-e-multiple J_extMultiple") != null
                                    && selectorFold.getElementsByClass("sl-e-multiple J_extMultiple").size() > 0;
                            categoryAttributeDO.setCanScreen(b ? 3 : 2);
                            categoryAttributeDO.setCreator(1L);
                            categoryAttributeDO.setModifier(1L);
                            categoryAttributeMapper.insert(categoryAttributeDO);

                        }

                        String script = br.select("script").not("[async]").first().data();
                        if (StrUtil.isNotBlank(script)) {
                            try {
                                String other_exts = StrUtil.subBetween(script, "var other_exts =", "var pay_after =").trim();
                                String sub = StrUtil.sub(other_exts, 0, other_exts.length() - 1);
                                JSONArray jsonArray = JSONArray.parseArray(sub);
                                for (int i1 = 0; i1 < jsonArray.size(); i1++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i1);

                                    String attributeName = oneCategory.text().trim() + "_" + jsonObject.getString("name").trim();
                                    AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, attributeName));
                                    if (exists == null) {
                                        // 插入属性表
                                        exists = new AttributeDO();
                                        // 属性名称
                                        exists.setName(attributeName);
                                        exists.setShowName(jsonObject.getString("name"));
                                        exists.setInputType(2);
                                        exists.setInputList(jsonObject.getString("value_name").replaceAll(";", ","));
                                        exists.setHandAddStatus("Y");
                                        exists.setCreator(1L);
                                        exists.setModifier(1L);
                                        exists.setType(1);
                                        attributeMapper.insert(exists);
                                    }

                                    CategoryAttributeDO attribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                            .eq(CategoryAttributeDO::getAttributeId, exists.getId()));

                                    if (attribute != null && !attribute.getCategoryId().equals(categoryDO.getId())) {
                                        exists.setType(2);
                                        attributeMapper.updateById(exists);
                                    }


                                    CategoryAttributeDO categoryAttribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                            .eq(CategoryAttributeDO::getCategoryId, categoryDO.getId())
                                            .eq(CategoryAttributeDO::getAttributeId, exists.getId()));

                                    if (categoryAttribute == null) {
                                        // 插入分类属性表
                                        CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
                                        categoryAttributeDO.setCategoryId(categoryDO.getId());
                                        categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
                                        categoryAttributeDO.setAttributeId(exists.getId());
                                        // 选项大于5个就多选
                                        categoryAttributeDO.setCanScreen(exists.getInputList().split(",").length > 5 ? 3 : 2);
                                        categoryAttributeDO.setCreator(1L);
                                        categoryAttributeDO.setModifier(1L);
                                        categoryAttributeMapper.insert(categoryAttributeDO);
                                    }

                                }
                            } catch (Exception e) {

                            }

                        }

                        Elements elementsByClass = br.getElementsByClass("gl-i-wrap j-sku-item");
                        if (elementsByClass.size() == 0) {
                            if (br.getElementsByClass("gl-item").size() == 0) {
                                continue;
                            }
                            elementsByClass = br.getElementsByClass("gl-item").first().getElementsByClass("gl-i-wrap  j-sku-item");
                            if (elementsByClass.first() == null) {
                                continue;
                            }
                        }
                        String skuUrl = "https://item.jd.com/" + elementsByClass.first().attr("data-sku") + ".html#product-detail";


                        Document sku = null;
                        try {
                            sku = Jsoup.connect(skuUrl).get();
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/Attribute.txt", true);
                                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                bufWrite.write("读取商品失败," + caName + "," + skuUrl);
                                bufWrite.newLine();
                                bufWrite.close();
                                outWriter.close();
                                out.close();
                            } catch (Exception e1) {
                            }
                            continue;
                        }

                        // sku的销售规格
                        if (sku.getElementsByClass("li p-choose") != null) {
                            for (Element byClass : sku.getElementsByClass("li p-choose")) {
                                String dt = byClass.attr("data-type");
                                String attributeName = oneCategory.text().trim() + "_" + dt.trim();

                                AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, attributeName));
                                if (exists == null) {
                                    exists = new AttributeDO();
                                    exists.setName(attributeName);
                                    exists.setShowName(dt);
                                    exists.setInputType(2);
                                    exists.setHandAddStatus("Y");
                                    exists.setCreator(1L);
                                    exists.setModifier(1L);
                                    attributeMapper.insert(exists);
                                }

                                CategoryAttributeDO attribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                        .eq(CategoryAttributeDO::getAttributeId, exists.getId()));

                                if (attribute != null && !attribute.getCategoryId().equals(categoryDO.getId())) {
                                    exists.setType(2);
                                    attributeMapper.updateById(exists);
                                }

                                CategoryAttributeDO categoryAttribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                        .eq(CategoryAttributeDO::getCategoryId, categoryDO.getId())
                                        .eq(CategoryAttributeDO::getAttributeId, exists.getId()));
                                if (categoryAttribute == null) {
                                    // 插入分类属性表
                                    CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
                                    categoryAttributeDO.setCategoryId(categoryDO.getId());
                                    categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
                                    categoryAttributeDO.setAttributeId(exists.getId());
                                    // 选项大于5个就多选
                                    categoryAttributeDO.setCanScreen(1);
                                    categoryAttributeDO.setCreator(1L);
                                    categoryAttributeDO.setModifier(1L);
                                    categoryAttributeMapper.insert(categoryAttributeDO);
                                }
                            }

                        }

                        // sku详情页的卖点
                        if (sku.getElementsByClass("parameter2 p-parameter-list") != null
                                && sku.getElementsByClass("parameter2 p-parameter-list").size() > 0) {
                            Element first = sku.getElementsByClass("parameter2 p-parameter-list").first();
                            for (Element param : first.getElementsByTag("li")) {
                                String[] split = param.text().split("：");

                                String showName = split[0].trim();
                                String attributeName = oneCategory.text().trim() + "_" + showName;

                                AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, attributeName));
                                if (exists == null) {
                                    exists = new AttributeDO();
                                    exists.setName(attributeName);
                                    exists.setShowName(showName);
                                    exists.setInputType(2);
                                    exists.setHandAddStatus("Y");
                                    exists.setCreator(1L);
                                    exists.setModifier(1L);
                                    attributeMapper.insert(exists);
                                }

                                CategoryAttributeDO attribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                        .eq(CategoryAttributeDO::getAttributeId, exists.getId()));

                                if (attribute != null && !attribute.getCategoryId().equals(categoryDO.getId())) {
                                    exists.setType(2);
                                    attributeMapper.updateById(exists);
                                }

                                //新加功能
                                CategoryAttributeDO categoryAttribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                        .eq(CategoryAttributeDO::getCategoryId, categoryDO.getId())
                                        .eq(CategoryAttributeDO::getAttributeId, exists.getId()));
                                if (categoryAttribute == null) {
                                    // 插入分类属性表
                                    CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
                                    categoryAttributeDO.setCategoryId(categoryDO.getId());
                                    categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
                                    categoryAttributeDO.setAttributeId(exists.getId());
                                    categoryAttributeDO.setCanScreen(1);
                                    categoryAttributeDO.setCreator(1L);
                                    categoryAttributeDO.setModifier(1L);
                                    categoryAttributeMapper.insert(categoryAttributeDO);
                                }
                            }
                        }


                        // sku详情页的更多参数
                        Elements Ptable = sku.getElementsByClass("Ptable-item");
                        if (Ptable != null && Ptable.size() > 0) {
                            for (Element table : Ptable) {
                                String typeName = table.getElementsByTag("h3").first().text();
                                AttributeTypeDO attributeTypeDO = attributeTypeMapper.selectOne(Wrappers.<AttributeTypeDO>lambdaQuery()
                                        .eq(AttributeTypeDO::getName, element.text() + "_" + typeName)
                                        .eq(AttributeTypeDO::getCategoryId, categoryDO.getId()));
                                if (attributeTypeDO != null) {
                                    for (Element clearfix : table.getElementsByClass("clearfix")) {
                                        String dt = clearfix.getElementsByTag("dt").first().text();
                                        String attributeName = oneCategory.text().trim() + "_" + dt.trim();

                                        AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, attributeName));
                                        if (exists == null) {
                                            AttributeDO attributeDO = new AttributeDO();
                                            attributeDO.setName(attributeName);
                                            attributeDO.setShowName(dt);
                                            attributeDO.setInputType(2);
                                            attributeDO.setHandAddStatus("Y");
                                            attributeDO.setCreator(1L);
                                            attributeDO.setModifier(1L);
                                            if (clearfix.getElementsByClass("content") != null
                                                    && clearfix.getElementsByClass("content").size() > 0) {
                                                attributeDO.setRemark(clearfix.getElementsByClass("content").first().text());
                                            }
                                            attributeDO.setAttributeTypeId(attributeTypeDO.getId());
                                            attributeMapper.insert(attributeDO);
                                        } else {
                                            exists.setAttributeTypeId(attributeTypeDO.getId());
                                            attributeMapper.updateById(exists);
                                        }

                                        CategoryAttributeDO attribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                                .eq(CategoryAttributeDO::getAttributeId, exists.getId()));

                                        if (attribute != null && !attribute.getCategoryId().equals(categoryDO.getId())) {
                                            exists.setType(2);
                                            attributeMapper.updateById(exists);
                                        }

                                        CategoryAttributeDO categoryAttribute = categoryAttributeMapper.selectOne(Wrappers.<CategoryAttributeDO>lambdaQuery()
                                                .eq(CategoryAttributeDO::getCategoryId, categoryDO.getId())
                                                .eq(CategoryAttributeDO::getAttributeId, exists.getId()));
                                        if (categoryAttribute == null) {
                                            // 插入分类属性表
                                            CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
                                            categoryAttributeDO.setCategoryId(categoryDO.getId());
                                            categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
                                            categoryAttributeDO.setAttributeId(exists.getId());
                                            categoryAttributeDO.setCanScreen(1);
                                            categoryAttributeDO.setCreator(1L);
                                            categoryAttributeDO.setModifier(1L);
                                            categoryAttributeMapper.insert(categoryAttributeDO);
                                        }
                                    }
                                }

                            }
                        }

                    }
                }
                i++;
            }
            k++;

        }
    }

    /**
     * 商品
     * sku
     * sku图片
     * sku扩展
     * 商品-属性值
     * sku - 属性值
     */
    @Test
    public void testProduct() throws Exception {
        String html = "D:\\jd.html";
        Document document = Jsoup.parse(new File(html), "utf-8");
        Element j_cate = document.getElementById("J_cate");
        Elements li = j_cate.getElementsByTag("li");
        int k = 1;
        for (Element oneCategory : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element twoCategory : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element threeCategory : threeLevel) {
                    Elements a = threeCategory.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneCategory.text().trim() + "_" + twoCategory.text().trim() + "_" + element.text().trim();
                        CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                .eq(CategoryDO::getLevel, 3)
                        );

                        Document br = null;
                        try {
                            String href = httpClientUtil.get(element.attr("href"));
                            br = Jsoup.parse(href);
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/Product.txt", true);
                                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                bufWrite.write("读取分类失败," + caName);
                                bufWrite.newLine();
                                bufWrite.close();
                                outWriter.close();
                                out.close();
                            } catch (Exception e1) {
                            }
                            continue;
                        }

                        Elements elementsByClass = br.getElementsByClass("gl-i-wrap j-sku-item");
                        if (elementsByClass.size() == 0) {
                            if (br.getElementsByClass("gl-item").size() == 0) {
                                continue;
                            }
                            elementsByClass = br.getElementsByClass("gl-item").first().getElementsByClass("gl-i-wrap  j-sku-item");
                            if (elementsByClass.first() == null) {
                                continue;
                            }
                        }

                        for (Element skuElement : elementsByClass) {

                            String pic = "https:" + skuElement.getElementsByTag("a").first().attr("src");

                            String skuUrl = "https://item.jd.com/" + skuElement.attr("data-sku") + ".html#product-detail";
                            Document sku = null;
                            try {
                                sku = Jsoup.connect(skuUrl).get();
                            } catch (Exception e) {
                                try {
                                    FileOutputStream out = new FileOutputStream("D:/Product.txt", true);
                                    OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                    BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                    bufWrite.write("读取商品失败," + caName + "," + skuUrl);
                                    bufWrite.newLine();
                                    bufWrite.close();
                                    outWriter.close();
                                    out.close();
                                } catch (Exception e1) {
                                }
                                continue;
                            }


                            String productName = sku.getElementsByClass("item ellipsis").first().text();

                            // 新增或修改商品
                            ProductDO productDO = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getName, productName));
                            if (productDO == null) {
                                productDO = new ProductDO();
                                productDO.setMerchantsId(1L);
                                productDO.setCategoryId(String.valueOf(categoryDO.getId()));
                                productDO.setCascadeCategoryId(categoryDO.getPath());

                                String brandName = sku.getElementById("parameter-brand").getElementsByTag("a").first().text();
                                BrandDO brandDO = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, brandName));
                                if (brandDO == null) {
                                    brandDO = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getEnglishName, brandName));
                                }
                                productDO.setBrandId(brandDO != null ? brandDO.getId() : null);
                                productDO.setFreightTemplateId(1L);
                                productDO.setProductNo(NoUtil.generateProductNo());
                                productDO.setName(productName);
                                productDO.setPic(pic);
                                for (Element param : sku.getElementsByClass("parameter2 p-parameter-list").first().getElementsByTag("li")) {
                                    if (param.text().contains("商品毛重")) {
                                        if (param.text().contains("kg")) {
                                            productDO.setUnit("kg");
                                            String title = param.attr("title");
                                            title = title.substring(0,title.length()-2);
                                            productDO.setWeight(new BigDecimal(title));
                                        } else if (param.text().contains("g")) {
                                            productDO.setUnit("g");
                                            String title = param.attr("title");
                                            title = title.substring(0,title.length()-1);
                                            productDO.setWeight(new BigDecimal(title));
                                        }
                                        break;
                                    }

                                }
                                productDO.setCreator(1L);
                                productDO.setModifier(1L);
                                productMapper.insert(productDO);

                            } else {
                                ArrayList<String> strings = Lists.newArrayList(productDO.getCategoryId().split(","));
                                if (!strings.contains(categoryDO.getId() + "")) {
                                    productDO.setCategoryId(productDO.getCategoryId() + "," + categoryDO.getId());
                                    productDO.setCascadeCategoryId(productDO.getCascadeCategoryId() + "," + categoryDO.getPath());
                                }
                                productMapper.updateById(productDO);
                            }


                            // 新增sku
                            List<SkuDO> skuDOS = skuMapper.selectList(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getProductId, productDO.getId()));
                            String skuName = sku.getElementsByClass("sku-name").first().text();
                            SkuDO skuDO = new SkuDO();
                            skuDO.setProductId(productDO.getId());
                            skuDO.setMerchantsId(1L);
                            skuDO.setCategoryId(productDO.getCategoryId());
                            skuDO.setBrandId(productDO.getBrandId());
                            skuDO.setSkuNo(NoUtil.generateSkuNo());
                            skuDO.setName(skuName);
                            String subName = sku.getElementsByClass("item hide").first().attr("title");
                            skuDO.setSubName(subName);
                            skuDO.setRemark(skuUrl);
                            skuDO.setPic(productDO.getPic());
                            String price = sku.getElementsByClass("p-price").first().text();
                            skuDO.setPrice(new BigDecimal(price));
                            skuDO.setStock(10000);
                            skuDO.setLowStock(100);
                            skuDO.setLockStock(0);
                            skuDO.setSort(skuDOS.size() + 1);
                            skuDO.setPublishStatus("Y");
                            skuDO.setOnPublishTime(new Date());
                            skuDO.setRecommendStatus("Y");
                            skuDO.setNewStatus("Y");
                            skuDO.setPreviewStatus("Y");
                            skuDO.setAuditStatus(2);
                            skuDO.setCascadeCategoryId(productDO.getCascadeCategoryId());
                            skuDO.setCreator(1L);
                            skuDO.setModifier(1L);
                            skuMapper.insert(skuDO);

                            // 存储sku图片
                            String script = br.select("script").not("[async]").first().data();
                            if (StrUtil.isNotBlank(script)) {
                                String other_exts = StrUtil.subBetween(script, "imageList: ", "cat: ").trim();
                                String sub = StrUtil.sub(other_exts, 0, other_exts.length() - 1);
                                JSONArray jsonArray = JSONArray.parseArray(sub);
                                for (int i1 = 0; i1 < jsonArray.size(); i1++) {
                                    String src = jsonArray.getString(i1);
                                    SkuMediaDO skuMediaDO = new SkuMediaDO();
                                    skuMediaDO.setProductId(productDO.getId());
                                    skuMediaDO.setSkuId(skuDO.getId());
                                    skuMediaDO.setMediaType(1);
                                    skuMediaDO.setMediaUrl(src);
                                    skuMediaDO.setSort(i1 + 1);
                                    skuMediaDO.setCreator(1L);
                                    skuMediaDO.setModifier(1L);
                                    skuMediaMapper.insert(skuMediaDO);
                                }
                            }


                            // 商品属性值
                            JSONObject skuSpe = new JSONObject();
                            // sku的销售规格
                            if (sku.getElementsByClass("li p-choose") != null) {
                                for (Element byClass : sku.getElementsByClass("li p-choose")) {
                                    String dt = byClass.attr("data-type");
                                    String s = oneCategory.text().trim() + "_" + dt.trim();
                                    AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                    if (exists == null) {
                                        continue;
                                    }

                                    JSONArray jsonArray = new JSONArray();
                                    for (Element item : byClass.getElementsByClass("item")) {
                                        JSONObject jsonObject = new JSONObject();
                                        String attributeValue = item.attr("data-value");
                                        jsonObject.put("value", attributeValue);
                                        String attributePic = null;
                                        if (item.getElementsByTag("img") != null &&
                                                item.getElementsByTag("img").size() > 0) {
                                            attributePic = item.getElementsByTag("img").first().attr("src");
                                            jsonObject.put("pic", attributePic);
                                        }

                                        ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                                        productAttributeValueDO.setProductId(productDO.getId());
                                        productAttributeValueDO.setAttributeId(exists.getId());
                                        productAttributeValueDO.setIsSellingPoint("N");
                                        productAttributeValueDO.setIsSpecifications("Y");
                                        productAttributeValueDO.setAttributeValue(attributeValue);

                                        productAttributeValueDO.setPic(attributePic);
                                        productAttributeValueDO.setCreator(1L);
                                        productAttributeValueDO.setModifier(1L);
                                        productAttributeValueMapper.insert(productAttributeValueDO);
                                        jsonArray.add(jsonObject);

                                        // 存储 sku 属性值
                                        SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                                        skuAttributeValueDO.setSkuId(skuDO.getId());
                                        skuAttributeValueDO.setProductAttributeValueId(productAttributeValueDO.getId());
                                        skuAttributeValueDO.setCreator(1L);
                                        skuAttributeValueDO.setModifier(1L);
                                        skuAttributeValueMapper.insert(skuAttributeValueDO);
                                    }
                                    skuSpe.put(dt, jsonArray);
                                }
                            }

                            // sku详情页的卖点
                            JSONObject salePoint = new JSONObject();
                            if (sku.getElementsByClass("parameter2 p-parameter-list") != null
                                    && sku.getElementsByClass("parameter2 p-parameter-list").size() > 0) {
                                Element first = sku.getElementsByClass("parameter2 p-parameter-list").first();
                                for (Element param : first.getElementsByTag("li")) {
                                    String[] split = param.text().split("：");

                                    String at = split[0].trim();

                                    String vt = split[1].trim();
                                    String s = oneCategory.text().trim() + "_" + at;
                                    AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                    if (exists == null) {
                                        continue;
                                    }
                                    salePoint.put(at,vt);
                                    ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                                    productAttributeValueDO.setProductId(productDO.getId());
                                    productAttributeValueDO.setAttributeId(exists.getId());
                                    productAttributeValueDO.setIsSellingPoint("Y");
                                    productAttributeValueDO.setIsSpecifications("N");
                                    productAttributeValueDO.setAttributeValue(vt);
                                    productAttributeValueDO.setCreator(1L);
                                    productAttributeValueDO.setModifier(1L);
                                    productAttributeValueMapper.insert(productAttributeValueDO);

                                    // 存储 sku 属性值
                                    SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                                    skuAttributeValueDO.setSkuId(skuDO.getId());
                                    skuAttributeValueDO.setProductAttributeValueId(productAttributeValueDO.getId());
                                    skuAttributeValueDO.setCreator(1L);
                                    skuAttributeValueDO.setModifier(1L);
                                    skuAttributeValueMapper.insert(skuAttributeValueDO);
                                }
                            }


                            // sku详情页的更多参数
                            JSONArray param = new JSONArray();
                            Elements Ptable = sku.getElementsByClass("Ptable-item");
                            if (Ptable != null && Ptable.size() > 0) {
                                for (Element table : Ptable) {

                                    JSONObject type = new JSONObject();
                                    String typeName = table.getElementsByTag("h3").first().text();
                                    JSONArray value = new JSONArray();
                                    for (Element clearfix : table.getElementsByClass("clearfix")) {
                                        String dt = clearfix.getElementsByTag("dt").first().text();
                                        String s = oneCategory.text().trim() + "_" + dt.trim();
                                        AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                        if (exists == null) {
                                            continue;
                                        }

                                        String attributeValue = clearfix.getElementsByTag("dd").not(".Ptable-tips").first().text().trim();
                                        ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                                        productAttributeValueDO.setProductId(productDO.getId());
                                        productAttributeValueDO.setAttributeId(exists.getId());
                                        productAttributeValueDO.setIsSellingPoint("N");
                                        productAttributeValueDO.setIsSpecifications("N");
                                        productAttributeValueDO.setAttributeValue(attributeValue);
                                        productAttributeValueDO.setCreator(1L);
                                        productAttributeValueDO.setModifier(1L);
                                        productAttributeValueMapper.insert(productAttributeValueDO);

                                        // 存储 sku 属性值
                                        SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                                        skuAttributeValueDO.setSkuId(skuDO.getId());
                                        skuAttributeValueDO.setProductAttributeValueId(productAttributeValueDO.getId());
                                        skuAttributeValueDO.setCreator(1L);
                                        skuAttributeValueDO.setModifier(1L);
                                        skuAttributeValueMapper.insert(skuAttributeValueDO);

                                        JSONObject obj = new JSONObject();

                                        obj.put("key",dt);
                                        obj.put("value",attributeValue);
                                        String ext = null;
                                        if (clearfix.getElementsByClass("content") != null
                                                && clearfix.getElementsByClass("content").size() > 0) {
                                            ext = clearfix.getElementsByClass("content").first().text().trim();
                                        }
                                        obj.put("ext",ext);
                                        value.add(obj);
                                    }
                                    type.put(typeName, value);
                                    param.add(type);
                                }
                            }


                            // 存储sku ext
                            SkuExtDO skuExtDO = new SkuExtDO();
                            skuExtDO.setSkuId(skuDO.getId());
                            // skuExtDO.setDetailHtml("");
                            skuExtDO.setSkuSpecificationsJson(skuSpe.toJSONString());
                            skuExtDO.setSkuSalePointJson(salePoint.toJSONString());
                            skuExtDO.setSkuParamJson(param.toJSONString());
                            skuExtDO.setCreator(1L);
                            skuExtDO.setModifier(1L);
                            skuExtMapper.insert(skuExtDO);
                        }

                    }
                }
            }
            i++;
        }
        k++;

    }









    @Test
    public void testProduct2() throws Exception {
        String html = "D:\\jd.html";
        Document document = Jsoup.parse(new File(html), "utf-8");
        Element j_cate = document.getElementById("J_cate");
        Elements li = j_cate.getElementsByTag("li");
        int k = 1;
        for (Element oneCategory : li) {
            if (k<2){
                k++;
                continue;
            }
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element twoCategory : twoLevels) {

                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element threeCategory : threeLevel) {
                    Elements a = threeCategory.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneCategory.text().trim() + "_" + twoCategory.text().trim() + "_" + element.text().trim();
                        CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                .eq(CategoryDO::getLevel, 3)
                        );
                        System.out.println("======================开始解析分类:"+caName + "=======================");
                        Document br = null;
                        try {
                            String href = httpClientUtil.get(element.attr("href"));
                            br = Jsoup.parse(href);
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/Product.txt", true);
                                OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                bufWrite.write("读取分类失败," + caName);
                                bufWrite.newLine();
                                bufWrite.close();
                                outWriter.close();
                                out.close();
                            } catch (Exception e1) {
                            }
                            continue;
                        }

                        Elements elementsByClass = br.getElementsByClass("gl-i-wrap j-sku-item");
                        if (elementsByClass.size() == 0) {
                            if (br.getElementsByClass("gl-item").size() == 0) {
                                continue;
                            }
                            elementsByClass = br.getElementsByClass("gl-item").first().getElementsByClass("gl-i-wrap  j-sku-item");
                            if (elementsByClass.first() == null) {
                                continue;
                            }
                        }

                        for (Element skuElement : elementsByClass) {

                            String pic = "https:" + skuElement.getElementsByTag("a").first().attr("src");

                            String skuUrl = "https://item.jd.com/" + skuElement.attr("data-sku") + ".html#product-detail";
                            Document sku = null;
                            try {
                                sku = Jsoup.connect(skuUrl).get();
                            } catch (Exception e) {
                                try {
                                    FileOutputStream out = new FileOutputStream("D:/Product.txt", true);
                                    OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");
                                    BufferedWriter bufWrite = new BufferedWriter(outWriter);
                                    bufWrite.write("读取商品失败," + caName + "," + skuUrl);
                                    bufWrite.newLine();
                                    bufWrite.close();
                                    outWriter.close();
                                    out.close();
                                } catch (Exception e1) {
                                }
                                continue;
                            }


                            String productName = sku.getElementsByClass("item ellipsis").first().text();

                            // 新增或修改商品

                            ProductDO productDO = productMapper.selectOne(Wrappers.<ProductDO>lambdaQuery().eq(ProductDO::getName, productName));
                            if (productDO == null) {
                                productDO = new ProductDO();
                                productDO.setMerchantsId(1L);
                                productDO.setCategoryId(String.valueOf(categoryDO.getId()));
                                productDO.setCascadeCategoryId(categoryDO.getPath());

                                String brandName = sku.getElementById("parameter-brand").getElementsByTag("a").first().text();
                                BrandDO brandDO = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, brandName));
                                if (brandDO == null) {
                                    brandDO = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getEnglishName, brandName));
                                }
                                productDO.setBrandId(brandDO != null ? brandDO.getId() : null);
                                productDO.setFreightTemplateId(1L);
                                productDO.setProductNo(NoUtil.generateProductNo());
                                productDO.setName(productName);
                                productDO.setPic(pic);
                                for (Element param : sku.getElementsByClass("parameter2 p-parameter-list").first().getElementsByTag("li")) {
                                    if (param.text().contains("商品毛重")) {
                                        if (param.text().contains("kg")) {
                                            productDO.setUnit("kg");
                                            String title = param.attr("title");
                                            title = title.substring(0,title.length()-2);
                                            productDO.setWeight(new BigDecimal(title));
                                        } else if (param.text().contains("g")) {
                                            productDO.setUnit("g");
                                            String title = param.attr("title");
                                            title = title.substring(0,title.length()-1);
                                            productDO.setWeight(new BigDecimal(title));
                                        }
                                        break;
                                    }

                                }
                                productDO.setCreator(1L);
                                productDO.setModifier(1L);
                                System.out.println("=====新增商品=====");
                                System.out.println(JSONObject.toJSONString(productDO,true));
                            } else {
                                ArrayList<String> strings = Lists.newArrayList(productDO.getCategoryId().split(","));
                                if (!strings.contains(categoryDO.getId() + "")) {
                                    productDO.setCategoryId(productDO.getCategoryId() + "," + categoryDO.getId());
                                    productDO.setCascadeCategoryId(productDO.getCascadeCategoryId() + "," + categoryDO.getPath());
                                }
                                System.out.println("=====修改商品=====");

                                System.out.println(JSONObject.toJSONString(productDO,true));
                            }


                            // 新增sku
                            System.out.println("=====新增sku=====");
                            List<SkuDO> skuDOS = skuMapper.selectList(Wrappers.<SkuDO>lambdaQuery().eq(SkuDO::getProductId, productDO.getId()));
                            String skuName = sku.getElementsByClass("sku-name").first().text();
                            SkuDO skuDO = new SkuDO();
                            skuDO.setProductId(productDO.getId());
                            skuDO.setMerchantsId(1L);
                            skuDO.setCategoryId(productDO.getCategoryId());
                            skuDO.setBrandId(productDO.getBrandId());
                            skuDO.setSkuNo(NoUtil.generateSkuNo());
                            skuDO.setName(skuName);
                            String subName = sku.getElementsByClass("item hide").first().attr("title");
                            skuDO.setSubName(subName);
                            skuDO.setRemark(skuUrl);
                            skuDO.setPic(productDO.getPic());
                            String price = sku.getElementsByClass("p-price").first().text().substring(1);
                            System.out.println(price);
                            skuDO.setPrice(new BigDecimal(price));
                            skuDO.setStock(10000);
                            skuDO.setLowStock(100);
                            skuDO.setLockStock(0);
                            skuDO.setSort(skuDOS.size() + 1);
                            skuDO.setPublishStatus("Y");
                            skuDO.setOnPublishTime(new Date());
                            skuDO.setRecommendStatus("Y");
                            skuDO.setNewStatus("Y");
                            skuDO.setPreviewStatus("Y");
                            skuDO.setAuditStatus(2);
                            skuDO.setCascadeCategoryId(productDO.getCascadeCategoryId());
                            skuDO.setCreator(1L);
                            skuDO.setModifier(1L);
                            System.out.println(JSONObject.toJSONString(skuDO,true));

                            // 存储sku图片
                            System.out.println("=====新增sku图片=====");

                            String script = br.select("script").not("[async]").first().data();
                            if (StrUtil.isNotBlank(script)) {
                                String other_exts = StrUtil.subBetween(script, "imageList: ", "cat: ").trim();
                                String sub = StrUtil.sub(other_exts, 0, other_exts.length() - 1);
                                JSONArray jsonArray = JSONArray.parseArray(sub);
                                for (int i1 = 0; i1 < jsonArray.size(); i1++) {
                                    String src = jsonArray.getString(i1);
                                    SkuMediaDO skuMediaDO = new SkuMediaDO();
                                    skuMediaDO.setProductId(productDO.getId());
                                    skuMediaDO.setSkuId(skuDO.getId());
                                    skuMediaDO.setMediaType(1);
                                    skuMediaDO.setMediaUrl(src);
                                    skuMediaDO.setSort(i1 + 1);
                                    skuMediaDO.setCreator(1L);
                                    skuMediaDO.setModifier(1L);
                                    System.out.println(JSONObject.toJSONString(skuMediaDO,true));

                                }
                            }


                            // 商品属性值
                            JSONObject skuSpe = new JSONObject();
                            // sku的销售规格
                            System.out.println("=====新增sku销售规格=====");

                            if (sku.getElementsByClass("li p-choose") != null) {
                                for (Element byClass : sku.getElementsByClass("li p-choose")) {
                                    String dt = byClass.attr("data-type");
                                    String s = oneCategory.text().trim() + "_" + dt.trim();
                                    AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                    if (exists == null) {
                                        continue;
                                    }

                                    JSONArray jsonArray = new JSONArray();
                                    for (Element item : byClass.getElementsByClass("item")) {
                                        JSONObject jsonObject = new JSONObject();
                                        String attributeValue = item.attr("data-value");
                                        jsonObject.put("value", attributeValue);
                                        String attributePic = null;
                                        if (item.getElementsByTag("img") != null &&
                                                item.getElementsByTag("img").size() > 0) {
                                            attributePic = item.getElementsByTag("img").first().attr("src");
                                            jsonObject.put("pic", attributePic);
                                        }

                                        ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                                        productAttributeValueDO.setProductId(productDO.getId());
                                        productAttributeValueDO.setAttributeId(exists.getId());
                                        productAttributeValueDO.setIsSellingPoint("N");
                                        productAttributeValueDO.setIsSpecifications("Y");
                                        productAttributeValueDO.setAttributeValue(attributeValue);

                                        productAttributeValueDO.setPic(attributePic);
                                        productAttributeValueDO.setCreator(1L);
                                        productAttributeValueDO.setModifier(1L);
                                        System.out.println(JSONObject.toJSONString(productAttributeValueDO,true));

                                        jsonArray.add(jsonObject);

                                        // 存储 sku 属性值
                                        SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                                        skuAttributeValueDO.setSkuId(skuDO.getId());
                                        skuAttributeValueDO.setProductAttributeValueId(productAttributeValueDO.getId());
                                        skuAttributeValueDO.setCreator(1L);
                                        skuAttributeValueDO.setModifier(1L);
                                        System.out.println(JSONObject.toJSONString(skuAttributeValueDO,true));

                                    }
                                    skuSpe.put(dt, jsonArray);
                                }
                            }

                            // sku详情页的卖点
                            System.out.println("=====新增sku卖点=====");

                            JSONObject salePoint = new JSONObject();
                            if (sku.getElementsByClass("parameter2 p-parameter-list") != null
                                    && sku.getElementsByClass("parameter2 p-parameter-list").size() > 0) {
                                Element first = sku.getElementsByClass("parameter2 p-parameter-list").first();
                                for (Element param : first.getElementsByTag("li")) {
                                    String[] split = param.text().split("：");

                                    String at = split[0].trim();

                                    String vt = split[1].trim();
                                    String s = oneCategory.text().trim() + "_" + at;
                                    AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                    if (exists == null) {
                                        continue;
                                    }
                                    salePoint.put(at,vt);
                                    ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                                    productAttributeValueDO.setProductId(productDO.getId());
                                    productAttributeValueDO.setAttributeId(exists.getId());
                                    productAttributeValueDO.setIsSellingPoint("Y");
                                    productAttributeValueDO.setIsSpecifications("N");
                                    productAttributeValueDO.setAttributeValue(vt);
                                    productAttributeValueDO.setCreator(1L);
                                    productAttributeValueDO.setModifier(1L);
                                    System.out.println(JSONObject.toJSONString(productAttributeValueDO,true));


                                    // 存储 sku 属性值
                                    SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                                    skuAttributeValueDO.setSkuId(skuDO.getId());
                                    skuAttributeValueDO.setProductAttributeValueId(productAttributeValueDO.getId());
                                    skuAttributeValueDO.setCreator(1L);
                                    skuAttributeValueDO.setModifier(1L);
                                    System.out.println(JSONObject.toJSONString(skuAttributeValueDO,true));

                                }
                            }


                            // sku详情页的更多参数
                            System.out.println("=====新增sku更多参数=====");

                            JSONArray param = new JSONArray();
                            Elements Ptable = sku.getElementsByClass("Ptable-item");
                            if (Ptable != null && Ptable.size() > 0) {
                                for (Element table : Ptable) {

                                    JSONObject type = new JSONObject();
                                    String typeName = table.getElementsByTag("h3").first().text();
                                    JSONArray value = new JSONArray();
                                    for (Element clearfix : table.getElementsByClass("clearfix")) {
                                        String dt = clearfix.getElementsByTag("dt").first().text();
                                        String s = oneCategory.text().trim() + "_" + dt.trim();
                                        AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                        if (exists == null) {
                                            continue;
                                        }

                                        String attributeValue = clearfix.getElementsByTag("dd").not(".Ptable-tips").first().text().trim();
                                        ProductAttributeValueDO productAttributeValueDO = new ProductAttributeValueDO();
                                        productAttributeValueDO.setProductId(productDO.getId());
                                        productAttributeValueDO.setAttributeId(exists.getId());
                                        productAttributeValueDO.setIsSellingPoint("N");
                                        productAttributeValueDO.setIsSpecifications("N");
                                        productAttributeValueDO.setAttributeValue(attributeValue);
                                        productAttributeValueDO.setCreator(1L);
                                        productAttributeValueDO.setModifier(1L);
                                        System.out.println(JSONObject.toJSONString(productAttributeValueDO,true));

                                        // 存储 sku 属性值
                                        SkuAttributeValueDO skuAttributeValueDO = new SkuAttributeValueDO();
                                        skuAttributeValueDO.setSkuId(skuDO.getId());
                                        skuAttributeValueDO.setProductAttributeValueId(productAttributeValueDO.getId());
                                        skuAttributeValueDO.setCreator(1L);
                                        skuAttributeValueDO.setModifier(1L);
                                        System.out.println(JSONObject.toJSONString(skuAttributeValueDO,true));
                                        JSONObject obj = new JSONObject();

                                        obj.put("key",dt);
                                        obj.put("value",attributeValue);
                                        String ext = null;
                                        if (clearfix.getElementsByClass("content") != null
                                                && clearfix.getElementsByClass("content").size() > 0) {
                                            ext = clearfix.getElementsByClass("content").first().text().trim();
                                        }
                                        obj.put("ext",ext);
                                        value.add(obj);
                                    }
                                    type.put(typeName, value);
                                    param.add(type);
                                }
                            }


                            // 存储sku ext
                            SkuExtDO skuExtDO = new SkuExtDO();
                            skuExtDO.setSkuId(skuDO.getId());
                            // skuExtDO.setDetailHtml("");
                            skuExtDO.setSkuSpecificationsJson(skuSpe.toJSONString());
                            skuExtDO.setSkuSalePointJson(salePoint.toJSONString());
                            skuExtDO.setSkuParamJson(param.toJSONString());
                            skuExtDO.setCreator(1L);
                            skuExtDO.setModifier(1L);
                            System.out.println("=====新增sku ext=====");

                            System.out.println(JSONObject.toJSONString(skuExtDO,true));
                        }

                    }
                }
            }
            i++;
        }
        k++;

    }

}
