package com.beatdz.lib;

import com.beatdz.data.ItemOption;
import com.beatdz.real.Entity;
import com.beatdz.real.Item;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Utlis {

    public static Random c = new Random();

    public static void sleep(long paramLong) {
        try {
            Thread.sleep(paramLong);
        } catch (Exception exception) {
        }
    }

    public static int nextInt(int paramInt) {
        return (paramInt <= 0) ? 0 : c.nextInt(paramInt);
    }

    public static int nextInt(int paramInt1, int paramInt2) {
        return (paramInt2 == paramInt1 && paramInt2 == 0) ? 0 : (paramInt1 + nextInt(paramInt2 - paramInt1 + 1));
    }

    public static boolean nextInt() {
        return !(c.nextInt(2) == 0);
    }

    public static int nextInt(int[] paramArrayOfint) {
        return paramArrayOfint[nextInt(3)];
    }

    public static int getRange(int paramInt) {
        return (paramInt > 0) ? paramInt : -paramInt;
    }

    public static Entity a(Entity var0, Entity var1, Entity var2, Entity var3) {
        Entity var4;
        return (var4 = b(var0, var1, var2, var3)) != null && a(var4.x, var4.y, var0, var1) && a(var4.x, var4.y, var2, var3) ? var4 : null;
    }

    private static boolean a(int var0, int var1, Entity var2, Entity var3) {
        return (var3.x > var2.x && var2.x <= var0 && var0 <= var3.x || var2.x >= var3.x && var2.x >= var0 && var0 >= var3.x) && (var3.y > var2.y && var2.y <= var1 && var1 <= var3.y || var2.y >= var3.y && var2.y >= var1 && var1 >= var3.y);
    }

    private static Entity b(Entity var0, Entity var1, Entity var2, Entity var3) {
        double var4 = (double) var0.x;
        double var6 = (double) var1.x;
        double var8 = (double) var2.x;
        double var10 = (double) var3.x;
        double var12 = (double) var0.y;
        double var14 = (double) var1.y;
        double var16 = (double) var2.y;
        double var18 = (double) var3.y;
        double var20;
        if ((var20 = (var4 - var6) * (var16 - var18) - (var12 - var14) * (var8 - var10)) == 0.0D) {
            return null;
        } else {
            double var22 = ((var8 - var10) * (var4 * var14 - var12 * var6) - (var4 - var6) * (var8 * var18 - var16 * var10)) / var20;
            double var24 = ((var16 - var18) * (var4 * var14 - var12 * var6) - (var12 - var14) * (var8 * var18 - var16 * var10)) / var20;
            return Entity.create((int) var22, (int) var24);
        }
    }

    public static int getRange(int paramInt1, int paramInt2) {
        return getRange(paramInt1 - paramInt2);
    }

    public static int getRangez(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        return (int) Math.sqrt((getRange((paramInt1 - paramInt3) * (paramInt1 - paramInt3)) + getRange((paramInt2 - paramInt4) * (paramInt2 - paramInt4))));
    }

    private static double getRange(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
        return Math.sqrt(Math.abs((paramDouble1 - paramDouble3) * (paramDouble1 - paramDouble3)) + Math.abs((paramDouble2 - paramDouble4) * (paramDouble2 - paramDouble4)));
    }

    public static int getRange(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        paramInt1 = getRange(paramInt1 - paramInt3);
        paramInt2 = getRange(paramInt2 - paramInt4);
        return (paramInt1 > paramInt2) ? paramInt1 : paramInt2;
    }

    public static String formatMoney(String paramString) {
        try {
            Long.parseLong(paramString);
        } catch (Exception exception) {
            return paramString;
        }
        String str1 = "";
        String str2 = "";
        if (paramString.charAt(0) == '-') {
            str2 = "-";
            paramString = paramString.substring(1);
        }
        for (int i = paramString.length() - 1; i >= 0; i--) {
            if ((paramString.length() - 1 - i) % 3 == 0 && paramString.length() - 1 - i > 0) {
                str1 = paramString.charAt(i) + "." + str1;
            } else {
                str1 = paramString.charAt(i) + str1;
            }
        }
        return str2 + str1;
    }

    public static String formatMoney(long paramLong) {
        return formatMoney(String.valueOf(paramLong));
    }

    public static String formatMoney(int paramInt) {
        return formatMoney(String.valueOf(paramInt));
    }

    public static String[] split(String paramString1, String paramString2) {
        Vector<String> vector = new Vector();
        for (int i = paramString1.indexOf(paramString2); i >= 0; i = (paramString1 = paramString1.substring(i + paramString2.length())).indexOf(paramString2)) {
            vector.addElement(paramString1.substring(0, i));
        }
        vector.addElement(paramString1);
        String[] arrayOfString = new String[vector.size()];
        vector.copyInto((Object[]) arrayOfString);
        return arrayOfString;
    }

    public static String replace(String paramString1, String paramString2) {
        return paramString1.replaceAll("#", paramString2);
    }

    public static String replace(String paramString, long paramLong) {
        return paramString.replaceAll("#", String.valueOf(paramLong));
    }

    public static String nextUTF(int paramInt) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder(paramInt);
        for (byte b = 0; b < paramInt; b++) {
            stringBuilder.append(str.charAt(nextInt(str.length())));
        }
        return stringBuilder.toString();
    }

    public static byte[] inflateByteArray2(byte[] var0) {
        Inflater var2;
        (var2 = new Inflater()).setInput(var0);
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        byte[] var3 = new byte[4096];

        try {
            while (!var2.finished()) {
                int var4 = var2.inflate(var3);
                var1.write(var3, 0, var4);
            }

            byte[] var13 = var1.toByteArray();
            return var13;
        } catch (Exception var11) {
        } finally {
            try {
                var1.close();
            } catch (Exception var10) {
            }

        }

        return var0;
    }

    public static byte[] inflateByteArray(byte[] paramArrayOfbyte) {
        Inflater inflater;
        (inflater = new Inflater()).setInput(paramArrayOfbyte);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte[1024];
        try {
            while (!inflater.finished()) {
                int i = inflater.inflate(arrayOfByte);
                byteArrayOutputStream.write(arrayOfByte, 0, i);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception exception) {
            try {
                byteArrayOutputStream.close();
            } catch (Exception exception1) {
            }
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (Exception exception) {
            }
        }
        return paramArrayOfbyte;
    }

    public static byte[] deflateByteArray(byte[] var1) {
        ByteArrayOutputStream var2 = new ByteArrayOutputStream();
        Deflater var4;
        (var4 = new Deflater()).setLevel(9);
        var4.setInput(var1);
        var4.finish();
        try {
            //var2.write(var1, 0, 4);
            byte[] var5 = new byte[4096];
            while (!var4.finished()) {
                var2.write(var5, 0, var4.deflate(var5));
            }
            return var2.toByteArray();
        } catch (Exception exception) {
            try {
                var4.end();
            } catch (Exception exception1) {
            }
        } finally {
            try {
                var4.end();
            } catch (Exception exception) {
            }
        }
        return var1;
    }

    public static void write(String paramString, byte[] paramArrayOfbyte) {
        try {
            FileOutputStream fileOutputStream;
            (fileOutputStream = new FileOutputStream(paramString)).write(paramArrayOfbyte);
            fileOutputStream.close();
            return;
        } catch (Exception exception) {
            return;
        } finally {
        }
    }

    public static int readLength(String paramString) {
        byte[] arrayOfByte;
        return ((arrayOfByte = read(paramString)) != null) ? arrayOfByte.length : 0;
    }

    public static byte[] read(String paramString) {
        FileInputStream fileInputStream = null;
        try {
            File file;
            if (!(file = new File(paramString)).exists()) {
                return null;
            }
            fileInputStream = new FileInputStream(file);
            byte[] arrayOfByte = new byte[(int) file.length()];
            fileInputStream.read(arrayOfByte, 0, arrayOfByte.length);
            arrayOfByte = arrayOfByte;
            return arrayOfByte;
        } catch (Exception exception) {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception exception1) {
            }
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception exception) {
            }
        }
        return null;
    }

    public static int getRange(Entity entity1, Entity entity2) {
        return getRange(entity1.x, entity1.y, entity2.x, entity2.y);
    }

    public static int[] getXYFake(int var1, int var2, int J) {
        int var3 = J / 255;
        if (J % 255 != 0) {
            ++var3;
        }
        int byte1 = var2 / 255 * var3 + var1 / 255;
        int byte2 = var1 % 255;
        int byte3 = var2 % 255;
        return new int[]{byte1, byte2, byte3};
    }

    public static String j(int var0) {
        return var0 % 1000 == 0 ? formatMoney(String.valueOf(var0 /= 1000)) : "" + (float) var0 / 1000.0F;
    }

    public static int[] getXYReal(int byte1, int byte2, int byte3, int var3) {
        //System.out.println("getXYReal() = ["+byte1+","+byte2+","+byte3+","+var3+"]");
        int var3z = var3 / 255;
        if (var3z % 255 != 0) {
            ++var3z;
        }
        return new int[]{byte2 + byte1 % var3z * 255, byte3 + byte1 / var3z * 255};
//        for (int i = 0; i <= var3; i++) {
//            if (i % 255 == byte2) {
//                for (int j = 0; j <= var3; j++) {
//                    if (j % 255 == byte3) {
//                        if ((j / 255) * var3z + (i / 255) == byte1) {
//                            return new int[]{i, j};
//                        }
//                    }
//                }
//
//            }
//        }
    }

    public static String valueOf(String[] arrayText, String string) {
        String str = "";
        for (int i = 0; i < arrayText.length; i++) {
            str += arrayText[i];
            if (i < arrayText.length - 1) {
                str += string;
            }
        }
        return str;
    }

    public static int getIntItemOption(ItemOption[] arrayItemOption, int id) {
        if (arrayItemOption == null) {
            return 0;
        }
        for (int i = 0; i < arrayItemOption.length; i++) {
            if (arrayItemOption[i].b[0] == id) {
                return arrayItemOption[i].b[1];
            }
        }
        return 0;

    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static ArrayList<Item> cleanArrayItem(Item[] array) {
        ArrayList<Item> item = new ArrayList<Item>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                item.add(array[i]);
            }
        }

        return item;
    }

    public static String getSTR(String[] string, String string0) {
        String str = "";

        for (int i = 0; i < string.length; i++) {
            str += string[i];
            if (i < string.length - 1) {
                str += string0;
            }
        }

        return str;
    }

}
