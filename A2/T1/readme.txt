Compile

javac -Xlint:unchecked src\library\Application.java src\library\Const.java src\library\contracts\IMenu.java src\library\contracts\IMenuOption.java src\library\entities\Book.java src\library\InputValidator.java src\library\KeyboardInput.java src\library\menu\handlers\AddBook.java src\library\menu\handlers\DeleteBook.java src\library\menu\handlers\DisplayAllBook.java src\library\menu\handlers\ExitProgram.java src\library\menu\handlers\SearchBook.java src\library\menu\Menu.java src\library\menu\MenuOption.java src\library\Persistence.java src\library\repositories\BookRepository.java -d bin

Run

java -cp bin library.Application