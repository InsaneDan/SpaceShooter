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

![LibGDX_lesson2_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX_sps2.gif)

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

![LibGDX_lesson2_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX_sps3.gif)

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

![LibGDX_lesson2_homework](https://github.com/InsaneDan/InsaneDan/blob/main/LibGDX_sps4.gif)

</details>

