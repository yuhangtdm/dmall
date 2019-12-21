package com.dmall.pms;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.util.NoUtil;
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
    private SkuAttributeMapper skuAttributeMapper;

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
        for (Element oneLevel : li) {
            CategoryDO oneCategoryDO = new CategoryDO();
            oneCategoryDO.setParentId(0L);
            oneCategoryDO.setName(oneLevel.text());
            oneCategoryDO.setLevel(1);
            oneCategoryDO.setSort(k);
            oneCategoryDO.setHotStatus("Y");
            oneCategoryDO.setNavStatus("Y");
            oneCategoryDO.setCreator(1L);
            oneCategoryDO.setModifier(1L);
            categoryMapper.insert(oneCategoryDO);
            oneCategoryDO.setPath("." + oneCategoryDO.getId() + ".");
            categoryMapper.updateById(oneCategoryDO);
            System.out.println("===插入一级分类:" + oneLevel.text() + ",顺序:" + k);

            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element two : twoLevels) {
                CategoryDO twoCategoryDO = new CategoryDO();
                twoCategoryDO.setParentId(oneCategoryDO.getId());
                twoCategoryDO.setName(two.text());
                twoCategoryDO.setLevel(2);
                twoCategoryDO.setSort(i);
                twoCategoryDO.setHotStatus("Y");
                twoCategoryDO.setNavStatus("Y");
                twoCategoryDO.setCreator(1L);
                twoCategoryDO.setModifier(1L);
                categoryMapper.insert(twoCategoryDO);
                twoCategoryDO.setPath(oneCategoryDO.getPath() + twoCategoryDO.getId() + ".");
                categoryMapper.updateById(twoCategoryDO);
                System.out.println("======插入二级分类：" + twoCategoryDO.getName() + ",顺序:" + i);
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                List<CategoryDO> categoryDOList = Lists.newArrayList();
                for (Element three : threeLevel) {
                    Elements a = three.getElementsByTag("a");
                    int j = 1;
                    for (Element element : a) {
                        CategoryDO threeCategory = new CategoryDO();
                        threeCategory.setParentId(twoCategoryDO.getId());
                        threeCategory.setName(oneCategoryDO.getName() + "-" + twoCategoryDO.getName() + "-" + element.text());
                        threeCategory.setLevel(3);
                        threeCategory.setSort(j);
                        threeCategory.setHotStatus("Y");
                        threeCategory.setNavStatus("Y");
                        threeCategory.setCreator(1L);
                        threeCategory.setModifier(1L);
                        String jdCat = StrUtil.subBetween(element.attr("href"), "?cat=", "&");
                        if (StrUtil.isBlank(jdCat)) {
                            jdCat = StrUtil.subBetween(element.attr("href"), "?cat=");
                        }
                        threeCategory.setJdCat(jdCat);
                        categoryDOList.add(threeCategory);
                        System.out.println("=====================三级分类插入完毕：" + threeCategory.getName() + ",顺序:" + j++);
                    }
                    iCategoryService.saveBatch(categoryDOList);
                }
                i++;
            }
            k++;
        }
    }

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
        for (Element oneLevel : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element two : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element three : threeLevel) {
                    Elements a = three.getElementsByTag("a");
                    for (Element element : a) {
                        Document br = Jsoup.connect(element.attr("href")).get();
                        Element brandsArea = br.getElementById("brandsArea");
                        if (brandsArea != null) {
                            Elements liii = brandsArea.getElementsByTag("li");
                            int z = 1;
                            for (Element element1 : liii) {
                                Element brand = element1.getElementsByTag("a").first();
                                BrandDO name = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, brand.text()));
                                String caName = oneLevel.text() + "-" + two.text() + "-" + element.text();
                                CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                        .eq(CategoryDO::getLevel, 3)
                                );
                                if (name == null) {
                                    BrandDO brandDO = new BrandDO();
                                    brandDO.setName(brand.text());
                                    if (brand.text().contains("（")) {
                                        brandDO.setEnglishName(StrUtil.subBetween(brand.text(), "（", "）"));
                                    } else {
                                        brandDO.setEnglishName(brand.text());
                                    }
                                    String attr = element1.attr("data-initial");
                                    if (attr != null) {
                                        if (attr.length() == 1) {
                                            brandDO.setFirstLetter(attr);
                                        } else {
                                            brandDO.setFirstLetter(attr.substring(0, 1));
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
                                    categoryBrandDO.setSort(z++);
                                    categoryBrandDO.setCreator(1L);
                                    categoryBrandDO.setModifier(1L);
                                    categoryBrandMapper.insert(categoryBrandDO);
                                } else {
                                    CategoryBrandDO categoryBrandDO = new CategoryBrandDO();
                                    categoryBrandDO.setCategoryId(categoryDO.getId());
                                    categoryBrandDO.setBrandId(name.getId());
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
        for (Element oneLevel : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element two : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element three : threeLevel) {
                    Elements a = three.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneLevel.text() + "-" + two.text() + "-" + element.text();
                        CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                .eq(CategoryDO::getLevel, 3)
                        );

                        Document br = null;
                        try {
                            String href = httpClientUtil.get(element.attr("href"));
                            br = Jsoup.parse(href);
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/file.txt", true);
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
                        String skuUrl = "https://item.jd.com/" + elementsByClass.first().attr("data-sku") + ".html#product-detail";


                        Document sku = null;
                        try {
                            sku = Jsoup.connect(skuUrl).get();
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/file.txt", true);
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
     * <p>
     * 商品分类-属性
     */
    @Test
    public void testAttribute() throws Exception {
        String html = "D:\\jd.html";
        Document document = Jsoup.parse(new File(html), "utf-8");
        Element j_cate = document.getElementById("J_cate");
        Elements li = j_cate.getElementsByTag("li");
        int k = 1;
        for (Element oneLevel : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element two : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element three : threeLevel) {
                    Elements a = three.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneLevel.text().trim() + "-" + two.text().trim() + "-" + element.text().trim();
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
                            // 插入属性表
                            AttributeDO attributeDO = new AttributeDO();
                            attributeDO.setName(caName + "_" + substring);
                            attributeDO.setShowName(substring);
                            attributeDO.setInputType(2);
                            attributeDO.setInputList(sb.substring(0, sb.length() - 1));
                            attributeDO.setHandAddStatus("Y");
                            attributeDO.setCreator(1L);
                            attributeDO.setModifier(1L);
                            attributeMapper.insert(attributeDO);

                            // 插入分类属性表
                            CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
                            categoryAttributeDO.setCategoryId(categoryDO.getId());
                            categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
                            categoryAttributeDO.setAttributeId(attributeDO.getId());
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

                                    // 插入属性表
                                    AttributeDO attributeDO = new AttributeDO();
                                    attributeDO.setAttributeTypeId(null);
                                    attributeDO.setName(caName + "_" + jsonObject.getString("name"));
                                    attributeDO.setShowName(jsonObject.getString("name"));
                                    attributeDO.setInputList(jsonObject.getString("value_name").replaceAll(";", ","));
                                    attributeDO.setInputType(2);
                                    attributeDO.setHandAddStatus("Y");
                                    attributeDO.setCreator(1L);
                                    attributeDO.setModifier(1L);
                                    attributeMapper.insert(attributeDO);

                                    // 插入分类属性表
                                    CategoryAttributeDO categoryAttributeDO = new CategoryAttributeDO();
                                    categoryAttributeDO.setCategoryId(categoryDO.getId());
                                    categoryAttributeDO.setCascadeCategoryId(categoryDO.getPath());
                                    categoryAttributeDO.setAttributeId(attributeDO.getId());
                                    // 选项大于5个就多选
                                    categoryAttributeDO.setCanScreen(attributeDO.getInputList().split(",").length > 5 ? 3 : 2);
                                    categoryAttributeDO.setCreator(1L);
                                    categoryAttributeDO.setModifier(1L);
                                    categoryAttributeMapper.insert(categoryAttributeDO);
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
                                String s = caName + "_" + dt;
                                AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                if (exists == null) {
                                    AttributeDO attributeDO = new AttributeDO();
                                    attributeDO.setName(s);
                                    attributeDO.setShowName(dt);
                                    attributeDO.setInputType(2);
                                    attributeDO.setHandAddStatus("Y");
                                    attributeDO.setCreator(1L);
                                    attributeDO.setModifier(1L);
                                    attributeMapper.insert(attributeDO);
                                }
                            }

                        }

                        // sku详情页的卖点
                        if (sku.getElementsByClass("parameter2 p-parameter-list") != null
                                && sku.getElementsByClass("parameter2 p-parameter-list").size() > 0) {
                            Element first = sku.getElementsByClass("parameter2 p-parameter-list").first();
                            for (Element param : first.getElementsByTag("li")) {
                                String[] split = param.text().split("：");

                                String s = caName + "_" + split[0];
                                AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                if (exists == null) {
                                    AttributeDO attributeDO = new AttributeDO();
                                    attributeDO.setName(s);
                                    attributeDO.setShowName(split[0]);
                                    attributeDO.setInputType(2);
                                    attributeDO.setHandAddStatus("Y");
                                    attributeDO.setCreator(1L);
                                    attributeDO.setModifier(1L);
                                    attributeMapper.insert(attributeDO);
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
                                        String s = caName + "_" + dt;
                                        AttributeDO exists = attributeMapper.selectOne(Wrappers.<AttributeDO>lambdaQuery().eq(AttributeDO::getName, s));
                                        if (exists == null) {
                                            AttributeDO attributeDO = new AttributeDO();
                                            attributeDO.setName(s);
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
        for (Element oneLevel : li) {
            Element J_popCtn = document.getElementById("J_popCtn");
            String ll = k < 10 ? "0" + k : "" + k;
            Elements twoLevels = J_popCtn.select(".cate_detail_tit[clstag^=h|keycount|head|category_" + ll + "c]");
            int i = 1;
            for (Element two : twoLevels) {
                String oo = i < 10 ? "0" + i : "" + i;
                Elements threeLevel = J_popCtn.select(".cate_detail_con[clstag^=h|keycount|head|category_" + ll + "d" + oo + "]");
                for (Element three : threeLevel) {
                    Elements a = three.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneLevel.text() + "-" + two.text() + "-" + element.text();
                        CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, caName)
                                .eq(CategoryDO::getLevel, 3)
                        );

                        Document br = null;
                        try {
                            String href = httpClientUtil.get(element.attr("href"));
                            br = Jsoup.parse(href);
                        } catch (Exception e) {
                            try {
                                FileOutputStream out = new FileOutputStream("D:/file.txt", true);
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
                                    FileOutputStream out = new FileOutputStream("D:/file.txt", true);
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
                                            title = StrUtil.subBetween(title, "kg");
                                            productDO.setWeight(new BigDecimal(title));
                                        } else if (param.text().contains("g")) {
                                            productDO.setUnit("g");
                                            String title = param.attr("title");
                                            title = StrUtil.subBetween(title, "g");
                                            productDO.setWeight(new BigDecimal(title));
                                        }
                                        break;
                                    }

                                }

                                productDO.setCreator(1L);
                                productDO.setModifier(1L);
                                // productMapper.insert(productDO);

                            } else {
                                ArrayList<String> strings = Lists.newArrayList(productDO.getCategoryId().split(","));
                                if (!strings.contains(categoryDO.getId() + "")) {
                                    productDO.setCategoryId(productDO.getCategoryId() + "," + categoryDO.getId());
                                    productDO.setCascadeCategoryId(productDO.getCascadeCategoryId() + "," + categoryDO.getPath());
                                }
                                // productMapper.updateById(productDO);

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
                            skuDO.setSkuSpecifications("");

                            skuMapper.insert(skuDO);

                            SkuMediaDO skuMediaDO = new SkuMediaDO();
                            skuMediaDO.setProductId(productDO.getId());
                            skuMediaDO.setSkuId(skuDO.getId());
                            skuMediaDO.setMediaType(1);
//                            skuMediaDO.setMediaUrl()
                            skuMediaDO.setSort(0);
                            skuMediaDO.setCreator(0L);
                            skuMediaDO.setGmtCreated(new Date());
                            skuMediaDO.setModifier(0L);
                            skuMediaDO.setGmtModified(new Date());
                            skuMediaDO.setIsDeleted("");


                        }

                    }
                }
            }
            i++;
        }
        k++;

    }
}
