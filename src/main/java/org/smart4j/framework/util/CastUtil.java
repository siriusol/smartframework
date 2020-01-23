package org.smart4j.framework.util;

/**
 * 转型操作工具类
 * @author Ther
 */
public final class CastUtil {

    /**
     * 转为 int
     */
    public static int castInt(Object object) {
        return CastUtil.castInt(object, 0);
    }

    /**
     * 转为 int（指定默认值）
     */
    public static int castInt(Object object, int defaultValue) {
        int value = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为 double
     */
    public static double castDouble(Object object) {
        return CastUtil.castDouble(object, 0);
    }

    /**
     * 转为 double（指定默认值）
     */
    public static double castDouble(Object object, double defaultValue) {
        double value = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为 long
     */
    public static long castLong(Object object) {
        return CastUtil.castLong(object, 0);
    }

    /**
     * 转为 long（指定默认值）
     */
    public static long castLong(Object object, long defaultValue) {
        long value = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为 boolean
     */
    public static boolean castBoolean(Object object) {
        return CastUtil.castBoolean(object, false);
    }

    /**
     * 转为 boolean（指定默认值）
     */
    public static boolean castBoolean(Object object, boolean defaultValue) {
        boolean value = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    value = Boolean.parseBoolean(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为 String
     */
    public static String castString(Object object) {
        return CastUtil.castString(object, "");
    }

    /**
     * 转为 String（指定默认值）
     */
    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }
}
