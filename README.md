# DigitGenerator
Library to handle digits of CPF, CNPJ, NUP and Generics

## Example
```
try {
	new CPFGenerator().check(new CPFNumber("11111111111"));
	System.out.println("This is a valid number");
} catch (InvalidNumberException e) {
	System.out.println(e.getMessage());
} catch (InvalidFormatException e) {
	System.out.println(e.getMessage());
}
```