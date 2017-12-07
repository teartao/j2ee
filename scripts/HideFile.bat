@echo off 
rem [这是代码注释]关闭自动输出
:begin
rem [这是代码注释]接收输入
cd /d %~d0
dir 
echo ===============请输入要加密的文件夹名=================
echo 输入之前，
echo 1、确认要加密（隐藏）的文件是否和本文件(当前bat文件)在同一目录（文件夹）下 
echo 上面打印的列表即为当前文件夹下所有非系统文件的文件名，
echo 请查找并确认（待加密）文件和bat在以上列表中
echo 2、确认你运行本文件是以管理员身份运行，否则会报错，
echo 如果不确定，就关掉重新打开
echo ================================================
set input=
set /p input=文件名 : 
rem [这是代码注释]输出得到的输入信息
echo 您输入的文件(夹)名为：%input%
echo =======================
echo 现在，对文件右击创建快捷方式，不要修改快捷方式名称。然后按(除ESC)任意键(字幕数字均可)继续
echo =======================
@pause
set sname=
set /p sname=请输入简单的文件名称，用于命令访问，最好是简单的单词或字母:
rem [这是代码注释]输出得到的输入信息
echo 您输入的（快捷命令）文件(夹)名为：%sname%

echo 即将把刚刚创建的快捷方式"%~d0\%input%.lnk"移动到C:\Windows目录下
move "%~d0\%input%.lnk" "C:\Windows\%sname%.lnk"

echo 正在隐藏文件，完成后F5刷新文件夹，即可发现文件消失
@pause
attrib +s +a +h "%~d0\%input%"
rem pause>null
echo 按住徽标+R 输入 %sname% 打开文件试试

@pause