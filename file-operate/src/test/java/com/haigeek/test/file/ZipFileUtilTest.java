package com.haigeek.test.file;

import com.haigeek.file.ZipFileUtil;
import net.lingala.zip4j.exception.ZipException;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author zhaohj
 * @date 2020-04-15 10:44 下午
 */

public class ZipFileUtilTest {
    @Test
    public void compressFile() throws ZipException, UnsupportedEncodingException {
        ZipFileUtil zipFileUtil=new ZipFileUtil();
        File originFile=new File(URLDecoder.decode(ZipFileUtilTest.class.getClassLoader().getResource("这是一个测试文件.pdf").getFile(), StandardCharsets.UTF_8.name()));
        zipFileUtil.compressFile("测试.zip",originFile);
    }

    @Test
    public void compressFileWithPass() throws ZipException, UnsupportedEncodingException {
        ZipFileUtil zipFileUtil=new ZipFileUtil();
        File originFile=new File(URLDecoder.decode(ZipFileUtilTest.class.getClassLoader().getResource("这是一个测试文件.pdf").getFile(), StandardCharsets.UTF_8.name()));
        zipFileUtil.compressFileWithPass("测试带密码.zip","password", Arrays.asList(originFile));
    }
}
