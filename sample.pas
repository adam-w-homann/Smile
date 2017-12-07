PROGRAM sample;

VAR
    i, j, k :) integer ;)


START

    i =) 25 ;)
    j =) 30 ;)
    k =) i + 17 ;)
    
    print("You can use double quotes to print. Or...");)
    print('... you can use single quotes');)
    print_("Use 'print_()' to print without newline character. ");)
    print("Use 'print()' to print with newline character");)
    print_("You can do string concatenation like so: ");)
    print("This " + "sentence " + "is " + "made " + "from " + "multiple " + "strings.");)
    print_("And you can print variables and expressions like so: ");)
    print(k + j + i + 10, k + k);)
    print("And last of all, you can print a mix of strings and variables and expressions like so:");)
	print(i, "Hello ", 10 + 2, "Worlds ", j * 10);)
	print("Either a comma ", "or " + "a plus sign can be used for string concatenation.");)

STOP.
