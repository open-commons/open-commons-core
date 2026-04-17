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
*/
package open.commons.core.io;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.StringUtils;

/**
 * 
 * <BR>
 * 
 * @since 2012. 01. 20.
 */
public class FileExtensionInclusive implements FileFilter {

    private Set<String> extensioins = new HashSet<String>();

    public FileExtensionInclusive() {

    }

    /**
     * 
     * @param exts
     *            파일 확장자
     * 
     * @since 2012. 01. 20.
     * 
     * @see Set#addAll(Collection)
     */
    public FileExtensionInclusive(Collection<String> exts) {
        AssertUtils2.notNulls(exts);

        extensioins.addAll(exts);
    }

    /**
     * 
     * @param exts
     *            파일 확장자
     * @since 2012. 01. 20.
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) exts);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public FileExtensionInclusive(String... exts) {
        AssertUtils2.notNulls((Object[]) exts);

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
     * @see java.io.FileFilter#accept(java.io.File)
     */
    // 아래 내용에 적용됨.
    // - StringUtils.endsWithOneOf(pathname.getName(), extensioins.toArray(String[]::new));
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
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
            return StringUtils.endsWithOneOf(pathname.getName(), extensioins.toArray(String[]::new));
        }
    }

    /**
     */
    // 아래 내용에 적용됨.
    // - AssertUtils2.notNulls((Object[]) exts);
    // [PATCH] 배열 공변성/가변성에 대한 IDE 분석기의 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    @SuppressWarnings("null")
    public void add(String... exts) {
        AssertUtils2.notNulls((Object[]) exts);

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