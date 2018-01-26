package yeqy.cave.scheme.paser;

import yeqy.cave.scheme.analyze.Constant;

public class TokenPaser {
    public static String[] parseToken(String code) {
        return code.replaceAll("\\(", "( ").replaceAll("\\)", " ) ").split("\\s+");
    }

    public static int nextBraces(String[] tokens, int count, int begin) {
//        if (braces != Constant.left_braces && braces != Constant.right_braces)
//            throw new RuntimeException("param braces is not scheme braces!");
        int left = 0;
        int right = 0;
//        for (int i = begin, j = 0; i < tokens.length; i++) {
//            String token = tokens[i];
//            if (Constant.right_braces.getToken().equals(token)) {
//                if (++j == count + 1) {
//                    return i;
//                }
//            } else if (Constant.left_braces.getToken().equals(token)) {
//                j--;
//            }
//        }
        for (int i = begin + 1; i < tokens.length; i++) {
            String token = tokens[i];
            if (Constant.right_braces.getToken().equals(token)) {
                if (++right > left)
                    return i;
            } else if (Constant.left_braces.getToken().equals(token)) {
                left++;
            }
        }
        return -1;
    }

}
