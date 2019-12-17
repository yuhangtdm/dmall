package com.dmall.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @description:
 * @author: created by hang.yu on 2019/12/17 19:56
 */
public class Test {
    public static void main(String[] args) throws IOException {
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
                List<CategoryDO> categoryDOList = Lists.newArrayList();
                for (Element three : threeLevel) {
                    Elements a = three.getElementsByTag("a");
                    for (Element element : a) {
                        System.out.println(element.attr("href") + "----" + oneLevel.text() + "---" + element.text());
                        String jdCat = StrUtil.subBetween(element.attr("href"),"?cat=","&");
                        if (StrUtil.isBlank(jdCat)){
                            jdCat = StrUtil.subAfter(element.attr("href"),"?cat=", true);
                        }
                        System.out.println(jdCat);
                        Document br = Jsoup.connect(element.attr("href")).get();
                        Element brandsArea = br.getElementById("brandsArea");

                        if (brandsArea != null){
                            Elements liii = brandsArea.getElementsByTag("li");
                            for (Element element1 : liii) {
                                Elements brands = element1.getElementsByTag("a");
                                for (Element brand : brands) {
                                    /*System.out.println(brand.text() + brand.getElementsByTag("img").attr("src") + "---"
                                            + element1.attr("data-initial"));
                                    if (brand.text().contains("（")){
                                        System.out.println(StrUtil.subBetween(brand.text(),"（", "）"));
                                    }*/
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
