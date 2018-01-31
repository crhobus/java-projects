.assembly extern mscorlib {}
.assembly teste{}
.module teste.exe
.class public xD {
.method static public void principal()
{ .entrypoint
.locals (int64 cont)
ldc.i8 0
stloc cont
lb0:
ldloc cont
ldc.i8 10
clt
brfalse lb2
ldstr "    vou parar de contar    "
call void [mscorlib]System.Console::Write(string)
br lb1
lb2:
ldloc cont
ldc.i8 1
add
stloc cont
ldloc cont
call void [mscorlib]System.Console::Write(int64)
br lb0
lb1:
ldstr "Consegui contar"
call void [mscorlib]System.Console::Write(string)
ret
}
}
