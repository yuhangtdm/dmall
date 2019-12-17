package com.dmall.pms;

import java.util.Date;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.BrandDO;
import com.dmall.pms.generator.dataobject.CategoryBrandDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.dmall.pms.generator.mapper.BrandMapper;
import com.dmall.pms.generator.mapper.CategoryBrandMapper;
import com.dmall.pms.generator.mapper.CategoryMapper;
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

import java.io.File;
import java.io.IOException;
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
                        threeCategory.setName(element.text());
                        threeCategory.setLevel(3);
                        threeCategory.setSort(j);
                        threeCategory.setHotStatus("Y");
                        threeCategory.setNavStatus("Y");
                        threeCategory.setCreator(1L);
                        threeCategory.setModifier(1L);
                        String jdCat = StrUtil.subBetween(element.attr("href"),"?cat=","&");
                        if (StrUtil.isBlank(jdCat)){
                            jdCat = StrUtil.subBetween(element.attr("href"),"?cat=");
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
                aDo.setPath(categoryDO.getPath()  + aDo.getId() + ".");
            }
            update.addAll(categoryDOList1);
            iCategoryService.updateBatchById(categoryDOList1);
        }
    }

    @Test
    public void testBrand() throws Exception{
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
                        System.out.println(element.attr("href") + "----" + oneLevel.text() + "---" + element.text());
                        Document br = Jsoup.connect(element.attr("href")).get();
                        Element brandsArea = br.getElementById("brandsArea");
                        if (brandsArea != null){
                            Elements liii = brandsArea.getElementsByTag("li");
                            int z = 1;
                            for (Element element1 : liii) {
                                Element brand = element1.getElementsByTag("a").first();
                                String jdCat = StrUtil.subBetween(element.attr("href"),"?cat=","&");
                                if (StrUtil.isBlank(jdCat)){
                                    jdCat = StrUtil.subBetween(element.attr("href"),"?cat=");
                                }
                                BrandDO name = brandMapper.selectOne(Wrappers.<BrandDO>lambdaQuery().eq(BrandDO::getName, brand.text()));
                                CategoryDO categoryDO = categoryMapper.selectOne(Wrappers.<CategoryDO>lambdaQuery().eq(CategoryDO::getName, element.text())
                                        .eq(CategoryDO::getLevel, 3)
                                        .eq(CategoryDO::getJdCat, jdCat)
                                );
                                if (name == null){
                                    BrandDO brandDO = new BrandDO();
                                    brandDO.setName(brand.text());
                                    if (brand.text().contains("（")){
                                        brandDO.setEnglishName(StrUtil.subBetween(brand.text(),"（", "）"));
                                    }else {
                                        brandDO.setEnglishName(brand.text());
                                    }
                                    brandDO.setFirstLetter(liii.attr("data-initial"));
                                    brandDO.setLogo("https:" + brand.getElementsByTag("img").attr("src"));
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
                                }else {
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

}
