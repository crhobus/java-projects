.assembly extern mscorlib {}
.assembly E:\Documetos\EclipseProjects\TrabCompiladores\Exemplos\ex1{}
.module E:\Documetos\EclipseProjects\TrabCompiladores\Exemplos\ex1.exe
.class public quadrado {
.method static public void principal()
{ .entrypoint
ldc.i8 1
ldc.i8 2
clt
brfalse lb0
ldc.i8 1
call void [mscorlib]System.Console::Write(int64)
br lb1
lb0:
ldc.i8 2
call void [mscorlib]System.Console::Write(int64)
lb1:
ret
}
}
