#### 前期准备
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
- 使用vscode进行开发
#### 确实使用vscode写java是一种`考验`把，使用过程中要不断了解许多东西，可能没有使用像eclipse、IDEA那样智能，但是做程序员不就是一路折腾吗。
1. vscode支持eclipse项目，可以在两个编辑器之间互换，但需了解基本知识
    配置.classpath文件
    ```
    <classpathentry kind="src" path="src"/>
    <classpathentry kind="output" path="bin"/>
    ```

    每当需要为你的项目增加一个库时，就需要在.classpath中增加
    ```
    <classpathentry kind="lib" path="Your lib path"/>
    ```
    path有两种选择：(eclipse使用的是第1种，即当你在buildpath中增加一个reference library时)

    1.直接定位到你本地的lib路径，但是这样就不能迁移项目。
    ```
    eg:<classpathentry  kind="lib" path="/home/xuecheng/RefLib/algs4.jar"/>
    ```

    2.在根目录创建lib目录，将你的xxx.jar复制到该目录下。
    ```
    eg:<classpathentry kind="lib" path="lib/algs4.jar"/>
    ```
2. 有时候我们想要进行单元测试(比如我写的每一章节的答案，全部放在main里面显得繁琐杂乱)

   新建test目录，修改.classpath
   ```
   <classpathentry kind="src" path="test"/>
   ```
   kind定义为src，vscode中的java扩展会自动把你的XxxxTest.java文件生成到和src/目录下相同包名路径下的.class文件到bin/目录中


