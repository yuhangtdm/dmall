package com.dmall.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dmall.common.util.NoUtil;
import com.dmall.pms.generator.dataobject.*;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
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
                        if (caName.equals("家用电器_空调\uE601_超薄电视")){
                            System.out.println("无敌了");
                        }
                    }
                }
            }
        }
    }

}
