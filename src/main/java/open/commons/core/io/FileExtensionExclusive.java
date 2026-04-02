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
 * This file is generated under this project, "open-commons-core".
 *
 * Date  : 2012. 1. 20.
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.core.io;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ObjectUtils;
import open.commons.core.utils.StringUtils;

/**
 * 
 * @since 2012. 01. 20.
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */
public class FileExtensionExclusive implements FileFilter {

    private Set<String> extensioins = new HashSet<String>();

    public FileExtensionExclusive() {
    }

    /**
     * 
     * @param exts
     *            파일 확장자
     * 
     * @throws NullPointerException
     *             파라미터({@code exts})가 {@code null}인 경우 발생.
     * 
     * @since 2012. 01. 20.
     * 
     * @see Set#addAll(Collection)
     */
    public FileExtensionExclusive(Collection<String> exts) {
        AssertUtils2.notExistNull(exts);

        extensioins.addAll(exts);
    }

    /**
     * 
     * @param exts
     *            파일 확장자
     * 
     * @throws NullPointerException
     *             파라미터({@code exts})가 {@code null}이거나 {@code null}을 포함한 경우 발생.
     * 
     * @since 2012. 01. 20.
     * 
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) exts);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public FileExtensionExclusive(String... exts) {
        ObjectUtils.requireNonNulls((Object[]) exts);

        for (String ext : exts) {
            extensioins.add(ext);
        }
    }

    /**
     * @param pathname
     * @return
     * 
     * @since 2012. 01. 20.
     * 
     * 
     * @see java.io.FileFilter#accept(java.io.File)
     */
    // 아래 내용에 적용됨.
    // - pathname.getName()
    // - extensioins.toArray(String[]::new
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public boolean accept(@Nullable File pathname) {
        if (pathname == null) {
            return false;
        }

        if (!pathname.isFile()) {
            return false;
        } else {
            return !StringUtils.endsWithOneOf(pathname.getName(), extensioins.toArray(String[]::new) //
            );
        }
    }

    /**
     * @throws NullPointerException
     *             파라미터({@code exts})가 {@code null}이거나 {@code null}을 포함한 경우 발생.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) exts);
    // [PATCH] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public void add(String... exts) {
        ObjectUtils.requireNonNulls((Object[]) exts);

        synchronized (extensioins) {
            for (String ext : exts) {
                extensioins.add(ext);
            }
        }
    }

    public void add(@Nullable String ext) {
        synchronized (extensioins) {
            if (ext != null) {
                extensioins.add(ext);
            }
        }
    }

    public void remove(@Nullable String ext) {
        synchronized (extensioins) {
            if (ext != null) {
                extensioins.remove(ext);
            }
        }
    }

}
