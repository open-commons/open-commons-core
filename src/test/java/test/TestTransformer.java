/*
 * Copyright 2025 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2025. 9. 8. 오전 11:09:17
 *
 * Author: Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 */

package test;

import java.util.ArrayList;
import java.util.List;

import open.commons.core.annotation.Getter;
import open.commons.core.annotation.Setter;
import open.commons.core.utils.ObjectUtils;
import open.commons.core.utils.Transformer;

/**
 * 
 * @since 2025. 9. 8.
 * @version 2.1.0
 * @author Park Jun-Hong (parkjunhong77@gmail.com)
 */
public class TestTransformer {

    public static class A {
        String name;
        Aa child;
        List<Aa> children;

        public A() {
        }

        public A(String name) {
            this.name = name;
        }

        @Getter
        public String getName() {
            return name;
        }

        @Setter
        public void setName(String name) {
            this.name = name;
        }

        @Getter
        public List<Aa> getChildren() {
            if (logging) {
                System.out.println("[children.G] A=" + this + ", A.children=" + this.children);
            }
            return children;
        }

        @Setter
        public void setChildren(List<Aa> aa) {
            this.children = aa;
            if (logging) {
                System.out.println("[children.S] A=" + this + ", A.children=" + this.children);
            }
        }

        @Getter
        public Aa getChild() {
            if (logging) {
                System.out.println("[child.A] A=" + this + ", A.child=" + this.child);
            }
            return child;
        }

        @Setter
        public void setChild(Aa child) {
            this.child = child;
            if (logging) {
                System.out.println("[child.S] A=" + this + ", A.child=" + this.child);
            }
        }
    }

    public static class Aa {
        String name;
        List<Aaa> children;

        public Aa() {
        }

        public Aa(String name) {
            this.name = name;
        }

        @Getter
        public String getName() {
            return name;
        }

        @Setter
        public void setName(String name) {
            this.name = name;
        }

        @Getter
        public List<Aaa> getChildren() {
            if (logging) {
                System.out.println("[children.G] Aa=" + this + ", Aa.children=" + this.children);
            }
            return children;
        }

        @Setter
        public void setChildren(List<Aaa> aaa) {
            this.children = aaa;
            if (logging) {
                System.out.println("[children.S] Aa=" + this + ",  Aa.children=" + this.children);
            }
        }
    }

    public static class Aaa {
        String name;

        public Aaa() {
        }

        public Aaa(String name) {
            this.name = name;
        }

        @Getter
        public String getName() {
            return name;
        }

        @Setter
        public void setName(String name) {
            this.name = name;
        }
    }

    public static class B {
        String name;
        public Bb child;
        List<Bb> children;

        public B() {
        }

        public B(String name) {
            this.name = name;
        }

        @Getter
        public String getName() {
            return name;
        }

        @Setter
        public void setName(String name) {
            this.name = name;
        }

        @Getter
        public List<Bb> getChildren() {
            if (logging) {
                System.out.println("[children.G] B=" + this + ", B.children=" + this.children);
            }
            return children;
        }

        @Setter(deepConvert = true)
        public void setChildren(List<Bb> bb) {
            this.children = bb;
            if (logging) {
                System.out.println("[children.S] B=" + this + ", B.children=" + this.children);
            }
        }

        @Getter
        public Bb getChild() {
            if (logging) {
                System.out.println("[child.G] B=" + this + ", B.child=" + this.child);
            }
            return child;
        }

//        @Setter(deepConvert = true)
        @Setter
        public void setChild(Bb child) {
            this.child = child;
            if (logging) {
                System.out.println("[child.S] B=" + this + ", B.child=" + this.child);
            }
        }
    }

    public static class Bb {
        String name;
        List<Bbb> children;

        public Bb() {
        }

        public Bb(String name) {
            this.name = name;
        }

        @Getter
        public String getName() {
            return name;
        }

        @Setter
        public void setName(String name) {
            this.name = name;
        }

        @Getter
        public List<Bbb> getChildren() {
            if (logging) {
                System.out.println("[children.G] Bb=" + this + ", Bb.children=" + this.children);
            }
            return children;
        }

        @Setter(deepConvert = true)
        public void setChildren(List<Bbb> bbb) {
            this.children = bbb;
            if (logging) {
                System.out.println("[children.S] Bb=" + this + ", Bb.children=" + this.children);
            }
        }
    }

    public static class Bbb {
        String name;

        public Bbb() {
        }

        public Bbb(String name) {
            this.name = name;
        }

        @Getter
        public String getName() {
            return name;
        }

        @Setter
        public void setName(String name) {
            this.name = name;
        }
    }

    static Aa listAa(String name) {
        Aa aa = new Aa(name);
        List<Aaa> children = new ArrayList<>();
        children.add(new Aaa(name + ".children-1"));
        aa.setChildren(children);
        return aa;
    }

    static Bb listBb(String name) {
        Bb bb = new Bb(name);
        List<Bbb> children = new ArrayList<>();
        children.add(new Bbb(name + ".children-1"));
        bb.setChildren(children);
        return bb;
    }

    static boolean logging = false;

    public static void main(String[] args) {

        List<Aa> aChildren = new ArrayList<>();
        A a = new A("a");
        a.setChild(new Aa("a#c"));
        aChildren.add(listAa("a#1"));
        a.setChildren(aChildren);

        System.out.println();
        System.out.println();

        logging = true;

        A cloneA = Transformer.transform(a, false, new A("aa"), false, null);
        System.out.println("cloneA ........ " + cloneA);
        System.out.println();
        System.out.println();

        logging = false;

        List<Bb> bChildren = new ArrayList<>();
        B b = new B("b");
        b.setChild(new Bb("b#c"));
        bChildren.add(listBb("b#1"));
        b.setChildren(bChildren);

        logging = true;

        B cloneB = Transformer.transform(b, false, new B("bb"), false, null);
        System.out.println("cloneB ......" + cloneB);
        System.out.println();
        System.out.println();

        B a2b = Transformer.transform(a, false, new B("a2b"), false, null);
        System.out.println("a2b  >>> " + a2b);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        
        
        cloneA = ObjectUtils.transform(a, false, new A("aa"), false, null);
        System.out.println("cloneA#1 ........ " + cloneA);
        System.out.println();
        System.out.println();

        cloneB = ObjectUtils.transform(b, false, new B("bb"), false, null);
        System.out.println("cloneB#1 ......" + cloneB);
        System.out.println();
        System.out.println();

        a2b = ObjectUtils.transform(a, false, new B("a2b"), false, null);
        System.out.println("a2b#1  >>> " + a2b);
        System.out.println();
        System.out.println();

    }

}
