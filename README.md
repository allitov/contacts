# Contacts
**Приложение для сохранения и просмотра контактных данных.**

Приложение предоставляет возможности по сохранению и просмотру контактов, 
а также загрузки данных из файлов, указанных пользователем.

## Настройки приложения
Настройки приложения осуществляются путем изменения значений свойств в файле **application.properties**:

- **spring.profiles.active**:
    - _default_ - программа работает без предварительного чтения данных о контактах из файла.
    - _init_ - перед началом работы программа читает данные о контактах из файла пользователя. При этом данные должны быть в формате: "Ф. И. О;номер телефона;адрес электронной почты" (без кавычек, с символом переноса строки в конце).
- **app.saving-file.path** - путь до файла сохранения контактов.
- **app.loading-file.path** - путь до файла чтения контактов.

## Интерфейс и формат ввода
Главное меню состоит из 4 пунктов (для выбора нужно ввести в консоль число без пробелов и иных символов,
после чего нажать клавишу Enter):
1. Приложение выводит в консоль все данные, введенные пользователем или считанные из файла.
2. Приложение добавит контакт для последующего сохранения в файл. Формат ввода (без кавычек): 
"Ф. И. О.; номер телефона; адрес электронной почты"
3. Приложение удалит из памяти контакт по введенному email и не сохранит данные о нем в файл в последующем.
Формат ввода (без кавычек): "email@example.example"
4. Выход из программы. Приложение осуществит запись данных в файл и завершит работу.
