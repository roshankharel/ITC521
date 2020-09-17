## Compile


```sh
javac -d bin src/library/Application.java src/library/Const.java src/library/contracts/IMenu.java src/library/contracts/IMenuOption.java src/library/entities/Book.java src/library/KeyboardInput.java src/library/menu/handlers/AddBook.java src/library/menu/handlers/DeleteBook.java src/library/menu/handlers/DisplayAllBook.java src/library/menu/handlers/ExitProgram.java src/library/menu/handlers/SearchBook.java src/library/menu/Menu.java src/library/menu/MenuOption.java src/library/Persistence.java src/library/repositories/BookRepository.java src/library/validation/errors/FieldRequiredError.java src/library/validation/errors/ISBNFormatError.java src/library/validation/errors/NegativeIntegerError.java src/library/validation/errors/OptionNotInRange.java src/library/validation/errors/YearFormatError.java src/library/validation/Validator.java
```

## Run

```sh
java -cp bin library.Application
```