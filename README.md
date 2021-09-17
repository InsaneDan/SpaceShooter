# ![android_logo](https://github.com/InsaneDan/InsaneDan/blob/main/Android.png) Разработка игры на LibGDX
<details>
<summary>Курс Geekbrains</summary>

Преподаватель: Алексей Кутепов  
Дата проведения: 23.08.2021–16.09.2021
</details>

# SpaceShooter
![https://img.shields.io/badge/Status-WIP-red](https://img.shields.io/badge/Status-WIP-red) ![https://img.shields.io/badge/Realese-No-red](https://img.shields.io/badge/Realese-No-red)

Just to get an impression of what the libGDX is capable of... simple 2D-game built with this
framework.  
SubProjects for Desktop, Android, iOS and Html are planned.



<details>
<summary>Урок 1. Установка и настройка инструментов разработки</summary>

<details>
<summary>Задание к уроку </summary>

1. Установить все необходимые инструменты
2. Создать проект и убедиться что он запускается
3. Залить проект на GitHub
4. Создать новую ветку
5. Выбрать картинку для фона и отрисовать. Изменения выполнить в новой ветке
6. Сделать pull-request к ветке master
7. Сдать ДЗ в виде pull-request
</details>

**Решение**
1) Размеры экрана приложения заданы через LwjglApplicationConfiguration config.
2) настройка setColor и позиционирование:
* дефолтное изображение (badlogic.jpg) в синем цвете и прозрачное;
* TextureRegion (надпись BAD из дефолтного изображения) в исходной цветовой гамме, непрозрачное, поверх всех слоев;
* оба изображения центрированы относительно поля приложения.
</details>
 
<details>
<summary>Урок 2. Работа с графикой. Векторная математика. Обработка логики игры.</summary>

<details>
<summary>Задание к уроку </summary>

1. Изучить материал из методички и статьи: https://habr.com/post/131931/
2. Реализовать движение логотипа badlogic (можно свою картинку вставить) при нажатии клавиши мыши (touchDown) в точку нажатия на экране и остановку в данной точке.
</details>

**Решение**
- input events - ЛКМ/touchDown;
- объекты (target и follower) используют одну текстуру;
- "центровка" движения объектов и вращения (через origin);
- ротация объектов в противоположных направлениях, 1 оборот за 2 секунды;
- объект follower каждую секунду уменьшается в размере на 20% и возвращается к исходному размеру, во процессе изменения размера меняется цвет (в max и min точках - исходный цвет);
- при приближении к цели скорость уменьшается – реализовано через расчет координат.

**! TODO:** Pасcчитать через взаимодействие (сложение) векторов - положение и ускорение - не получилось.

![LibGDX_lesson2_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps2.gif)

</details>
 
<details>
<summary>Урок 3. Разработка «каркаса» игры</summary>

<details>
<summary>Задание к уроку </summary>

1. Разобраться с темой урока.
2. Адаптировать ДЗ 2 к новой архитектуре проекта. Желательно всю логику которая касается обработки логотипа по максимуму разместить в классе Logo
</details>

**Решение**
- на уроке: пересчет координатной сетки границ экрана, границ игрового мира;
- движение объекта с ускорением в том направлении, куда он нацелен, после достижения заданного максимума скорость не увеличивается;
- разворот во время движения, полный оборот на 360° выполняется за 1 секунду;
- для вращения выбирается меньший угол;
- при выравнивании угловой скорости и скорости поворота - объект может уйти в бесконечную петлю, если не сдвинуть мишень.

![LibGDX_lesson2_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps3.gif)

</details>
 
<details>
<summary>Урок 4. Оптимизация проекта</summary>

<details>
<summary>Задание к уроку </summary>

1. Реализовать спрайт корабля
2. Разрезать текстуру корабля на 2 части
3. Cделать управление кораблём с помощью тача и/или клавиатуры
4. *** Сделать ограничение движения корабля
</details>

**Решение**
- MenuScreen - добавлены пролетающие кометы и кнопки;
- класс Rnd убрал – используем com.badlogic.gdx.math.MathUtils.random (а он в свою очередь реализует java.util.Random);
- чтобы имитировать вращение элементов кнопок, поле scale в классе Rect разбито на scaleX и scaleY;
- кнопки - общий абстрактный класс ButtonTemplate наследуется от BaseButton, содержит список спрайтов элементов (ButtonElement) и "подложку". Параметры ButtonElement могут определять разное поведение спрайта. В шаблон передается атлас текстур, цвет кнопки, текст (выбор из атласа или пустой), направление вращения и масштаб.
- реализация управления – клавиатура, тачпад;
- небольшая инерционность движения – фактически игрок двигает указатель, за которым следует корабль;
- запрет выхода за пределы границ экрана;
- вынес управление кораблем в отдельный класс.

![LibGDX_lesson3_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps4.gif)

</details>
 
<details>
<summary>Урок 5. Доработка игровой логики</summary>

<details>
<summary>Задание к уроку </summary>

1. Разобраться с классами Sound(http://www.libgdx.ru/2013/10/sound-effects.html) и Music(http://www.libgdx.ru/2013/10/streaming-music.html) (можно мне вопросы задавать) и реализовать фоновую музыку и звуки выстрелов
2. Реализовать автострельбу (подсказка: таймер в update)
</details>

**Решение**
- автострельба через накопительный счетчик deltaTime в методе update (PlayerShip);
- добавлены звуки выстрелов для игрового корабля;
- добавлена фоновая музыка (в основной класс - SpaceShooter).

![LibGDX_lesson4_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps5.gif)

</details>
 
<details>
<summary>Урок 6. Управление «экранами»</summary>

<details>
<summary>Задание к уроку </summary>

1. Сделать 2 режима корабля: когда он быстро вылетает на экран и когда начинает двигаться со своей скоростью и вести бой. 
   Важно чтобы стрельба началась сразу после того как корабль полностью появится на экране 
   (сейчас маленькие корабли стреляют в самом конце).
2. * Сделать проверку столкновения вражеского корабля с нашим кораблём и уничтожение вражеского корабля.
</details>

**Решение**
* убрал все константы, для настройки параметров вражеских кораблей используются "шаблоны";
* корабли вылетают и начинают стрелять после появления - реализация через начальный вектор скорости и проверку границ экрана; 
* проверка столкновений (коллизий) не реализована.

![LibGDX_lesson4_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps6.gif)

</details>
 
<details>
<summary>Урок 7. Работа с камерой и звуками</summary>

<details>
<summary>Задание к уроку </summary>
Добавить надпись GAME_OVER и кнопку начала новой игры NEW_GAME. При нажатии на кнопку начинать игру заново.
</details>

**Решение**
* общий метод для проверки коллизий при попадании пуль реализован в родительском классе BaseShip - реализация через проверку rect instanceof Bullet и дальше проверяем  bullet.getOwner();


![LibGDX_lesson4_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps7.gif)

</details>
 
<details>
<summary>Урок 8. Финальная доработка проекта</summary>

<details>
<summary>Задание к уроку </summary>

</details>

**Решение**

![LibGDX_lesson4_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps8.gif)

</details>

