/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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
*
* This file is generated under this project, "UUUU".
*
* Date  : 2014. 4. 10. 오후 2:09:03
*
* Author: Park_Jun_Hong_(fafanmama_at_naver_com)
* 
*/

package open.commons.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

import open.commons.utils.AssertUtils;
import open.commons.utils.CollectionUtils;

/**
 * 
 * @param <D>
 *            {data} 검증할 데이터 타입.
 * @param <T>
 *            {token} 데이터를 검증에 사용될 단위 데이터 타입
 * @since 2014. 4. 10.
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class AbstractValidator<D, T> implements IValidator<D, T> {

    protected static final int INIT_VALID = 0xFFFFFFFF;
    private int valid = INIT_VALID;

    protected static final int MAX_FEATURE_COUNT = 31;
    protected static final int INIT_FEATURES = 0x00;
    protected static final int UNKNOWN_TOKEN_FEATURE = 31;

    private final AtomicInteger featureHolder = new AtomicInteger(0);
    private int featureCount = 0;
    private int features = INIT_FEATURES;
    /** 키: feature, 값: {@link ITokenValidator} */
    private ConcurrentSkipListMap<Integer, ITokenValidator<T>> tokenValidators = new ConcurrentSkipListMap<Integer, ITokenValidator<T>>();

    /** Unknown Tokens */
    private Set<T> unknownTokens = new HashSet<T>();

    private List<String> invalidReasons = new ArrayList<String>();

    private ConcurrentMap<Object, Integer> customeFeatures = new ConcurrentHashMap<Object, Integer>();

    protected ITokenizer<D, T> tokenizer;

    public AbstractValidator() {
        this(null);
    }

    public AbstractValidator(ITokenizer<D, T> tokenizer) {

        this.tokenizer = tokenizer;

        ITokenValidator<T> tokenValidator = new FalseTokenValidator<T>("Unknown Tokens") {
            /**
             * @see open.commons.validation.NamedTokenValidator#isPositive()
             */
            @Override
            public boolean isPositive() {
                return false;
            }
        };

        tokenValidators.put(UNKNOWN_TOKEN_FEATURE, tokenValidator);
        setFeature(UNKNOWN_TOKEN_FEATURE, true, tokenValidator.isPositive());

    }

    public int addTokenValidator(ITokenValidator<T> tokenValidator) {
        AssertUtils.assertNulls(tokenValidator);

        int feature = getFeature(tokenValidator.isPositive());

        tokenValidators.put(feature, tokenValidator);

        return feature;
    }

    public List<Integer> addTokenValidators(ITokenValidator<T>... tokenValidators) {
        List<Integer> features = new ArrayList<Integer>();
        for (ITokenValidator<T> tokenValidator : tokenValidators) {
            features.add(addTokenValidator(tokenValidator));
        }

        return features;
    }

    protected final void addUnknownToken(T token) {
        unknownTokens.add(token);
    }

    protected final int getCustomFeature(Object holder, ITokenValidator<T> tokenValidator) {
        Integer feature = customeFeatures.get(holder);

        if (feature == null) {
            feature = getFeature(tokenValidator.isPositive());

            customeFeatures.put(holder, feature);

            tokenValidators.put(feature, tokenValidator);
        }

        return feature;
    }

    protected final D getData() {
        return tokenizer != null ? tokenizer.getData() : null;
    }

    protected final int getFeature(boolean positive) {

        AssertUtils.assertTrue("Oops! The count of features is over the MAX.", featureCount > MAX_FEATURE_COUNT, IllegalArgumentException.class);

        int feature = featureHolder.intValue();

        featureHolder.set(feature + 1);

        setFeature(feature, true, positive);

        featureCount++;

        return feature;
    }

    public String getInvalidReason() {

        Integer feature = null;
        ITokenValidator<T> validator = null;
        for (Entry<Integer, ITokenValidator<T>> entry : tokenValidators.entrySet()) {
            feature = entry.getKey();
            validator = entry.getValue();

            if (!isValid(feature)) {
                invalidReasons.add(validator.getName() + ", expected: " + validator.getValidTokens());
            }
        }

        String reason = invalidReasons.size() < 1 ? "VALID" : CollectionUtils.toString(invalidReasons, ", ");

        invalidReasons.clear();

        return reason;
    }

    protected ITokenValidator<T> getTokenValidator(int feature) {
        return tokenValidators.get(feature);
    }

    public Set<T> getUnknownTokens() {
        return Collections.unmodifiableSet(unknownTokens);
    }

    /**
     * Return whether the feature is configured or not.
     * 
     * @param feature
     *            feature to check.<br>
     * @return
     * 
     * @since 2014. 4. 10.
     */
    protected boolean isFeatured(int feature) {
        return (this.features & feature) == feature;
    }

    public boolean isValid() {
        return this.valid == INIT_VALID;
    }

    /**
     * Return whether type feature is valid.
     * 
     * @param feature
     * @return
     * 
     * @since 2014. 4. 10.
     */
    protected boolean isValid(int feature) {
        int shift = 0x01 << feature;

        return (this.valid & shift) == shift;
    }

    /**
     * @see open.commons.validation.IValidator#postValid(ITokenizer<D, T>)
     */
    @Override
    public boolean postValid(ITokenizer<D, T> data) {
        return true;
    }

    /**
     * @see open.commons.validation.IValidator#preValid(ITokenizer<D, T>)
     */
    @Override
    public boolean preValid(ITokenizer<D, T> data) {
        return true;
    }

    public void removeTokenValidator(int feature) {
        tokenValidators.remove(feature);

        reset();
    }

    protected void reset() {
        this.features = INIT_FEATURES;
        this.valid = INIT_VALID;
        this.invalidReasons.clear();
        this.unknownTokens.clear();

        resetValidation();
    }

    private void resetValidation() {

        Integer feature = null;
        ITokenValidator<T> tokenValidator = null;
        for (Entry<Integer, ITokenValidator<T>> entry : tokenValidators.entrySet()) {
            feature = entry.getKey();
            tokenValidator = entry.getValue();
            setFeature(feature, true, tokenValidator.isPositive());
        }

    }

    protected void setCauseByToken(String cause) {
        this.invalidReasons.add(cause);

    }

    /**
     * 
     * @param feature
     *            설정 flag
     * @param enable
     *            설정/해제 여부
     * @param positive
     *            검증결과가 true 인 경우가 적합한 경우인지
     * @since 2014. 4. 10.
     */
    private void setFeature(int feature, boolean enable, boolean positive) {
        int shift = 0x01 << feature;

        if (enable) {
            // 설정 추가
            this.features |= shift; // 설정 추가
            // 해당 feature 검증값 설정: 0, 단 negative 인 경우에는 1.
            this.valid = positive ? valid & (shift ^ 0xFFFFFFFF) : valid | shift;
        } else {
            // 설정 제거
            this.features &= (shift ^ 0xFFFFFFFF);
            // 해당 feature 검증값 초기화: 1.
            this.valid |= shift;
        }
    }

    public final void setTokenizer(ITokenizer<D, T> tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * 
     * @param feature
     * @since 2014. 4. 10.
     */
    protected final void setValid(int feature) {
        ITokenValidator<T> tokenValidator = tokenValidators.get(feature);

        int shift = 0x01 << feature;

        this.valid = tokenValidator.isPositive() ? this.valid | shift : this.valid & (shift ^ INIT_VALID);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append(getClass().getSimpleName());
        sb.append("[data: ");
        sb.append(getData());
        sb.append("\n\t, valid: ");
        sb.append(isValid());
        sb.append(", unknown-tokens: ");
        sb.append(getUnknownTokens());
        sb.append(", invalid-reasons: ");
        sb.append(getInvalidReason());
        sb.append(", validators: ");

        Iterator<Integer> itr = tokenValidators.keySet().iterator();

        int feature = 0;
        String tokenName = null;
        if (itr.hasNext()) {
            feature = itr.next();
            tokenName = tokenValidators.get(feature).getName();

            sb.append('[');
            sb.append(tokenName);
            sb.append("] -> ");
            sb.append(isValid(feature));

            while (itr.hasNext()) {
                sb.append(", ");

                feature = itr.next();
                tokenName = tokenValidators.get(feature).getName();

                sb.append('[');
                sb.append(tokenName);
                sb.append("] -> ");
                sb.append(isValid(feature));
            }
        }

        sb.append(']');

        return sb.toString();
    }

    protected final boolean valid0(T token) {
        Integer feature = null;
        ITokenValidator<T> tokenValidator = null;

        for (Entry<Integer, ITokenValidator<T>> entry : tokenValidators.entrySet()) {
            feature = entry.getKey();
            tokenValidator = entry.getValue();

            if (tokenValidator.validate(token)) {
                setValid(feature);

                setFeature(feature, false, true);

                // (start) TODO: 미등록 토큰 처리 / Park_Jun_Hong_(fafanmama_at_naver_com): 2014. 9. 17.
                if (feature == UNKNOWN_TOKEN_FEATURE) {
                }
                // (end): 2014. 9. 17.

                return tokenValidator.isPositive();
            }
        }

        return false;
    }

    /**
     * 
     * @see open.commons.validation.IValidator#validate()
     */
    @Override
    public final boolean validate() {
        return validate(false);
    }

    /**
     * 
     * @see open.commons.validation.IValidator#validate(boolean)
     */
    @Override
    public final boolean validate(boolean fully) {

        if (this.tokenizer == null) {
            throw new IllegalStateException("ITokenizer가 설정되지 않았습니다.");
        }

        if (!preValid(tokenizer) && !fully) {
            return isValid();
        }

        validate0(fully);

        postValid(tokenizer);

        return isValid();
    }

    protected final void validate0(boolean fully) {
        while (tokenizer.hasToken()) {
            if (valid0(tokenizer.getToken()) || fully) {
                continue;
            }
        }

    }
}
