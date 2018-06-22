/*
 * Powered By [chan]
 * Web Site: http://wealthlake.cn
 * Since 2012 - 2017
 */

package com.liuhe.redpacket.web.oms;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.liuhe.redpacket.domain.QrCode;
import com.liuhe.redpacket.exception.LogicException;
import com.liuhe.redpacket.query.PageResult;
import com.liuhe.redpacket.query.QrCodeQuery;
import com.liuhe.redpacket.service.IQrCodeService;
import com.liuhe.redpacket.system.MethodAnnotation;
import com.liuhe.redpacket.utils.FileUtil;
import com.liuhe.redpacket.utils.MatrixToImageWriter;
import com.liuhe.redpacket.utils.result.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/qrCode")
public class QrCodeController {

    @Autowired
    IQrCodeService qrCodeService;

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/index")
    private String QrCode() {
        return "qrCode/qrCode";
    }

    /**
     * 高级查询+分页
     *
     * @param qu
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    private PageResult<QrCode> query(QrCodeQuery qu) {
        PageResult<QrCode> list = qrCodeService.query(qu);
        return list;
    }

    /**
     * 添加
     *
     * @param num
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    private AjaxResult save(int num) {
        AjaxResult ar;
        try {
            List<QrCode> qrCodes = qrCodeService.save(num);
            StringBuilder ids = new StringBuilder();
            for (QrCode qrCode : qrCodes) {
                ids = ids.append(qrCode.getId()).append(",");
            }
            ar = new AjaxResult(ids);
        } catch (LogicException e) {
            ar = new AjaxResult(e.getMessage(), e.getErrorCode());
        } catch (InterruptedException e) {
            ar = new AjaxResult("系统异常", 00);
        }
        return ar;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    private AjaxResult delete(@RequestParam("ids") String ids) {
        AjaxResult ar;
        try {
            String[] idList = ids.split(",");
            if (idList.length == 0)return new AjaxResult();
            for (String id : idList) {
                if (StringUtils.isNotBlank(id)) {
                     qrCodeService.delete(Long.valueOf(id));
                }
            }
            ar = new AjaxResult();
        } catch (LogicException e) {
            ar = new AjaxResult(e.getMessage(), e.getErrorCode());
        }
        return ar;
    }

    @RequestMapping("download")
    public void download(HttpServletRequest request, HttpServletResponse response, String ids) throws IOException {
        String[] idList = ids.split(",");
        if (idList.length == 0)return;

        OutputStream out = response.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框
        //根据临时的zip压缩包路径，创建zip文件
        String basePath = request.getSession().getServletContext().getRealPath("/");
        String zipFilePath = basePath+"temp/temp.zip";
        File zip = new File(zipFilePath);
        if (!zip.getParentFile().exists()) {
            zip.getParentFile().mkdirs();
        }
        if (!zip.exists()){
            zip.createNewFile();
        }

        //创建zip文件输出流
        FileOutputStream fos = new FileOutputStream(zip);
        ZipOutputStream zos = new ZipOutputStream(fos);
        boolean flag = false;//是否有图片
        for (String id : idList) {
            if (StringUtils.isNotBlank(id)) {
                QrCode qrCode = qrCodeService.get(Long.valueOf(id));
                if (qrCode != null){
                    File file = new File(basePath+qrCode.getUrl());
                    boolean b = zipFile(file, zos);
                    if(b){
                        flag = b;
                    }
                }
            }
        }
        zos.close();
        fos.close();
        if (!flag)return;
        //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出
        InputStream fis = new BufferedInputStream(new FileInputStream(zipFilePath));
        byte[] buff = new byte[4096];
        int size;
        while((size=fis.read(buff)) != -1){
            out.write(buff, 0, size);
        }
    }

    //封装压缩文件的方法
    public  boolean zipFile(File inputFile,ZipOutputStream zipoutputStream) {
        try {
            if(inputFile.exists()) { //判断文件是否存在
                if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹

                    //创建输入流读取文件
                    FileInputStream fis = new FileInputStream(inputFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    //将文件写入zip内，即将文件进行打包
                    ZipEntry ze = new ZipEntry(inputFile.getName()); //获取文件名
                    zipoutputStream.putNextEntry(ze);

                    //写入文件的方法，同上
                    byte [] b=new byte[1024];
                    long l=0;
                    while(l<inputFile.length()){
                        int j=bis.read(b,0,1024);
                        l+=j;
                        zipoutputStream.write(b,0,j);
                    }
                    //关闭输入输出流
                    bis.close();
                    fis.close();
                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], zipoutputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
