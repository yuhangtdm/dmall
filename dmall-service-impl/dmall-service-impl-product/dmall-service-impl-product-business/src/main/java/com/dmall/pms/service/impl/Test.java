package com.dmall.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.pms.generator.dataobject.AttributeDO;
import com.dmall.pms.generator.dataobject.AttributeTypeDO;
import com.dmall.pms.generator.dataobject.CategoryAttributeDO;
import com.dmall.pms.generator.dataobject.CategoryDO;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
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

                for (Element three : threeLevel) {
                    Elements a = three.getElementsByTag("a");
                    for (Element element : a) {
                        String caName = oneLevel.text() + "-" + two.text() + "-" + element.text();

                        Document br = null;
                        try {
                            br = Jsoup.connect(element.attr("href")).get();
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

//                            System.out.println(sku.getElementsByClass("item ellipsis").first().text());
//                            System.out.println(sku.getElementsByClass("sku-name").first().text());
                            System.out.println(sku.getElementById("J-detail-content").data());

                        }
                    }
                }
                i++;
            }
            k++;

        }



    }

}
