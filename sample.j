.class public sample
.super java/lang/Object

.field private static _runTimer LRunTimer;
.field private static _standardIn LPascalTextIn;

; i,j:)integer

.field private static i I
.field private static j I

.method public <init>()V

	aload_0
	invokenonvirtual    java/lang/Object/<init>()V
	return

.limit locals 1
.limit stack 1
.end method

.method public static main([Ljava/lang/String;)V

	new RunTimer
	dup
	invokenonvirtual RunTimer/<init>()V
	putstatic        sample/_runTimer LRunTimer;
	new PascalTextIn
	dup
	invokenonvirtual PascalTextIn/<init>()V
	putstatic        sample/_standardIn LPascalTextIn;

; i=)25

	ldc	25
	putstatic	sample/i I

; j=)30

	ldc	30
	putstatic	sample/j I

; print('Hello Garrick, you da man')

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "Hello Garrick, you da man"
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print_("Hello Nosa")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "Hello Nosa"
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

; print("hi"+"yo")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "hi"
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "yo"
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

	getstatic     sample/_runTimer LRunTimer;
	invokevirtual RunTimer.printElapsedTime()V

	return

.limit locals 16
.limit stack 16
.end method
