# SI_2025_lab2_193284
Јана Трпковска 193284

## 2. Control Flow Graph (CFG)
На сликата е прикажан CFG за функцијата `checkCart` : 
![CFG](CFG_checkCart.jpg)



## 3. Цикломатска комплексност
Формула:
M = E - N + 2P или M = D + 1, D = бр. на услови

Во `checkCart` има 8 услови.
2 for loops, 6 if-услови = D

М = 8+1 = 9

Потребни се минимум 9 независни тест патеки за да се покрие целата логика на функцијата.


## 4. Тест случаи според Every Statement критериум
Целта е да ги покриеме сите редови од кодот барем еднаш. Тест примери:

1. Ако листата со предмети е `null`, треба да се фрли исклучок.
Пример: `checkCart(null, "1234567890123456")`
-> Очекуван резултат: RuntimeException за `allItems list can't be null`

2. Ако некој предмет нема име, исто така треба да фрли исклучок.
Пример: `Item(null, 1, 100, 0)`
-> Очекуван резултат: RuntimeException за "Invalid item"

3. Ако има попуст, да се пресмета сумата со попуст.
Пример: `Item("A", 1, 100, 0.2)`
->Очекуван резултат: 80.0

4. Ако нема попуст, сумата е нормално `price * quantity`.
Пример: `Item("B", 2, 50, 0)`
-> Очекуван резултат: 100.0

5. Ако некој предмет има количина > 10 или цена > 300, се одзема 30 од вкупната сума.
Пример: `Item("C", 11, 10, 0)`
-> Очекуван резултат: (11*10) - 30 = 80.0

6. Ако картичката не е 16 цифри или е null, треба да се фрли исклучок.
Пример: `checkCart(..., "123")`
-> Очекуван резултат: RuntimeException за "Invalid card number"

7. Ако има невалиден знак во картичката (пример буква), исто така треба да фрли.
Пример: `"12345678x0123456"`
-> Очекуван резултат: RuntimeException за "Invalid character in card number"

8. Пример со целосно валидни вредности
-> Очекуван резултат: пресметана сумарна вредност

9. Комбиниран пример со попуст + услов за одземање 30
-> Проверува повеќе редови заедно

За да се покрие секој ред, потребни се минимум 9 различни тест примери.


## 5. Multiple Condition тестирање

Овој услов има три проверки:

`if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)`

За да го покриеме по Multiple Condition критериум, треба да ги тестираме сите можни комбинации.
Ги направив следниве примери:

1. Ниту еден од условите не е исполнет -> не се одзема 30
Пример: цена = 100, попуст = 0, количина = 1

2. Само цената е > 300 -> се одзема 30
Пример: цена = 400, попуст = 0, количина = 1

3. Само попуст -> се одзема 30
Пример: Цена = 100, попуст = 0.2, количина = 1

4. Само количината е > 10 -> се одзема 30
Пример: цена = 100, попуст = 0, количина = 12

5. Цена и попуст се исполнети -> се одзема 30
Пример: 400, 0.1, 1

6. Цена и количина се исполнети -> се одзема 30
Пример: 400, 0, 15

7. Попуст и количина се исполнети -> се одзема 30
Пример: 100, 0.3, 11

8. Сите три се исполнети -> се одзема 30
Пример: 400, 0.5, 20

Затоа што има 3 услови поврзани со `||`, имаме 2^n комбинации, 2^3 = 8 комбинации.
Затоа се потребни 8 тест случаи за да се покрие овој услов комплетно.


## 6. Unit тестирање со JUnit

Во оваа точка се бара автоматско тестирање на функцијата `checkCart`, односно да провериме дали правилно се извршува за различни ситуации. Ова е корисно затоа што ни овозможува да не го тестираме кодот рачно секој пат, туку Gradle сам ги извршува сите тестови.

Тестирањето е поделено во 2 дела:
1. Every Statement критериум: каде што целта е да се помине секој ред од кодот барем еднаш. Затоа напишавме тестови кои што покриваат: кога листата е null, кога предмет нема име, кога има попуст и кога картичката е невалидна.
2. Multiple Condition критериум: овде ги тестираме сите можни комбинации од условот:
`item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10`.

Бидејќи има 3 услови, тоа значи 2^3 = 8 можни комбинации, затоа и има 8 тестови.
Тестовите се напишани во фајлот `SILab2Test.java`, кој се наоѓа во `src/test/java/`. Секој тест е означен со `@Test`, и користи `assertEquals` или `assertThrows` за да се провери дали вратениот резултат е точен или дали правилно се фрла грешка.

Кога се извршува командата: 
./gradlew test, 
Gradle сам ги стартува сите тестови и враќа резултат. 
Кај мене тестовите поминаа успешно: 
BUILD SUCCESSFUL in 15s
3 actionable tasks: 3 up-to-date

Ова значи дека функцијата `checkCart()` ги враќа точните резултати за сите сценарија што ги тестиравме.


## 7. Сите барања од задачата се комплетно исполнети: имплементиран е Gradle Java проект со checkCart функција, unit тестови, CFG дијаграм, пресметка за цикломатска комплексност и покриени тест сценарија според зададените критериуми.