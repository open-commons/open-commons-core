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

/*
* 
*
* This file is generated under this project, "UUUU".
*
* Date  : 2014. 4. 10. 오후 2:09:03
*
* Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
* 
*/

package open.commons.core.validation;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ObjectUtils;

/**
 * 
 * @param <D>
 *            {data} 검증할 데이터 타입.
 * @param <T>
 *            {token} 데이터를 검증에 사용될 단위 데이터 타입
 * @since 2014. 4. 10.
 */
public class AbstractValidator<D, T> implements IValidator<D, T> {

    protected static final int INIT_VALID = 0xFFFFFFFF;
    protected static final int MAX_FEATURE_COUNT = 31;
    protected static final int INIT_FEATURES = 0x00;
    protected static final int UNKNOWN_TOKEN_FEATURE = 31;

    @SuppressWarnings("null")
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private int valid = INIT_VALID;
    private final AtomicInteger featureHolder = new AtomicInteger(0);
    private int featureCount = 0;
    private int features = INIT_FEATURES;

    /** 키: feature, 값: {@link ITokenValidator} */
    private final ConcurrentSkipListMap<Integer, ITokenValidator<T>> tokenValidators = new ConcurrentSkipListMap<>();

    /** Unknown Tokens (스레드 안전성 확보) */
    @SuppressWarnings("null")
    private final Set<T> unknownTokens = ConcurrentHashMap.newKeySet();

    /** 검증 실패 사유 (스레드 안전성 확보) */
    private final List<String> invalidReasons = new CopyOnWriteArrayList<>();

    private final ConcurrentMap<Object, Integer> customFeatures = new ConcurrentHashMap<>();

    protected @Nullable ITokenizer<D, T> tokenizer;

    /**
     * 객체를 생성합니다.
     */
    public AbstractValidator() {
        this(null);
    }

    /**
     * 객체를 생성합니다.
     *
     * @param tokenizer
     *            토크나이저
     */
    public AbstractValidator(@Nullable ITokenizer<D, T> tokenizer) {
        this.tokenizer = tokenizer;

        ITokenValidator<T> tokenValidator = new FalseTokenValidator<>("Unknown Tokens") {
            @Override
            public boolean isPositive() {
                return false;
            }
        };

        this.tokenValidators.put(UNKNOWN_TOKEN_FEATURE, tokenValidator);
        setFeature(UNKNOWN_TOKEN_FEATURE, true, tokenValidator.isPositive());
    }

    /**
     * 토큰 검증기를 추가합니다.
     *
     * @param tokenValidator
     *            토큰 검증기
     *
     * @return 할당된 Feature 식별자
     */
    public int addTokenValidator(ITokenValidator<T> tokenValidator) {
        Objects.requireNonNull(tokenValidator);

        int feature = getFeature(tokenValidator.isPositive());
        this.tokenValidators.put(feature, tokenValidator);

        return feature;
    }

    /**
     * 다수의 토큰 검증기를 추가합니다.
     *
     * @param tokenValidators
     *            토큰 검증기 가변 인자
     *
     * @return 할당된 Feature 식별자 리스트
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) tokenValidators);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    @SafeVarargs
    public final List<Integer> addTokenValidators(ITokenValidator<T>... tokenValidators) {
        ObjectUtils.requireNonNulls((Object[]) tokenValidators);

        List<Integer> featuresList = new CopyOnWriteArrayList<>();
        for (ITokenValidator<T> tokenValidator : tokenValidators) {
            featuresList.add(addTokenValidator(tokenValidator));
        }

        return featuresList;
    }

    /**
     * 알 수 없는 토큰을 추가합니다.
     *
     * @param token
     *            토큰
     */
    protected final void addUnknownToken(T token) {
        this.unknownTokens.add(token);
    }

    /**
     * 커스텀 Feature를 반환하거나 새롭게 생성하여 반환합니다.
     *
     * @param holder
     *            소유자 객체
     * @param tokenValidator
     *            토큰 검증기
     *
     * @return 할당된 Feature 식별자
     */
    protected final int getCustomFeature(Object holder, ITokenValidator<T> tokenValidator) {
        Objects.requireNonNull(holder);
        Objects.requireNonNull(tokenValidator);

        return customFeatures.computeIfAbsent(holder, _ -> {
            int feature = getFeature(tokenValidator.isPositive());
            this.tokenValidators.put(feature, tokenValidator);
            return feature;
        });
    }

    /**
     * 대상 데이터를 반환합니다.
     *
     * @return 대상 데이터. 토크나이저가 없는 경우 {@code null} 반환.
     */
    protected final @Nullable D getData() {
        return this.tokenizer != null ? this.tokenizer.getData() : null;
    }

    /**
     * 새로운 Feature 식별자를 생성하여 반환합니다.
     *
     * @param positive
     *            검증결과가 {@code true}인 경우가 적합한지 여부
     *
     * @return Feature 식별자
     *
     * @throws IllegalArgumentException
     *             Feature 개수가 최대 허용치({@value #MAX_FEATURE_COUNT})를 초과한 경우 발생.
     */
    protected final int getFeature(boolean positive) {
        AssertUtils2.isFalse("Oops! The count of features is over the MAX.", featureCount > MAX_FEATURE_COUNT, IllegalArgumentException.class);

        int feature = this.featureHolder.getAndIncrement();
        setFeature(feature, true, positive);
        this.featureCount++;

        return feature;
    }

    /**
     * 검증 실패 사유를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2026. 4. 6.       parkjunhong77@gmail.com     (3.0.0) 함수형 프로그래밍 적용 및 Getter 내부 상태 변조(Side-Effect) 안티패턴 제거
     * </pre>
     * 
     * * @return 검증 실패 사유 문자열
     */
    // 아래 내용에 적용됨.
    // - String.join(", ", customReasons)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public String getInvalidReason() {

        // 1. tokenValidators에서 실패 사유 추출
        List<String> customReasons = this.tokenValidators.entrySet().stream() //
                .filter(entry -> !isValid(entry.getKey())) //
                .map(entry -> {
                    ITokenValidator<T> validator = entry.getValue();
                    Set<T> tokens = validator.getValidTokens();
                    return String.join("", validator.getName(), ", expected: ", tokens != null ? tokens.toString() : "null");
                }) //
                .collect(Collectors.toList());

        // 2. setCauseByToken()을 통해 수집된 커스텀 사유 병합
        if (!this.invalidReasons.isEmpty()) {
            customReasons.addAll(this.invalidReasons);
        }

        // 3. 반환 (초기화는 reset() 메소드에서만 수행)
        return customReasons.isEmpty() ? "VALID" : String.join(", ", customReasons);
    }

    /**
     * 지정된 Feature 식별자에 해당하는 토큰 검증기를 반환합니다.
     *
     * @param feature
     *            Feature 식별자
     *
     * @return 토큰 검증기
     */
    protected @Nullable ITokenValidator<T> getTokenValidator(int feature) {
        return this.tokenValidators.get(feature);
    }

    /**
     * 알 수 없는 토큰 셋을 반환합니다.
     *
     * @return 토큰 셋 (수정 불가)
     */
    // 아래 내용에 적용됨.
    // - Collections.unmodifiableSet(unknownTokens)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public Set<T> getUnknownTokens() {
        return Collections.unmodifiableSet(unknownTokens);
    }

    /**
     * 특정 Feature가 설정되어 있는지 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param feature
     *            확인할 Feature 식별자
     *
     * @return 설정 여부
     *
     * @since 2014. 4. 10.
     */
    protected boolean isFeatured(int feature) {
        return (this.features & feature) == feature;
    }

    /**
     * 최종 검증 통과 여부를 반환합니다.
     *
     * @return 검증 통과 여부
     */
    public boolean isValid() {
        return this.valid == INIT_VALID;
    }

    /**
     * 특정 Feature가 유효한지(검증 통과) 여부를 반환합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param feature
     *            Feature 식별자
     *
     * @return 유효성 여부
     *
     * @since 2014. 4. 10.
     */
    protected boolean isValid(int feature) {
        int shift = 0x01 << feature;

        return (this.valid & shift) == shift;
    }

    /**
     * @see open.commons.core.validation.IValidator#postValid(open.commons.core.validation.ITokenizer)
     */
    @Override
    public boolean postValid(ITokenizer<D, T> data) {
        return true;
    }

    /**
     * @see open.commons.core.validation.IValidator#preValid(open.commons.core.validation.ITokenizer)
     */
    @Override
    public boolean preValid(ITokenizer<D, T> data) {
        return true;
    }

    /**
     * 토큰 검증기를 제거합니다.
     *
     * @param feature
     *            제거할 Feature 식별자
     */
    public void removeTokenValidator(int feature) {
        this.tokenValidators.remove(feature);

        reset();
    }

    /**
     * 검증 상태를 초기화합니다.
     */
    protected void reset() {
        this.features = INIT_FEATURES;
        this.valid = INIT_VALID;
        this.invalidReasons.clear();
        this.unknownTokens.clear();

        resetValidation();
    }

    /**
     * 검증기별 초기 Feature 설정을 복구합니다.
     */
    private void resetValidation() {

        Integer feature = null;
        ITokenValidator<T> tokenValidator = null;
        for (Entry<Integer, ITokenValidator<T>> entry : tokenValidators.entrySet()) {
            feature = entry.getKey();
            tokenValidator = entry.getValue();
            setFeature(feature, true, tokenValidator.isPositive());
        }

    }

    /**
     * 검증 실패 원인을 추가합니다.
     *
     * @param cause
     *            실패 원인 문자열
     */
    protected void setCauseByToken(String cause) {
        this.invalidReasons.add(cause);

    }

    /**
     * Feature 설정 값을 변경합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param feature
     *            설정 Flag
     * @param enable
     *            설정/해제 여부
     * @param positive
     *            검증결과가 {@code true}인 경우가 적합한지 여부
     *
     * @since 2014. 4. 10.
     */
    private void setFeature(int feature, boolean enable, boolean positive) {
        int shift = 0x01 << feature;

        if (enable) {
            this.features |= shift;
            this.valid = positive ? valid & ~shift : valid | shift;
        } else {
            this.features &= ~shift;
            this.valid |= shift;
        }
    }

    /**
     * 토크나이저를 설정합니다.
     *
     * @param tokenizer
     *            토크나이저
     */
    public final void setTokenizer(ITokenizer<D, T> tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * Feature의 유효 상태를 설정합니다.
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자                    | 내용
     * ----------------------------------------------------------------------
     * 2014. 4. 10.      parkjunhong77@gmail.com     최초 작성
     * </pre>
     *
     * @param feature
     *            Feature 식별자
     *
     * @since 2014. 4. 10.
     */
    protected final void setValid(int feature) {
        ITokenValidator<T> tokenValidator = this.tokenValidators.get(feature);

        if (tokenValidator == null) {
            this.logger.warn("feature({})에 해당하는 TokenValidator가 존재하지 않습니다.", feature);
            return;
        }

        int shift = 0x01 << feature;
        this.valid = tokenValidator.isPositive() ? this.valid | shift : this.valid & ~shift;
    }

    /**
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()();
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()) //
                .append("[data: ") //
                .append(getData()) //
                .append("\n\t, valid: ") //
                .append(isValid())//
                .append(", unknown-tokens: ")//
                .append(getUnknownTokens()) //
                .append(", invalid-reasons: ") //
                .append(getInvalidReason()) //
                .append(", validators: ");

        StringJoiner validatorJoiner = new StringJoiner(", ");
        this.tokenValidators.forEach((feature, validator) -> {
            validatorJoiner.add("[" + validator.getName() + "] -> " + isValid(feature));
        });

        sb.append(validatorJoiner.toString()).append(']');
        return sb.toString();
    }

    /**
     * 단일 토큰을 검증합니다.
     *
     * @param token
     *            단위 토큰
     *
     * @return 검증 통과 여부
     */
    protected final boolean valid0(T token) {
        for (Entry<Integer, ITokenValidator<T>> entry : this.tokenValidators.entrySet()) {
            int feature = entry.getKey();
            ITokenValidator<T> tokenValidator = entry.getValue();

            if (tokenValidator.validate(token)) {
                setValid(feature);
                setFeature(feature, false, true);

                // (start) TODO: 미등록 토큰 처리 / Park_Jun_Hong_(parkjunhong77@gmail.com): 2014. 9. 17.
                if (feature == UNKNOWN_TOKEN_FEATURE) {
                    // 미등록 토큰 발생 시 별도 후처리 로직 필요
                }
                // (end): 2014. 9. 17.

                return tokenValidator.isPositive();
            }
        }

        return false;
    }

    @Override
    public final boolean validate() {
        return validate(false);
    }

    @Override
    public final boolean validate(boolean fully) {
        ITokenizer<D, T> tokenizer = this.tokenizer;
        Objects.requireNonNull(tokenizer, "ITokenizer가 설정되지 않았습니다.");

        if (!preValid(tokenizer) && !fully) {
            return isValid();
        }

        validate0(fully);
        postValid(tokenizer);

        return isValid();
    }

    /**
     * 토큰화된 모든 데이터에 대해 검증을 수행합니다.
     *
     * @param fully
     *            전체 검증(Fail-Fast 무시) 여부
     */
    protected final void validate0(boolean fully) {
        ITokenizer<D, T> tokenizer = this.tokenizer;
        while (tokenizer != null && tokenizer.hasToken()) {
            if (!valid0(tokenizer.getToken()) && !fully) {
                // [PATCH] Fail-Fast 보강: fully가 false일 경우 즉시 중단
                break;
            }
        }
    }
}
