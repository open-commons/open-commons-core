/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package open.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>
 * 
 * <pre>
 * <b>변환 대상 ProC:</b><font color="RED">
 * - RG99OL9983.pc의 데이타 포맷팅 함수들
 * </pre>
 * 
 * </font>
 * </p>
 * 
 * <b>[클래스 개요]</b>
 * <p>
 * 
 * <p>
 * <b>NOTE:</b> [설명 및 주의사항]<br>
 * 
 * </p>
 * 
 * @stereotype [CTRL, SC, DAO, DAOVO]
 * @daoType [EM, QM] *DAO만 적용*
 * @author Park Jun-Hong
 * @since 2011. 10. 13.
 * @see
 * 
 *      <pre>
 * == 개정이력(Modification Information) ==
 * 
 * 수정일                		수정자 	 			수정내용
 * ------------		---------------		---------------------------
 * 2011. 10. 13.      Park Jun-Hong	           최초 생성
 *      </pre>
 */
public class FormatUtils {
    public static final String REGEX_TIME_HHMMSS = "^(\\d{2})(\\d{2})(\\d{2}).*$";

    public static final String DATE_FORMAT_NUMERIC = "yyyyMMddHHmmss";

    private static final Map<String, SimpleDateFormat> formats = new HashMap<String, SimpleDateFormat>();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_NUMERIC);

    static {
        formats.put(DATE_FORMAT_NUMERIC, DATE_FORMAT);
    }

    private FormatUtils() {
    }

    /**
     * 포맷에 포함된 길이에 맞도록 내용을 줄임표시를 해서 반환한다.
     * 
     * @param format
     * @param arguments
     * @return
     */
    public static String format(String format, Object... arguments) {

        boolean successing = false;

        String[] argArr = new String[arguments.length];

        // arguments index
        int argi = 0;
        int argl = 0;

        // length in format
        String lif = "";

        int sp = -1;
        int ep = -1;

        for (int i = 0; i < arguments.length; i++) {
            argArr[i] = arguments[i] != null ? arguments[i].toString() : "null";
        }

        while ((sp = format.indexOf('%', ep + 1)) != -1) {

            ep = indexOf(format, sp + 1, 'd', 'f', 's');

            if (ep == -1) {
                successing |= false;
                break;
            }

            lif = format.substring(sp + 1, ep);
            if (!lif.isEmpty()) {
                // arg = argArr[argi];
                argl = argArr[argi].length();

                if (lif.charAt(0) == '-') {
                    lif = lif.substring(1);
                }

                int l = Integer.parseInt(lif);

                if (l < 3) {
                    l = 8;
                }

                if (argl > 3 && argl > l) {
                    argArr[argi] = argArr[argi].substring(0, l - 3) + "...";
                }
            }

            argi++;
            successing = true;
        }

        format = format.replaceAll("(%\\-?\\d*\\.?\\d*)[d|f|c]", "$1s");

        if (successing) {
            return String.format(format, (Object[]) argArr);
        } else {
            return format;
        }
    }

    /**
     * 
     * <b>[메소드 개요]</b>
     * <p>
     * 
     * </p>
     * 
     * @param format
     *            표현 포맷
     * @param string
     *            표현될 문자열
     * @param ints
     *            나누어질 인덱스 값. 시작과 끝 인덱스는 exclusuve
     * @return
     * 
     * 
     *         <br>
     * 
     *         <pre>
     * == 개정이력(Modification Information) ==
     * 
     * 수정일                		수정자 	 			수정내용
     * ------------		---------------		---------------------------
     * 2011. 09. 27.      Park Jun-Hong	           최초 생성
     *         </pre>
     */
    public static String format(String format, String string, int... ints) {
        String[] strings = new String[ints.length + 1];

        int[] indice = new int[ints.length + 1];
        System.arraycopy(ints, 0, indice, 1, ints.length);

        int i = 0;
        for (; i < indice.length - 1; i++) {
            strings[i] = string.substring(indice[i], indice[i + 1]);
        }

        strings[i] = string.substring(indice[i]);

        return String.format(format, (Object[]) strings);
    }

    private static String format0(String string, String regex, String result) {
        try {
            if (string.matches(regex)) {
                return string.replaceAll(regex, result);
            } else {
                return string;
            }
        } catch (Exception e) {
            return string;
        }
    }

    /**
     * 세자리로 끊어서 콤마(,)로 구분할 때 추가되는 콤마의 개수를 반환한다.
     * 
     * @param digit
     * @return <BR>
     * @since 2012. 3. 8.
     * @author Park Jun-Hong (parkjunhong77@gmail.com)
     */
    public static int getCommaCount(String digit) {
        char[] digitSrc = digit.toCharArray();

        // 소스 전체 길이
        int length = digitSrc.length;
        // 숫자 길이
        int digitLength = -1;

        if (digitSrc[0] == '-') {
            digitLength = length - 1;
            return digitLength / 3 + (digitLength % 3 != 0 ? 1 : 0) - 1;
        } else {
            return length / 3 + (length % 3 != 0 ? 1 : 0) - 1;
        }
    }

    /**
     * {@link #DATE_FORMAT_NUMERIC}를 사용하여 현재 시간을 포맷팅하여 반환한다.
     * 
     * @return
     * 
     */
    public static final String getCurrentTimestamp() {
        return DATE_FORMAT.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 주어진 포맷을 사용하여 현재 시간을 포맷팅하여 반환한다.
     * 
     * @param format
     * @return
     */
    public static final String getTimestamp(String format) {
        SimpleDateFormat sdf = formats.get(format);

        if (sdf == null) {
            sdf = new SimpleDateFormat(format);

            formats.put(format, sdf);
        }

        return sdf.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 주어진 문자열에서 찾고자하는 문자들 중 처음 발생하는 위치를 반환한다.
     * 
     * @param string
     * @param fromIndex
     *            시작 위치
     * @param cs
     *            찾고자 하는 문자
     * @return 찾고자하는 문자가 없는 경우 -1.
     */
    private static int indexOf(String string, int fromIndex, char... cs) {

        if (string == null) {
            return -1;
        }

        int index = Integer.MAX_VALUE;
        int tmp = -1;

        for (char c : cs) {
            if ((tmp = string.indexOf(c, fromIndex)) != -1) {
                if (tmp < index) {
                    index = tmp;
                }
            }
        }

        return index != Integer.MAX_VALUE ? index : -1;
    }

    public static void main(String[] args) {
        System.out.println("1234567890".replaceAll("(\\d+)(\\d{3})", "$1,$2"));
    }

    /**
     * 예) {@code 184512 -> 18:45:12}
     * 
     * @param time
     *            시간 정보가 있는 6자리 문자열
     * @return ##:##:## 포맷을 만족하는 8자리 문자열
     */
    public static String timeForm(String time) {
        return format0(time, REGEX_TIME_HHMMSS, "$1:$2:$3");
    }

    /**
     * e.g. {@code 18:45:12 -> 184512}
     * 
     * @param timeform
     * @return
     */
    public static String timeFormtoString(String timeform) {
        return timeform.replaceAll(":", "");
    }

    public static String toThousandExpr(byte digit) {
        return toThousandExpr(Byte.toString(digit));
    }

    public static String toThousandExpr(char[] digit) {
        return toThousandExpr(String.valueOf(digit));
    }

    public static String toThousandExpr(int digit) {
        return toThousandExpr(Integer.toString(digit));
    }

    public static String toThousandExpr(long digit) {
        return toThousandExpr(Long.toString(digit));
    }

    public static String toThousandExpr(String digit) {

        char[] digitSrc = digit.toCharArray();

        // 소스 전체 길이
        int length = digitSrc.length;
        // 숫자 길이
        int digitLength = -1;

        // 콤마 추가 mod 값
        int commaIndex = -1;

        // 소스 캐릭터 시작 인덱스
        int dsIndex = 0;
        // 결과 캐릭터 시작 인덱스
        int rstIndex = 0;

        char[] result = null;
        if (digitSrc[0] == '-') {
            digitLength = length - 1;

            result = new char[length + digitLength / 3 + (digitLength % 3 != 0 ? 1 : 0) - 1];
            commaIndex = digitLength % 3;

            dsIndex = 1;
            rstIndex = 1;

            result[0] = '-';

        } else {
            digitLength = length;

            result = new char[length + length / 3 + (length % 3 != 0 ? 1 : 0) - 1];
            commaIndex = (length - 1) % 3;
        }

        for (; dsIndex < digitSrc.length - 1; dsIndex++, rstIndex++) {
            result[rstIndex] = digitSrc[dsIndex];

            if (dsIndex % 3 == commaIndex) {
                rstIndex++;
                result[rstIndex] = ',';
            }
        }

        result[rstIndex] = digitSrc[dsIndex];

        return new String(result);
    }

}