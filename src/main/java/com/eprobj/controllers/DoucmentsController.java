package com.eprobj.controllers;

import com.eprobj.config.MyLog;
import com.eprobj.entity.Documents;
import com.eprobj.entity.User;
import com.eprobj.enums.OperationType;
import com.eprobj.enums.OperationUnit;
import com.eprobj.service.DocumentsService;
import com.eprobj.service.UserService;
import com.eprobj.utill.GetServerRealPathUnit;
import com.eprobj.utill.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ClassName DoucmentsController
 * @Description TODO
 * @Author kangjian
 * @Date 2019/9/10 18:05
 * @Version 1.0
 **/
@Controller
@RequestMapping("/doc")
public class DoucmentsController {

    @Autowired
    private DocumentsService documentsService;
    @Autowired
    private UserService userService;

    @RequestMapping("/toContribute")
    public String toContribute() {
        return "views/fzw/zxtg";
    }

    @Value("${project.basepath}")
    private String basePath;
    /**
     * 上传图片
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload/img")
    @ResponseBody
    public Map<String, Object> uploadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> res = new HashMap<>();
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String path = GetServerRealPathUnit.getPath("static/img");
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            if("php" == oldFileName.substring(oldFileName.lastIndexOf("."))){

            }
            String suffix = "jpg,jpeg,gif,png";
            String suf = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
            if (suffix.indexOf(suf)<0){
                res.put("code", 1);
                return res;
            }
            File filePath = new File(path, newFileName);

            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            File file1 = new File(path + File.separator + newFileName);

            file.transferTo(file1);

            res.put("url", file1);
            res.put("name", newFileName);
            return res;
        } else {
            res.put("code", 1);
            return res;
        }
    }

    @RequestMapping("/upload/contentImg")
    @ResponseBody
    public Map<String, Object> contentImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> res2 = new HashMap<>();
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String path = GetServerRealPathUnit.getPath("static/img");
            String randomStr = UUID.randomUUID().toString();
            String newFileName = randomStr + oldFileName.substring(oldFileName.lastIndexOf("."));
            File filePath = new File(path, newFileName);

            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            File file1 = new File(path + File.separator + newFileName);

            file.transferTo(file1);

            res.put("code", 0);
            res.put("msg", "上传成功");
            res2.put("src",basePath + "/doc/showImg?imgUrl="+newFileName);
            res2.put("title",newFileName);
            res.put("data",res2);
            return res;
        } else {
            res.put("code", 1);
            return res;
        }
    }

    /**
     *author:weijiakun
     * IO流读取图片
     * @param
     */
    @RequestMapping(value = "/showImg",method = RequestMethod.GET)
    public void IoReadImage(HttpServletRequest request,HttpServletResponse response)throws IOException {
        documentsService.IoReadImage(request.getParameter("imgUrl"),response);
    }

    /**
     *author:weijiakun
     * IO流读取图片
     * @param
     */
    @RequestMapping(value = "/showFile",method = RequestMethod.GET)
    @ResponseBody
    public String showFile(HttpServletRequest request,HttpServletResponse response)throws IOException {
        String upload = GetServerRealPathUnit.getPath("static/file");
        String fileUrl = request.getParameter("fileUrl");
        documentsService.IoReadFile(request.getParameter("fileUrl"),response);
        return upload + "/" + fileUrl;
    }

    @RequestMapping("/upload/delete")
    @ResponseBody
    public Map<String, Object> uploadImg(HttpServletRequest request) throws IOException {
        String absPathName = request.getParameter("path").replaceAll("\\\\","/");
        String path = GetServerRealPathUnit.getPath("static")+ absPathName;
        File file = new File(path);
        Map<String, Object> map = new HashMap<>();
        if (!file.exists()) {
            System.out.println("删除文件失败:" + path + "不存在！");
            map.put("isDelete",false);
            return map;
        } else {
            map.put("isDelete",deleteFile(file));
            return map;
        }
    }

    @RequestMapping("/toDetails")
    public ModelAndView toDetails(HttpServletRequest request) {
        String docId = request.getParameter("docId");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/fzw/chakan");
        Documents documents = documentsService.details(docId);
        mav.addObject("documents",documents);
        List files = new ArrayList();
        List images = new ArrayList();
        List fileNames = new ArrayList();
        List imagesNames = new ArrayList();

        List<Map<String, Object>> filesList = new ArrayList<>();
        List<Map<String, Object>> imagesList = new ArrayList<>();
        if (StringUtils.isNotBlank(documents.getAppendixAddress())) {
            files = Arrays.asList(documents.getAppendixAddress().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getImagesName())) {
            imagesNames = Arrays.asList(documents.getImagesName().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getImagesAddress())) {
            images = Arrays.asList(documents.getImagesAddress().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getAppendixName())) {
            fileNames = Arrays.asList(documents.getAppendixName().split(";"));
        }
        for (int i = 0; i< files.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("file",files.get(i));
            map.put("fileName",fileNames.get(i));
            filesList.add(map);
        }
        for (int i = 0; i< images.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image",basePath+"/doc/showImg?imgUrl="+imagesNames.get(i));
            map.put("imageName",imagesNames.get(i));
            imagesList.add(map);
        }
        mav.addObject("files",filesList);
        mav.addObject("images",imagesList);
        return mav;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(HttpServletRequest request) {
        String docId = request.getParameter("docId");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/fzw/edit");
        Documents documents = documentsService.details(docId);
        mav.addObject("documents",documents);
        List files = new ArrayList();
        List images = new ArrayList();
        List fileNames = new ArrayList();
        List imagesNames = new ArrayList();

        List<Map<String, Object>> filesList = new ArrayList<>();
        List<Map<String, Object>> imagesList = new ArrayList<>();
        if (StringUtils.isNotBlank(documents.getAppendixAddress())) {
            files = Arrays.asList(documents.getAppendixAddress().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getImagesName())) {
            imagesNames = Arrays.asList(documents.getImagesName().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getImagesAddress())) {
            images = Arrays.asList(documents.getImagesAddress().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getAppendixName())) {
            fileNames = Arrays.asList(documents.getAppendixName().split(";"));
        }
        for (int i = 0; i< files.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("file",files.get(i));
            map.put("fileName",fileNames.get(i));
            filesList.add(map);
        }
        for (int i = 0; i< images.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image",basePath+"/doc/showImg?imgUrl="+imagesNames.get(i));
            map.put("imageName",imagesNames.get(i));
            imagesList.add(map);
        }
        mav.addObject("files",filesList);
        mav.addObject("images",imagesList);
        return mav;
    }

    @RequestMapping("/toFileList")
    public ModelAndView toFileList(HttpServletRequest request) {
        String docId = request.getParameter("docId");
        ModelAndView mav = new ModelAndView();
        Documents documents = documentsService.details(docId);
        List files = new ArrayList();
        List images = new ArrayList();
        List fileNames = new ArrayList();
        if (StringUtils.isNotBlank(documents.getAppendixAddress())) {
            files = Arrays.asList(documents.getAppendixAddress().split(";"));
        }
        if (StringUtils.isNotBlank(documents.getImagesName())) {
            for (Object image : Arrays.asList(documents.getImagesName().split(";"))) {
                images.add(basePath + "/doc/showImg?imgUrl="+image);
            }
        }
        if (StringUtils.isNotBlank(documents.getAppendixName())) {
            fileNames = Arrays.asList(documents.getAppendixName().split(";"));
        }
        mav.addObject("files",files);
        mav.addObject("images",images);
        mav.addObject("fileNames",fileNames);
        mav.setViewName("views/fzw/fujianlist");
        return mav;
    }


    /**
     * 上传文件
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload/file")
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> res = new HashMap<>();
        if (!file.isEmpty()) {
            String oldFileName = file.getOriginalFilename();
            String newFileName = new String(oldFileName.getBytes(), "UTF-8");
            String path = GetServerRealPathUnit.getPath("/static/file");

            String suffix = "txt,pdf,doc,docx,xls,xlsx,ppt,pptx,rar,jpg,jpeg,gif,png,bmp";
            String suf = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
            if (suffix.indexOf(suf)<0){
                res.put("code", 1);
                return res;
            }
            File filePath = new File(path, oldFileName);

            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            File file1 = new File(path + File.separator + newFileName);

            file.transferTo(file1);


            res.put("url", file1);
            res.put("name", newFileName);
            return res;
        } else {
            res.put("code", 1);
            return res;
        }
    }

    @MyLog(detail = "增加稿件",operationType = OperationType.INSERT,operationUnit = OperationUnit.DOCUMENTS)
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@RequestBody Documents documents) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            documents.setUserId(user.getId());
            int rownum = documentsService.save(documents);
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "成功保存为草稿", null);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "保存草稿失败，请稍后再试", null);
        }
    }

    /**
     * 保存文档
     * @return
     */
    @RequestMapping("/toList")
    public String toList() {
        return "views/fzw/gaojian";
    }

    @RequestMapping("/list-page")
    @ResponseBody
    public Object listPage(@RequestParam(value="qTitle") String qTitle,
                           @RequestParam(value="qUserName") String qUserName,
                           @RequestParam(value="qCreateTime") String qCreateTime,
                           @RequestParam(value="qStatus") String qStatus,
                           @RequestParam(value = "qChannel") String qChannel,
                           @RequestParam(value="page", defaultValue="1") Integer page,
                           @RequestParam(value="limit", defaultValue="20") Integer limit) {
        Map params = new HashMap();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (!subject.hasRole("管理员")) {
            params.put("qId",user.getId());
        }


        params.put("qTitle",qTitle);
        params.put("qStatus",qStatus);
        params.put("qUserName",qUserName);
        params.put("qChannel",qChannel);
        params.put("index",(page-1)*limit);
        params.put("limit",limit);
        params.put("qCreateTime",qCreateTime);
        Long count = documentsService.count(params);
        List<Documents> documentsList = documentsService.page(params);
        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS,"文档查询成功",documentsList, count);
    }

    /**
     * 查看文档详情
     * @param id
     * @return
     */
    @RequestMapping("/details/{id}")
    @ResponseBody
    public Object details(@PathVariable String id) {
        Documents documents = documentsService.details(id);
        if (documents != null) {
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "查询详情成功",documents);
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "查询详情失败",null);
        }
    }


    @MyLog(detail = "修改稿件",operationType = OperationType.UPDATE,operationUnit = OperationUnit.DOCUMENTS)
    @RequestMapping("/update/{id}")
    @ResponseBody
    public Object update(@RequestBody Documents documents,@PathVariable String id) {
        if (!StringUtils.isNotBlank(id)) {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "缺少关键参数",null);
        } else {
            documents.setId(Integer.valueOf(id));
            int rownum = documentsService.updateByDocId(documents);
            if (rownum >= 1) {
                return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "修改成功",null);
            } else {
                return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "修改失败",null);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param file
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    @MyLog(detail = "删除稿件",operationType = OperationType.DELETE,operationUnit = OperationUnit.DOCUMENTS)
    public static boolean deleteFile(File file ) {
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件成功！");
                return true;
            } else {
                System.out.println("删除单个文件失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：不存在！");
            return false;
        }
    }

    @RequestMapping("/export")
    @ResponseBody
    public Object export(HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        String docIds = request.getParameter("docIds");
        Boolean isExport = documentsService.export(docIds,response);
        if (isExport) {
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "导出成功",null);
        } else {
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "导出失败",null);
        }
    }

    @MyLog(detail = "删除选中稿件",operationType = OperationType.DELETE,operationUnit = OperationUnit.DOCUMENTS)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam(value="docIds[]") List<Integer> docIds){
        if (!documentsService.deleteByIds(docIds))
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL, "系统异常", null);
        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS, "用户删除成功", null);
    }

    @RequestMapping(value = "/download")
    @ResponseBody
    public Object download(@RequestParam(value="path") String path){
        return null;
    }

    @RequestMapping("/getChannel")
    @ResponseBody
    public Object getChannel() {
        return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS,"查询成功",documentsService.getChannel(),documentsService.getChannel().size());
    }

    @RequestMapping("/findReview")
    @ResponseBody
    public Object findDocToReview() {
        try {
            String num = documentsService.findDocToReview();
            return RespUtil.getResp(RespUtil.RESP_CODE_SUCCESS,"查询成功",num);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.getResp(RespUtil.RESP_CODE_FAIL,"查询成功",null);
        }

    }
}
