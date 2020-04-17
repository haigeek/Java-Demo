package com.haigeek.file;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhaohj
 * @date 2020-04-15 10:26 下午
 */
public class ZipFileUtil {
    /**
     * 压缩单个文件
     * @param compressFilePath 压缩后文件路径
     * @param originFile 源文件
     */
    public void compressFile(String compressFilePath, File originFile) throws ZipException {
        new ZipFile(compressFilePath).addFile(originFile);
    }

    public void compressMultipleFiles(String compressFilePath, List<File> originFiles) throws ZipException {
        new ZipFile(compressFilePath).addFiles(originFiles);
    }

    public void compressFolder(String compressFilePath,File folderPath ) throws ZipException {
        new ZipFile(compressFilePath).addFolder(folderPath);
    }

    public void compressStream(String compressFilePath, InputStream inputStream) throws ZipException {
        new ZipFile(compressFilePath).addStream(inputStream, new ZipParameters());
    }

    public void compressFileWithPass(String compressFilePath,String passWord,List<File> originFiles ) throws ZipException {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        // Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        ZipFile zipFile = new ZipFile(compressFilePath, passWord.toCharArray());
        zipFile.addFiles(originFiles, zipParameters);
    }

    public void compressFolderWithPass(String compressFilePath,String passWord,File originFolder ) throws ZipException {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        // Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        ZipFile zipFile = new ZipFile(compressFilePath, passWord.toCharArray());
        zipFile.addFolder(originFolder, zipParameters);
    }

}
