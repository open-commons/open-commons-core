[The language used to this is korean.]

build_all.project.xml을 사용하기 위해서는 다음과 같은 설정을 해야 합니다.

1] ANT library공간에 ant-contrib-1.0.b3.jar 및 lib 디렉토리 하위의 모든 jar 파일을 추가합니다.

- 이클립스의 경우 설정: Window > Preferences > Ant > Runtime - Classpath _Ant Home Entries 에 추가

2] 프로젝트의 ANT 빌드 파일에 아래와 같이 xmlns:ac="antlib:net.sf.antcontrib" 설정을 합니다.

<project basedir="." default="xxx" name="xxx" xmlns:ac="antlib:net.sf.antcontrib">

...
