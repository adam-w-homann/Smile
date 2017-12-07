.class public sample
.super java/lang/Object

.field private static _runTimer LRunTimer;
.field private static _standardIn LPascalTextIn;

; i,j,k:)integer

.field private static i I
.field private static j I
.field private static k I

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

; k=)i+17

	getstatic	sample/i I
	ldc	17
	iadd
	putstatic	sample/k I

; print("You can use double quotes to print. Or...")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "You can use double quotes to print. Or..."
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print('... you can use single quotes')

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "... you can use single quotes"
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print_("Use 'print_()' to print without newline character. ")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "Use 'print_()' to print without newline character. "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

; print("Use 'print()' to print with newline character")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "Use 'print()' to print with newline character"
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print_("You can do string concatenation like so: ")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "You can do string concatenation like so: "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

; print("This "+"sentence "+"is "+"made "+"from "+"multiple "+"strings.")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "This "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "sentence "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "is "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "made "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "from "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "multiple "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "strings."
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print_("And you can print variables and expressions like so: ")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "And you can print variables and expressions like so: "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V

; print(k+j+i+10,k+k)

; starwhale 2
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	dup
	getstatic	sample/k I
	getstatic	sample/j I
	iadd
	getstatic	sample/i I
	iadd
	ldc	10
	iadd
	invokevirtual java/io/PrintStream/print(I)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           " "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
; starwhale 2
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	dup
	getstatic	sample/k I
	getstatic	sample/k I
	iadd
	invokevirtual java/io/PrintStream/print(I)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           " "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print("And last of all, you can print a mix of strings and variables and expressions like so:")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "And last of all, you can print a mix of strings and variables and expressions like so:"
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print(i,"Hello ",10+2,"Worlds ",j*10)

; starwhale 2
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	dup
	getstatic	sample/i I
	invokevirtual java/io/PrintStream/print(I)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           " "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "Hello "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
; starwhale 2
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	dup
	ldc	10
	ldc	2
	iadd
	invokevirtual java/io/PrintStream/print(I)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           " "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "Worlds "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
; starwhale 2
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	dup
	getstatic	sample/j I
	ldc	10
	imul
	invokevirtual java/io/PrintStream/print(I)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           " "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           ""
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

; print("Either a comma ","or "+"a plus sign can be used for string concatenation.")

	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "Either a comma "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "or "
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic     java/lang/System/out Ljava/io/PrintStream;
	ldc           "a plus sign can be used for string concatenation."
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
