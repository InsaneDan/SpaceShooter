# ![android_logo](https://github.com/InsaneDan/InsaneDan/blob/main/Android.png) Разработка игры на LibGDX

Just to get an impression of what the libGDX is capable of... simple 2D-game built with this
framework.

# SpaceShooter
![https://img.shields.io/badge/Status-WIP-red](https://img.shields.io/badge/Status-WIP-red) ![https://img.shields.io/badge/Realese-No-red](https://img.shields.io/badge/Realese-No-red) ![https://img.shields.io/badge/Version-1.0-yellow](https://img.shields.io/badge/Version-1.0-yellow)

Feel free to download [JAR](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/SpaceShooter_ida-1.0.jar) for desktop or
[APK](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/SpaceShooter_ida-1.0.apk) for Android

----

Build & Run configuration (Gradle):  
Main class name: ru.isakov.space.shooter.game.desktop.DesktopLauncher  
Module name: SpaceShooter.desktop  

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
* TextureRegion (надпись BAD из дефолтного изображения) в исходной цветовой гамме, непрозрачное,
  поверх всех слоев;
* оба изображения центрированы относительно поля приложения.
---
</details>
<details>
<summary>Урок 2. Работа с графикой. Векторная математика. Обработка логики игры.</summary>

<details>
<summary>Задание к уроку </summary>

1. Изучить материал из методички и статьи: https://habr.com/post/131931/
2. Реализовать движение логотипа badlogic (можно свою картинку вставить) при нажатии клавиши
   мыши (touchDown) в точку нажатия на экране и остановку в данной точке.
</details>

**Решение**
- input events - ЛКМ/touchDown;
- объекты (target и follower) используют одну текстуру;
- "центровка" движения объектов и вращения (через origin);
- ротация объектов в противоположных направлениях, 1 оборот за 2 секунды;
- объект follower каждую секунду уменьшается в размере на 20% и возвращается к исходному размеру,
  во процессе изменения размера меняется цвет (в max и min точках - исходный цвет);
- при приближении к цели скорость уменьшается.

![LibGDX_lesson2_homework](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/LibGDX_sps2.gif)

---
</details>
<details>
<summary>Урок 3. Разработка «каркаса» игры</summary>

<details>
<summary>Задание к уроку </summary>

1. Разобраться с темой урока.
2. Адаптировать ДЗ 2 к новой архитектуре проекта. Желательно всю логику которая касается
   обработки логотипа по максимуму разместить в классе Logo
</details>

**Решение**
- на уроке: пересчет координатной сетки границ экрана, границ игрового мира;
- движение объекта с ускорением в том направлении, куда он нацелен, после достижения заданного
  максимума скорость не увеличивается;
- разворот во время движения, полный оборот на 360° выполняется за 1 секунду;
- для вращения выбирается меньший угол;
- при выравнивании угловой скорости и скорости поворота - объект может уйти в бесконечную петлю,
  если не сдвинуть мишень.

![LibGDX_lesson3_homework](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/LibGDX_sps3.gif)

---
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
- класс Rnd убрал – используем com.badlogic.gdx.math.MathUtils.random (а он в свою очередь
  реализует java.util.Random);
- чтобы имитировать вращение элементов кнопок, поле scale в классе Rect разбито на scaleX и scaleY;
- кнопки - общий абстрактный класс ButtonTemplate наследуется от BaseButton, содержит список
  спрайтов элементов (ButtonElement) и "подложку". Параметры ButtonElement могут определять
  разное поведение спрайта. В шаблон передается атлас текстур, цвет кнопки, текст (выбор из
  атласа или пустой), направление вращения и масштаб.
- реализация управления – клавиатура, тачпад;
- небольшая инерционность движения – фактически игрок двигает указатель, за которым следует корабль;
- запрет выхода за пределы границ экрана;
- вынес управление кораблем в отдельный класс.

![LibGDX_lesson4_homework](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/LibGDX_sps4.gif)

---
</details>
<details>
<summary>Урок 5. Доработка игровой логики</summary>

<details>
<summary>Задание к уроку </summary>

1. Разобраться с классами Sound (http://www.libgdx.ru/2013/10/sound-effects.html) и Music
   (http://www.libgdx.ru/2013/10/streaming-music.html) (можно мне вопросы задавать) и
   реализовать фоновую музыку и звуки выстрелов
2. Реализовать автострельбу (подсказка: таймер в update)
</details>

**Решение**
- автострельба через накопительный счетчик deltaTime в методе update (PlayerShip);
- добавлены звуки выстрелов для игрового корабля;
- добавлена фоновая музыка (в основной класс - SpaceShooter).

![LibGDX_lesson5_homework](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/LibGDX_sps5.gif)

---
</details>
<details>
<summary>Урок 6. Управление «экранами»</summary>

<details>
<summary>Задание к уроку </summary>

1. Сделать 2 режима корабля: когда он быстро вылетает на экран и когда начинает двигаться со
   своей скоростью и вести бой.
   Важно чтобы стрельба началась сразу после того как корабль полностью появится на экране
   (сейчас маленькие корабли стреляют в самом конце).
2. * Сделать проверку столкновения вражеского корабля с нашим кораблём и уничтожение вражеского
   корабля.
</details>

**Решение**
* убрал все константы, для настройки параметров вражеских кораблей используются "шаблоны";
* корабли вылетают и начинают стрелять после появления - реализация через начальный вектор
  скорости и проверку границ экрана;
* проверка столкновений (коллизий) не реализована.

![LibGDX_lesson6_homework](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/LibGDX_sps6.gif)

---
</details>
<details>
<summary>Урок 7. Работа с камерой и звуками</summary>

<details>
<summary>Задание к уроку </summary>
Добавить надпись GAME_OVER и кнопку начала новой игры NEW_GAME. При нажатии на кнопку начинать 
игру заново.
</details>

**Решение**
* общий метод для проверки коллизий при попадании пуль реализован в родительском классе BaseShip
- реализация через проверку rect instanceof Bullet и дальше проверяем  bullet.getOwner();
* отображение спрайта GameOver;
* старт новой игры через сброс настроек игровых объектов; второй вариант - с созданием нового
  GameScreen закоментирован (как более затратный по ресурсам).
* добавлены новые корабли и изменена анимация взрыва.

![LibGDX_lesson7_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps7.gif)
![LibGDX_lesson7_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX/SpaceShooter/LibGDX_sps7_2.gif)
---
</details>
<details>
<summary>Урок 8. Финальная доработка проекта</summary>

Задание: Сдать готовый проект игры

![LibGDX_lesson8_homework](https://github.com/InsaneDan/SpaceShooter/blob/master/supplements/LibGDX_sps8.gif)

---
</details>
 
<details>
<summary>Дополнительные материалы</summary>

- https://habr.com/post/131931/ - ЛИНЕЙНАЯ АЛГЕБРА для разработчиков игр
- Официальная документация по LibGDX: https://github.com/libgdx/libgdx/wiki
- Документация по LibGDX на русском языке: http://www.libgdx.ru
- Описание работы с камерой: http://www.libgdx.ru/2013/11/orthographic-camera.html
- Работа с файлами ( в них можно хранить настройки игры): https://github.com/libgdx/libgdx/wiki/File-handling
- Настройки удобно хранить как JSON и для этого есть библиотека GSON: http://developer.alexanderklimov.ru/android/library/gson.php
- Сайт со статьями по Android-разработке: http://developer.alexanderklimov.ru/android/
- Серия статей по LibGDX (немного устаревших): https://www.gamefromscratch.com/page/LibGDX-Tutorial-series.aspx
- Серия статей по LibGDX на русском: http://android-study.ru/uroki-libgdx/
- Изучение Unity: https://unity3d.com/ru/learn?_ga=2.79452052.1010341648.1553797005-1755179663.1553797005
- Счётчики на иконках: https://habr.com/ru/post/117997/
- Реклама в приложении: https://developers.google.com/admob/android/quick-start
</details>
 
<details>
<summary>Бэклог на доработку...</summary>

* вынести все настройки в отдельный конфигурационный файл!
* использовать полигональные формы для проверки коллизий: https://www.codeandweb.com/physicseditor
* избавиться от антипаттерна Magic Number при настройке вражеских кораблей по шаблонам (все параметры в отдельный конфиг. файл? enum?);
* кнопка выхода из игры, пауза, возврат в стартовое меню;
* меню настроек (громкость звуков, сложность и др.);
* power-ups должны появляться при взрыве вражеских кораблей, разлетаются в разных направлениях (в сторону корабля игрока, не улетают за пределы экрана, исчезают по таймауту) - щиты, ракеты, аптечки, доп.оружие (вторичное/третичное), оружие с повышенным уроном;
* траектория движения вражеских кораблей (чтобы могли разворачиваться и сохранять направление движения, при этом стрелять в сторону игрока);
* возможность задать очередность появления вражеских кораблей (в EnemyEmitter);
* разные типы снарядов с различной траекторией движения: в сторону корабля игрока, "самонаводящиеся", "разрывные", множественные (несколько одновременно) и др.
* первичное, вторичное и третичное оружие;
* уровень с боссом (с различными типами оружия);
* уровни сложности (background, типы кораблей, оружие)
* бесконечный режим;
* индикатор HP для вражеских кораблей (должен отображаться горизонтально, даже если корабль разворачивается);
* навигация по кнопкам (подсвечивать активную?) или управление с клавиатуры: старт новой игры - Enter (newGameButton), выход в меню - Esc (menuButton), вход в настройки и т.д.;
* список ТОП-игроков (с синхронизацией между устройствами);
* перерисовать все спрайты в едином стиле
</details>


<details>
<summary>Details</summary>

Geekbrains  
Преподаватель: Алексей Кутепов  
Дата проведения: 23.08.2021–16.09.2021
</details>
