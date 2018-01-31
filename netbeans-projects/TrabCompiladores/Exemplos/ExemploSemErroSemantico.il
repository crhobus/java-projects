.assembly extern mscorlib {}
.assembly ExemploSemErroSemantico{}
.module ExemploSemErroSemantico.exe
.class public quadrado {
.method static public void principal()
{ .entrypoint
.locals (int64 area, int64 s, int64 lado)
call string[mscorlib]System.Console::ReadLine()
call int64[mscorlib]System.Int64::Parse(string)
stloc lado
ldloc lado
ldloc lado
 mul 
stloc area
ldloc area
call void [mscorlib]System.Console::Write(int64)
call string[mscorlib]System.Console::ReadLine()
call int64[mscorlib]System.Int64::Parse(string)
stloc s
ret
}
}
