javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\gui\draw.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\gui\frame.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\gui\gcstatus.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\gui\gui_starter.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\gui\guiapp1.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\jvm\info.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\test\itest.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\test\test.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\uint\constants.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\uint\u128.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\uint\u16.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\uint\u256.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\uint\u32.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\uint\u64.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\mk\utils.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\base.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\benchmark.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\common.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\test_all.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\test_u128.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\test_u16.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\test_u256.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\test_u32.java || goto :mk_bad
javac -d %~dp0..\build\classes -cp %~dp0..\src %~dp0..\src\test\mk\uint\test_u64.java || goto :mk_bad
goto :mk_gud
:mk_bad
echo Bad.
exit /b %errorlevel%
goto :mk_end
:mk_gud
echo Gud.
goto :mk_end
:mk_end
