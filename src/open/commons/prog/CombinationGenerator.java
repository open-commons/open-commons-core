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

package open.commons.prog;

import java.math.BigInteger;

public class CombinationGenerator {

    private int[] a;
    private int n;
    private int r;
    private BigInteger numLeft;
    private BigInteger total;

    // ------------
    // Constructor
    // ------------

    /**
     * 
     * @param n
     *            조합 대상 원소 개수
     * @param r
     *            결과 대상 원소 개수
     * 
     *            <BR>
     * @author Park Jun-Hong (fafanmama_at_naver_dot_com)
     * @since 2012. 1. 6.
     */
    public CombinationGenerator(int n, int r) {
        if (r > n) {
            throw new IllegalArgumentException();
        }
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.r = r;
        a = new int[r];
        BigInteger nFact = getFactorial(n);
        BigInteger rFact = getFactorial(r);
        BigInteger nminusrFact = getFactorial(n - r);
        total = nFact.divide(rFact.multiply(nminusrFact));
        reset();
    }

    // ------
    // Reset
    // ------

    public int[] getNext() {

        if (numLeft.equals(total)) {
            numLeft = numLeft.subtract(BigInteger.ONE);
            return a;
        }

        int i = r - 1;
        while (a[i] == n - r + i) {
            i--;
        }
        a[i] = a[i] + 1;
        for (int j = i + 1; j < r; j++) {
            a[j] = a[i] + j - i;
        }

        numLeft = numLeft.subtract(BigInteger.ONE);
        return a;

    }

    // ------------------------------------------------
    // Return number of combinations not yet generated
    // ------------------------------------------------

    public BigInteger getNumLeft() {
        return numLeft;
    }

    // -----------------------------
    // Are there more combinations?
    // -----------------------------

    public BigInteger getTotal() {
        return total;
    }

    // ------------------------------------
    // Return total number of combinations
    // ------------------------------------

    public boolean hasMore() {
        return numLeft.compareTo(BigInteger.ZERO) == 1;
    }

    // ------------------
    // Compute factorial
    // ------------------

    public void reset() {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        numLeft = new BigInteger(total.toString());
    }

    // --------------------------------------------------------
    // Generate next combination (algorithm from Rosen p. 286)
    // --------------------------------------------------------

    private static BigInteger getFactorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = n; i > 1; i--) {
            fact = fact.multiply(new BigInteger(Integer.toString(i)));
        }
        return fact;
    }

    public static void main(String[] args) {
        // String[] elements = { "a", "b", "c", "d", "e", "f", "g" };
        int[] elements = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
        int[] indices;
        CombinationGenerator x = new CombinationGenerator(elements.length, 9);
        StringBuffer combination;

        int totalCount = 0;
        while (x.hasMore()) {
            combination = new StringBuffer();
            indices = x.getNext();
            for (int i = 0; i < indices.length; i++) {
                combination.append(elements[indices[i]]);

            }
            totalCount++;
            System.out.println(combination.toString());
        }

        System.out.println("totalCount: " + totalCount);
    }
}