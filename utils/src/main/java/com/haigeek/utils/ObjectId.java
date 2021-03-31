package com.haigeek.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaohj
 * @date 2020-06-25 17:42
 */
public class ObjectId {
    /** 线程安全的下一个随机数,每次生成自增+1 */
    private static final AtomicInteger nextInc = new AtomicInteger(RandomUtil.randomInt());
    /** 机器信息 */
    private static final int machine = getMachinePiece() | getProcessPiece();


    /**
     * 给定的字符串是否为有效的ObjectId
     *
     * @param s 字符串
     * @return 是否为有效的ObjectId
     */
    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        s = StrUtil.removeAll(s, "-");
        final int len = s.length();
        if (len != 24) {
            return false;
        }

        char c;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'f') {
                continue;
            }
            if (c >= 'A' && c <= 'F') {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 获取一个objectId的bytes表现形式
     *
     * @return objectId
     * @since 4.1.15
     */
    public static byte[] nextBytes() {
        final ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.putInt((int) DateUtil.currentSeconds());// 4位
        bb.putInt((RandomUtil.randomInt()));// 4位
        bb.putInt(nextInc.getAndIncrement());// 4位

        return bb.array();
    }

    /**
     * 获取一个objectId用下划线分割
     *
     * @return objectId
     */
    public static String next() {
        return next(false);
    }

    /**
     * 获取一个objectId
     *
     * @param withHyphen 是否包含分隔符
     * @return objectId
     */
    public static String next(boolean withHyphen) {
        byte[] array = nextBytes();
        final StringBuilder buf = new StringBuilder(withHyphen ? 26 : 24);
        int t;
        for (int i = 0; i < array.length; i++) {
            if (withHyphen && i % 4 == 0 && i != 0) {
                buf.append("-");
            }
            t = array[i] & 0xff;
            if (t < 16) {
                buf.append('0');
            }
            buf.append(Integer.toHexString(t));

        }
        return buf.toString();
    }

    // ----------------------------------------------------------------------------------------- Private method start
    /**
     * 获取机器码片段
     *
     * @return 机器码片段
     */
    private static int getMachinePiece() {
        // 机器码
        int machinePiece= (RandomUtil.randomInt()) << 16;
        return machinePiece;
    }

    /**
     * 获取进程码片段
     *
     * @return 进程码片段
     */
    private static int getProcessPiece() {
        // 进程码
        // 因为静态变量类加载可能相同,所以要获取进程ID + 加载对象的ID值
        final int processPiece;
        // 进程ID初始化
        int processId= RandomUtil.randomInt();

        final ClassLoader loader = ClassLoaderUtil.getClassLoader();
        // 返回对象哈希码,无论是否重写hashCode方法
        int loaderId = (loader != null) ? System.identityHashCode(loader) : 0;

        // 进程ID + 对象加载ID
        StringBuilder processSb = new StringBuilder();
        processSb.append(Integer.toHexString(processId));
        processSb.append(Integer.toHexString(loaderId));
        // 保留前2位
        processPiece = processSb.toString().hashCode() & 0xFFFF;

        return processPiece;
    }
    // ----------------------------------------------------------------------------------------- Private method end
}
