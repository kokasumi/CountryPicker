package mnilg.com.countrypicker.utills;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 * /**
 * Author：Liu Yang
 * Date: 2015/10/26 17:15
 * Email：yang.liu@mengqitech.com
 */
public class StringUtils {
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断是否为电话号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 检测input是否是字母
     *
     * @param input
     * @return
     */
    public static boolean isCharacterLetter(String input) {
        return input.matches("[A-Za-z]");
    }

    /*
     * 验证密码(必须包含字母和数字)
     */
    public static boolean checkPassword(String password) {
        String regex = ".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]";
        return Pattern.matches(regex, password);
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;
    }

    /**
     * 将view转换成bitmap
     *
     * @param view
     * @param bitmapWidth
     * @param bitmapHeight
     * @return
     */
    public static Bitmap convertViewToBitmap(View view, int bitmapWidth, int bitmapHeight) {
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return bitmap;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 获取指定字符串的排序字母(即第一个字母)
     *
     * @param str
     * @return
     */
    public static String getSortLetter(String str) {
        if(str == null || TextUtils.isEmpty(str))
            return "#";
        CharacterParser characterParser = CharacterParser.getInstance();
        //汉字转换成拼音
        String pinyin = characterParser.getSelling(str);
        String sortString = pinyin.substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            sortString = sortString.toUpperCase();
        } else {
            sortString = "#";
        }
        return sortString;
    }

    /**
     * 汉字转拼音
     * @param str
     * @return
     */
    public static String getPinYin(String str) {
        if(str == null)
            return "";
        CharacterParser characterParser = CharacterParser.getInstance();
        //汉字转换成拼音
        return characterParser.getSelling(str);
    }

    /**
     * 比较判断两个String是否是一样的值
     *
     * @param string1
     * @param string2
     * @return true / false
     */
    public static boolean isStringEqualToString(String string1, String string2) {
        return isEmpty(string1) && isEmpty(string2) || !(isEmpty(string1) || isEmpty(string2)) && string1.equalsIgnoreCase(string2);
    }
}
