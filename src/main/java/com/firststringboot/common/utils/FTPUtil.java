package com.firststringboot.common.utils;

import com.firststringboot.common.Exception.CustomException;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String configFtpServer = "62.234.110.25";
    private static String configFtpUser = "test_file";
    private static String configFtpPassword = "123456";
    private static String configFtpConnectPort = "21";

    private FTPClient ftpClient;

    public void openConnect() throws Exception {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(configFtpServer);
            ftpClient.login(configFtpUser, configFtpPassword);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            logger.warn("FTP 登陆成功");
        } catch (Exception e) {
            throw new Exception("FTP 登陆失败：[" + e.getMessage() + "]");
        }
    }

    private void closeConnect() throws Exception {
        try {
            ftpClient.disconnect();
        } catch (Exception e) {
            logger.error("FTP 断开连接失败：[" + e.getMessage() + "]");
        }
    }


    private boolean upload(String filePath,String remotePath) {
        InputStream inputStream = null;
        File file = new File(filePath);
        try {
            inputStream = new FileInputStream(file);
            getUploadPath(remotePath);
            boolean uploaded = ftpClient.storeFile(new String(file.getName().getBytes(), "iso-8859-1"), inputStream);
            logger.info(String.format("上传%s文件到FTP%s...", file.getName(), uploaded ? "成功" : "失败"));
        } catch (Exception e) {

        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                logger.error("FTP 上传文件输出流关闭失败：[" + e.getMessage() + "]");
            }
        }
        return true;
    }

    private void getUploadPath(String path) throws Exception {
        String[] paths = path.split("/");
        if (paths.length <= 0) throw new CustomException("找不到上传路径");
        for (String str : paths) {
            if (str != null && !str.trim().equals("")) {
                if (ftpClient.changeWorkingDirectory(str)) continue;
                if (!ftpClient.makeDirectory(str)) throw new CustomException(String.format("切换到%s目录失败", path));
                if (!ftpClient.changeWorkingDirectory(str)) throw new CustomException(String.format("切换到%s目录失败", path));
            }
        }
    }

    private void downloadFile(String pathname, String filename, String localpath) throws Exception {
        if (!ftpClient.changeWorkingDirectory(pathname)) throw new Exception("切换ftp目录失败！");
        FileOutputStream outputStream = new FileOutputStream(String.format("%s/%s", localpath, filename));

        if (!ftpClient.retrieveFile(new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), outputStream))
            throw new Exception(ftpClient.getReplyString());
        outputStream.flush();
        outputStream.close();
    }


    public static void uploadFile(String filePath,String remotePath) throws Exception {
        FTPUtil ftpUtil = new FTPUtil();
        logger.warn("<===========打开ftp连接中===========>");
        ftpUtil.openConnect();
        logger.warn("<===========开始上传文件===========>");
        ftpUtil.upload(filePath,remotePath);
        logger.warn("<===========文件上传完成  关闭连接中===========>");
        ftpUtil.closeConnect();
    }

    public static void download(String pathname, String filename, String localpath) throws Exception {
        FTPUtil ftpUtil = new FTPUtil();
        logger.warn("<===========打开ftp连接中===========>");
        ftpUtil.openConnect();
        logger.warn("<===========开始下载文件===========>");
        ftpUtil.downloadFile(pathname, filename, localpath);
        logger.warn("<===========文件下载完成  关闭连接中===========>");
        ftpUtil.closeConnect();
    }

}
