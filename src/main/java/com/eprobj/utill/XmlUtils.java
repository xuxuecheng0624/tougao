package com.eprobj.utill;

import com.eprobj.entity.Documents;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName XmlUtils
 * @Description 生成xml工具类
 * @Author kangjian
 * @Date 2019/11/28 14:54
 * @Version 1.0
 **/

public class XmlUtils {

    public static void createDom4j(File file, List<Documents> documents){
        try {
            // 创建Document
            Document document = DocumentHelper.createDocument();
            // 添加根节点
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Element root = document.addElement("WCMDOCUMENTS");
            CommUtils commUtils = new CommUtils();
            for (int i = 0; i < documents.size(); i++) {
                // 在根节点下添加第一个子节点
                Element oneChildElement= root.addElement("WCMDOCUMENT").addAttribute("Version", "6.0");
                // 文档节点
                Element documentElement = oneChildElement.addElement("PROPERTIES");
                documentElement.addElement("DOCID");
                documentElement.addElement("DOCVERSION").addText("0");
                documentElement.addElement("DOCTYPE").addText("20");
                documentElement.addElement("DOCPUBTIME");
                documentElement.addElement("CRUSER").addText("<![CDATA[admin]]>");
                documentElement.addElement("CRTIME").addText(sdf.format(documents.get(i).getCreateTime()));
                documentElement.addElement("DOCFLAG").addText("0");
                documentElement.addElement("ATTRIBUTE").addText("<![CDATA[]]>");
                documentElement.addElement("ATTACHPIC").addText("3");
                documentElement.addElement("DOCLINK").addText("<![CDATA[]]>");
                documentElement.addElement("DOCFILENAME").addText("<![CDATA[" + documents.get(i).getAppendixName() + "]]>");
                documentElement.addElement("DOCCHANNEL").addText("栏目id");
                documentElement.addElement("DOCTITLE").addText(documents.get(i).getTitle());
                documentElement.addElement("DOCPEOPLE").addText(documents.get(i).getLinkman());
                documentElement.addElement("DOCSOURCENAME").addText("<![CDATA[]]>");
                documentElement.addElement("DOCSTATUS").addText("1");
                documentElement.addElement("DOCCONTENT").addText("<![CDATA[" + commUtils.deleteAllHTMLTag(documents.get(i).getContent()) + "]]>");
                documentElement.addElement("DOCHTMLCON").addText("<![CDATA["+ documents.get(i).getContent() + "]]>");
                documentElement.addElement("DOCWORDSCOUNT").addText(commUtils.deleteAllHTMLTag(documents.get(i).getContent()).length() + "");
                documentElement.addElement("DOCABSTRACT").addText("<![CDATA[]]>");
                documentElement.addElement("DOCKEYWORDS").addText("<![CDATA[]]>");
                documentElement.addElement("DOCAUTHOR").addText(documents.get(i).getAuthor());
                documentElement.addElement("DOCRELTIME").addText(sdf.format(documents.get(i).getCreateTime()));
                documentElement.addElement("TITLECOLOR").addText("<![CDATA[]]>");
                documentElement.addElement("SUBDOCTITLE").addText("<![CDATA[]]>");
                documentElement.addElement("EDITOR");
                documentElement.addElement("CHNLNAME").addText("<![CDATA[" + documents.get(i).getChannel() + "]]>");
                Element FJListElement = oneChildElement.addElement("WCMAPPENDIXS");

                String appenddixName = documents.get(i).getAppendixName();
                String imageName = documents.get(i).getImagesName();

                if (StringUtils.isNotBlank(appenddixName)) {

                }

                if (StringUtils.isNotBlank(imageName)) {
                }

                if (!StringUtils.isNotBlank(imageName) && !StringUtils.isNotBlank(appenddixName)) {
                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}