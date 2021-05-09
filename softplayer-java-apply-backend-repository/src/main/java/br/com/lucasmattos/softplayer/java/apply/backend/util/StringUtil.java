package br.com.lucasmattos.softplayer.java.apply.backend.util;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringUtil {
    public static boolean isNullEmpty(String str){
        if (str == null) return true;
        if (str.isEmpty()) return true;
        return false;
    }

}
