package com.firststringboot.common.utils;

import com.firststringboot.common.Exception.CustomException;
import com.firststringboot.common.model.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUtil {


    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static boolean isCreate = false;

    private static final String Local_Path = System.getProperty("java.io.tmpdir") + File.separator;

    /**
     * 文件保存在本地服务器
     *
     * @param file
     * @return
     */
    private static String saveToLoacl(MultipartFile file) {

        //获取原文件名
        String fileName = file.getOriginalFilename();

        if (StringUtils.isEmpty(file)) throw new CustomException("无文件名！");

        //获取原文件名的后缀名
        String suffixFileName = fileName.substring(fileName.lastIndexOf(".") + 1);

        //生成新的上传文件名
        String uploadName = String.format("%s%s", UUID.randomUUID().toString(), suffixFileName);

        //判断上传路径是否存在  不存在则创建
        if (tempPathIsExistAndAutoCreate(getLocalStorePath())) throw new CustomException("创建文件夹失败！");

        logger.warn("开始上传文件，上传文件名：" + fileName + ",上传的路径：" + getLocalStorePath() + "上传的新文件名：" + uploadName);

        //生成上传的保存路径
        File saveToPath = new File(getLocalStorePath() + File.separator + uploadName);

        try {
            //上传文件
            file.transferTo(saveToPath);
        } catch (Exception e) {
            throw new CustomException("保存上传的文件到临时目录失败！" + e.getMessage());
        }
        return uploadName;
    }

    /**
     * 文件保存在ftp服务器  并删除本地文件
     *
     * @param fileName
     * @param remotePath
     * @param isAutoDel
     * @return
     * @throws Exception
     */
    public static ResultModel saveToFTPServer(String fileName, String remotePath, boolean isAutoDel) throws Exception {

        //获取到上传的文件
        File localFile = getLocalFile(fileName);

        //判断上传的目录是否存在
        if (!localFile.exists()) throw new CustomException("找不到临时目录中的文件");

        //上传文件
        FTPUtil.uploadFile(fileName, remotePath);

        //是否上传后自动删除
        if (isAutoDel) if (!localFile.delete()) logger.warn("删除本地文件失败！");

        return ResultModel.getSuccessResultModel(String.format("%s%s", remotePath, fileName));
    }


    public static File getLocalFile(String fileName) {
        return new File(getLocalStorePath() + fileName);
    }


    public static String getLocalStorePath() {
        if (!isCreate)
            isCreate = tempPathIsExistAndAutoCreate(Local_Path);
        return Local_Path;
    }


    public static boolean tempPathIsExistAndAutoCreate(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }
}
