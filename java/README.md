### 1. 前期准备

#### 开发环境  
```
Ubuntu18.04.2LTS
VSCode + Java Extension Pack
```
- 配置JAVA_HOME、JRE_HOME、CLASSPATH(~/.bashrc改变当前用户，/etc/profile改变全局)
```
  export JAVA_HOME="/usr/share/jvm/jdk1.8.0_201"
  export JRE_HOME=$JAVA_HOME/jre
  export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
  export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
```
- 把从'[kevin-wayne/algs4](https://github.com/kevin-wayne/algs4)'下好的源代码分类打包,个人目的是为了清晰， 方便自己修改代码，PS.过程有点繁琐,因为作者是将整个原书代码以及作者自己编写的库打包在一个目录里

- 如果有需要可以将[算法4官方站点](https://algs4.cs.princeton.edu/code/)里的`algs4.jar`下载并放入自己的库里面。
```
eg：/home/xuecheng/RefLib/algs4.jar
```
也可以将它导入到你的CLASSPATH中(最后的$CLASSPATH不能省略)
```
export CLASSPATH=/home/xuecheng/RefLib/algs4.jar:$CLASSPATH
```
### 2. 使用vscode进行开发

#### 确实使用vscode写java是一种`浪费时间`把，使用过程中要不断了解许多东西，可能没有使用像eclipse、IDEA那样智能，但是做程序员不就是一路折腾吗。
- 配置`.classpath`文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<classpath>
  <classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>
  <classpathentry kind="src" path="src"/>
  <classpathentry kind="output" path="bin"/>
</classpath>
```
> 每当需要为你的项目增加一个库时，就需要在.classpath中增加
```
<classpathentry kind="lib" path="Your lib path"/>
```
> path有两种选择：(eclipse使用的是第1种，即当你在buildpath中add external archives时)  

  1.直接定位到你本地的lib路径，但是这样就不能迁移项目。
  ```
  eg:<classpathentry  kind="lib" path="/home/xuecheng/RefLib/algs4.jar"/>
  ```
  2.在根目录创建lib目录，将你的xxx.jar复制到该目录下。
  ```
  eg:<classpathentry kind="lib" path="lib/algs4.jar"/>
  ```

- 配置`.project`文件  

> 只有配置好了这个文件vscode中的java扩展包才能识别这是个java项目
```
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
  <name>java</name>
  <comment></comment>
  <projects>
  </projects>
  <buildSpec>
      <buildCommand>
          <name>org.eclipse.jdt.core.javabuilder</name>
          <arguments>
          </arguments>
      </buildCommand>
  </buildSpec>
  <natures>
      <nature>org.eclipse.jdt.core.javanature</nature>
  </natures>
</projectDescription>
```
- 单元测试(可选)
    1. 新建test目录，修改.classpath
    ```
    <classpathentry kind="src" path="test"/>
    ```
    kind定义为src，vscode中的java扩展会自动把你的XxxxTest.java文件生成到和src/目录下相同包名路径下的.class文件到bin/目录中

    2. 下载[JUnit4](https://github.com/junit-team/junit4)
    PS.现在已经更新到JUnit5了
    和algs4.jar一样两种办法,这里用第一种
    ```
    <classpathentry kind="lib" path="/home/xuecheng/RefLib/JUnit/junit-4.12-sources.jar"/>
    ```

    3. 创建和src/目录下想用的包路径，并将要测试的文件改名为XxxTest.java

    ```java
    package solutions.fundamentals;

    import static org.junit.Assert.*;
    import org.junit.Test;

    public class ChapterOneSolutionTest
    {
       @Test
       public void testParentheses()
       {
           String pattern = "[[]](){}[({})]";
           assertEquals(true, ChapterOneSolution.parentheses(pattern));
       }
    }
    ```

### 3. 最后写点废话

#### > algs4中的分类和修改包名、处理依赖真的繁琐，于是一边做的时候一边想计算机不就是为了从繁琐至简吗？

列个todo：
 - 一键修改包下面的不正确的包名
 - 自动处理依赖
 - 先这些把

最后：学了那么多乱七八糟的东西，不如静下心来通过某个特定的语言去写写算法，效率极佳，怀念当初用c写一遍一遍实现数据结构的时候
