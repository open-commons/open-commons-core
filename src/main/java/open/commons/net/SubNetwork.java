/*
 * Copyright 2011 Park Jun-Hong (parkjunhong77/google/com)
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

/*
*
* This file is generated under this project, "open-commons-core".
*
* Date  : 2015. 4. 15. 오후 3:32:20
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubNetwork {

    private static final String REGEX_IPV4 = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."//
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." //
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." //
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    private static final String REGEX_IPV4_STRICT = "^" + REGEX_IPV4 + "$";
    private static final String CIDR_NOTATION = REGEX_IPV4 + "\\s*/\\s*([1-9]|[1-2][0-9]|3[0-2])\\s*";

    protected static final String TO_STRING_FORMAT = "%-10s: %-35s / %s";
    private Pattern patternIPv4 = Pattern.compile(REGEX_IPV4);
    private Pattern patternIPv4Strict = Pattern.compile(REGEX_IPV4_STRICT);

    private Pattern patternCidrNotation = Pattern.compile(CIDR_NOTATION);

    private String ipv4;
    private String netmask;
    private int netmaskInt;
    private String network;
    private String broadcast;

    private int ipBin;
    private int netmaskBin;
    private int networkBin;
    private int broadcastBin;

    private int hostPartBin;

    /**
     * Constructor that takes a CIDR-notation string, e.g. "192.168.0.1/16"
     * 
     * @param cidrNotation
     *            A CIDR-notation string, e.g. "192.168.0.1/16"
     * @throws IllegalArgumentException
     *             if the parameter is invalid, i.e. does not match n.n.n.n/m where n=1-3 decimal digits, m = 1-3
     *             decimal digits in range 1-32
     */
    public SubNetwork(String cidrNotation) throws IllegalArgumentException {

        Matcher m = patternCidrNotation.matcher(cidrNotation);
        if (m.matches()) {
            calculate(dotValueToIPv4Expr(m.group(1), m.group(2), m.group(3), m.group(4)), Integer.parseInt(m.group(5)));
        } else {
            throw new IllegalArgumentException("Invalid CIDR notation. arg: " + cidrNotation);
        }
    }

    /**
     * 
     * @param ipv4
     * @param subnetMask
     * @throws NullPointerException
     *             Thrown if an argument is null.
     * @throws IllegalArgumentException
     *             Thrown if an argument is not matched to {@link #REGEX_IPV4} or CIDR is not valid.
     * @since Apr 13, 2015
     */
    public SubNetwork(String ipv4, int subnetMask) throws NullPointerException, IllegalArgumentException {
        calculate(ipv4, subnetMask);
    }

    /**
     * 
     * @param ipv4Address
     *            An IP address, e.g. "192.168.0.1"
     * @param mask
     *            A dotted decimal netmask e.g. "255.255.0.0"
     * @throws NullPointerException
     *             Thrown if an argument is null.
     * @throws IllegalArgumentException
     *             if the address or mask is invalid, i.e. does not match n.n.n.n where n=1-3 decimal digits and the
     *             mask is not all zeros
     * 
     * @since Apr 13, 2015
     */
    public SubNetwork(String ipv4Address, String mask) {
        calculate(ipv4Address, dottedDecimalNetmaskToIntValue(mask));
    }

    private String binValueToIPv4Expr(int intValue) {
        return dotValueToIPv4Expr(intValue >>> 24, (intValue << 8) >>> 24, (intValue << 16) >>> 24, (intValue << 24) >>> 24);
    }

    /**
     * 
     * @param ipv4
     * @param netmask
     * @throws IllegalArgumentException
     *             Thrown if an argument is not matched to {@link #REGEX_IPV4} or CIDR is not valid.
     *
     * @since Apr 15, 2015
     */
    private void calculate(String ipv4, int netmask) throws IllegalArgumentException {
        if (netmask < 1 || netmask > 31) {
            throw new IllegalArgumentException("Illegal Subnet Mask. Subnet mask: " + netmask);
        }

        this.ipv4 = ipv4;

        Matcher m = patternIPv4.matcher(ipv4);
        if (m.matches()) {
            this.ipBin = ipv4ToBinValue(ipv4);

            this.netmask = maskToIPv4Expr(netmask);
            this.netmaskInt = netmask;

            this.netmaskBin = maskToBinValue(netmask);

            this.networkBin = (int) (this.ipBin & this.netmaskBin);
            this.network = binValueToIPv4Expr(networkBin);

            this.broadcastBin = this.networkBin | ~(this.netmaskBin);
            this.broadcast = binValueToIPv4Expr(this.broadcastBin);

            this.hostPartBin = this.ipBin ^ this.networkBin;
        } else {
            throw new IllegalArgumentException("A parameters is not matched to " + REGEX_IPV4 + ". parameter: " + ipv4);
        }
    }

    private int[] dottedDecimalAddressToIntArr(String ipv4) {
        Matcher m = patternIPv4.matcher(ipv4);

        if (!m.matches()) {
            throw new IllegalArgumentException("A parameters is not matched to " + REGEX_IPV4 + ". Subnet mask: " + netmask);
        }

        int[] maskBits = new int[4];
        maskBits[0] = Integer.parseInt(m.group(1));
        maskBits[1] = Integer.parseInt(m.group(2));
        maskBits[2] = Integer.parseInt(m.group(3));
        maskBits[3] = Integer.parseInt(m.group(4));

        return maskBits;
    }

    private int dottedDecimalNetmaskToIntValue(String mask) {
        int[] maskBits = dottedDecimalAddressToIntArr(mask);

        int netmask = 0;
        boolean broken = false;
        for (int maskBit : maskBits) {
            for (int i = 7; i > -1; i--) {
                if ((maskBit & (int) Math.pow(2, i)) < 1) {
                    broken = true;
                    break;
                }
                netmask++;
            }

            if (broken) {
                break;
            }
        }

        return netmask;
    }

    private String dotValueToIPv4Expr(Object... values) {
        StringBuffer ipv4Expr = new StringBuffer();

        ipv4Expr.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            ipv4Expr.append('.');
            ipv4Expr.append(values[i]);
        }

        return ipv4Expr.toString();
    }

    /**
     * Return IPv4 Addresses contained this network.
     * 
     * @return
     *
     * @since Apr 15, 2015
     */
    public Collection<String> getAddresses() {

        List<String> addresses = new ArrayList<>();
        for (int i = this.networkBin + 1; i < this.broadcastBin; i++) {
            addresses.add(binValueToIPv4Expr(i));
        }

        return addresses;
    }

    /**
     * Return a number of IPv4 Addresses contained this network.
     * 
     * @return
     *
     * @since Apr 15, 2015
     */
    public int getAddressesCount() {
        return this.broadcastBin - this.networkBin - 1;
    }

    public String getBroadcastIP() {
        return this.broadcast;
    }

    public int getIpBin() {
        return this.ipBin;
    }

    public String getIpv4() {
        return ipv4;
    }

    public String getNetmask() {
        return netmask;
    }

    public String getNetwork() {
        return network;
    }

    private int ipv4ToBinValue(String ipv4) {

        String[] ipArr = ipv4.split("[\\.]");

        if (ipArr.length != 4) {
            throw new IllegalArgumentException("A parameters is not matched to " + REGEX_IPV4 + ". parameter: " + ipv4);
        }

        int value1 = Integer.parseInt(ipArr[0]);
        int value2 = Integer.parseInt(ipArr[1]);
        int value3 = Integer.parseInt(ipArr[2]);
        int value4 = Integer.parseInt(ipArr[3]);

        return (value1 << 24) | (value2 << 16) | (value3 << 8) | (value4 << 0);
    }

    public boolean isAvailable(String ipv4) {
        return isAvailable(ipv4, false);
    }

    public boolean isAvailable(String ipv4, boolean logged) {
        int other = ipv4ToBinValue(ipv4);
        return other == this.networkBin || other == this.broadcastBin ? false : matched(other, logged);
    }

    private int maskToBinValue(int mask) {
        long maskBinValue = 0;
        for (int i = mask; i > -1; i--) {
            maskBinValue |= (1L << (32L - i));
        }

        return (int) maskBinValue;
    }

    private String maskToIPv4Expr(int mask) {
        return binValueToIPv4Expr(maskToBinValue(mask));
    }

    /**
     * 
     * @param other
     *            binary value that a dotted decimal address is translated integer.
     * @param logged
     * @return
     *
     * @since Apr 15, 2015
     */
    private boolean matched(int other, boolean logged) {
        if (logged) {
            print(ipv4, other);
        }
        return ((this.netmaskBin & other) ^ this.networkBin) == 0;
    }

    public boolean matched(String ipv4) {
        return matched(ipv4ToBinValue(ipv4), false);
    }

    public boolean matched(String ipv4, boolean logged) {
        return matched(ipv4ToBinValue(ipv4), logged);
    }

    protected void print(String ipv4, int other) {
        String format = "%-15s: %s\n";
        System.out.println("=============================================================================");
        System.out.printf(format, "Host.address", this.ipv4);
        System.out.printf(format, "Host.mask", this.netmask);
        System.out.printf(format, "Host.network", this.network);
        System.out.printf(format, "Other.IPv4", ipv4);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf(format, "Other.IPv4", toWellFormed32bitBinaryString(other));
        System.out.printf(format, "Host.mask", toWellFormed32bitBinaryString(this.netmaskBin));
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(format, "", toWellFormed32bitBinaryString((int) ((this.netmaskBin & other))) + ", op=&");
        System.out.printf(format, "Host.network", toWellFormed32bitBinaryString(this.networkBin));
        System.out.println("-----------------------------------------------------------------");
        System.out.printf(format, "Masking", toWellFormed32bitBinaryString((int) ((this.netmaskBin & other) ^ this.networkBin)) + ", op=^");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @Override
    public String toString() {
        StringBuffer toString = new StringBuffer();
        toString.append(String.format(TO_STRING_FORMAT, "Address", toWellFormed32bitBinaryString(ipBin), ipv4));
        toString.append('\n');
        toString.append(String.format(TO_STRING_FORMAT, "Mask", toWellFormed32bitBinaryString(maskToBinValue(netmaskInt)), netmask + " (/" + netmaskInt + ")"));
        toString.append('\n');
        toString.append(String.format(TO_STRING_FORMAT, "Network", toWellFormed32bitBinaryString(networkBin), network));
        toString.append('\n');
        toString.append(String.format(TO_STRING_FORMAT, "Broadcast", toWellFormed32bitBinaryString(broadcastBin), broadcast));
        toString.append('\n');
        toString.append(String.format(TO_STRING_FORMAT, "Host part", toWellFormed32bitBinaryString(hostPartBin), binValueToIPv4Expr(hostPartBin)));
        toString.append('\n');
        toString.append(String.format("%-10s: %s / %s", "Available", getAddressesCount(), getAddresses()));

        return toString.toString();
    }

    private String toWellFormed32bitBinaryString(int value) {
        char[] str = new char[32];

        Arrays.fill(str, '0');

        char[] src = Integer.toBinaryString(value).toCharArray();
        System.arraycopy(src, 0, str, 32 - src.length, src.length);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 32; i++) {
            // split for 8-bit
            if (i < 24 && (i + 1) % 8 == 0) {
                sb.append(str[i] + " | ");
            } else
            // split for 2-bit
            if ((i + 1) % 2 == 0) {
                sb.append(str[i] + " ");
            } else {
                sb.append(str[i]);
            }
        }

        return sb.toString().trim();
    }

}
