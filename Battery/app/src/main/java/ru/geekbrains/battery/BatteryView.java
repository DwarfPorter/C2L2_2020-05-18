package ru.geekbrains.battery;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class BatteryView extends View {

    // Константы
    // Отступ элементов
    private final static int padding = 10;
    // Скругление углов батареи
    private final static int round = 5;
    // Ширина головы батареи
    private final static int headWidth = 10;

    // Цвет батареи
    private int batteryColor = Color.GRAY;
    // Цвет уровня заряда
    private int levelColor = Color.GREEN;
    // Цвет уровня заряда при нажатии +
    private int levelPressedColor = Color.RED;
    // Уровень заряда
    private int level = 100;

    private RectF batteryRectangle = new RectF();
    // Изображение уровня заряда
    private Rect levelRectangle = new Rect();
    // Изображение головы батареи
    private Rect headRectangle = new Rect();
    // "Краска" батареи
    private Paint batteryPaint;
    // "Краска" заряда
    private Paint levelPaint;
    // "Краска" уровня заряда при касании +
    private Paint levelPressedPaint;
    // Ширина элемента
    private int width = 0;
    // Высота элемента
    private int height = 0;

    // Касаемся элемента +
    private boolean pressed = false;

    public BatteryView(Context context) {
        super(context);
        init();
    }

    // Вызывается при добавлении элемента в макет
    // AttributeSet attrs - набор параметров, указанных в макете для этого
    // элемента
    public BatteryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями
    // AttributeSet attrs - набор параметров, указанных в макете для этого
    // элемента
    // int defStyleAttr - базовый установленный стиль
    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        init();
    }

    // Вызывается при добавлении элемента в макет с установленными стилями
    // AttributeSet attrs - набор параметров, указанных в макете для этого
    // элемента
    // int defStyleAttr - базовый установленный стиль
    // int defStyleRes - ресурс стиля, если он не был определён в предыдущем параметре
    public BatteryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
        init();
    }

    // Начальная инициализация полей класса
    private void init() {
        batteryPaint = new Paint();
        batteryPaint.setColor(batteryColor);
        batteryPaint.setStyle(Paint.Style.FILL);
        levelPaint = new Paint();
        levelPaint.setColor(levelColor);
        levelPaint.setStyle(Paint.Style.FILL);
        levelPressedPaint = new Paint();
        levelPressedPaint.setColor(levelPressedColor);
        levelPressedPaint.setStyle(Paint.Style.FILL);
    }

    // Инициализация атрибутов пользовательского элемента из xml
    private void initAttr(Context context, @Nullable AttributeSet attrs) {
        // При помощи этого метода получаем доступ к набору атрибутов.
        // На выходе - массив со значениями
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BatteryView, 0, 0);

        // Чтобы получить какое-либо значение из этого массива,
        // надо вызвать соответствующий метод и передать в этот метод имя
        // ресурса, указанного в файле определения атрибутов (attrs.xml)
        batteryColor = typedArray.getColor(R.styleable.BatteryView_battery_color, Color.GRAY);

        // Вторым параметром идёт значение по умолчанию. Оно будет подставлено,
        // если атрибут не будет указан в макете
        levelColor = typedArray.getColor(R.styleable.BatteryView_level_color, Color.GREEN);

        // Обратите внимание, что первый параметр пишется особым способом -
        // через подчёркивания. Первое слово означает имя определения:
        // <declare-styleable name="BatteryView">,
        // следующее слово - имя атрибута:
        // <attr name="level" format="integer" />
        level = typedArray.getInteger(R.styleable.BatteryView_level, 100);

        levelPressedColor = typedArray.getColor(R.styleable.BatteryView_level_pressed_color, Color.RED);

        // В конце работы дадим сигнал, что массив со значениями атрибутов
        // больше не нужен. Система в дальнейшем будет переиспользовать этот
        // объект, и мы больше не получим к нему доступ из этого элемента
        typedArray.recycle();
    }

    // Когда Android создаёт пользовательский экран, ещё не известны размеры
    // элемента, потому что используются расчётные единицы измерения: чтобы
    // элемент выглядел одинаково на разных устройствах, размер элемента
    // рассчитывается с учётом размера экрана, его разрешения и плотности
    // пикселей. Каждый раз при отрисовке экрана возникает необходимость
    // изменить размер элемента. Если нам надо нарисовать свой элемент,
    // переопределяем этот метод и задаём новые размеры элементов внутри View
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Получаем реальные ширину и высоту
        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingTop() - getPaddingBottom();

        // Отрисовка батареи
        batteryRectangle.set(padding,
                padding,
                width - padding - headWidth,
                height - padding);
        headRectangle.set(width - padding - headWidth,
                2 * padding,
                width - padding,
                height - 2 * padding);
        levelRectangle.set(2 * padding,
                2 * padding,
                (int)((width - 2 * padding - headWidth)*((double)level/(double)100)),
                height - 2 * padding);
    }

    // Вызывается, когда надо нарисовать элемент
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(batteryRectangle, round, round, batteryPaint);
        if (pressed){
            canvas.drawRect(levelRectangle, levelPressedPaint);
        } else {
            canvas.drawRect(levelRectangle, levelPaint);
        }
        canvas.drawRect(headRectangle, batteryPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Получаем действие (касание, отпускание, перемещение и т. д.)
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            pressed = true;
        } else if (action == MotionEvent.ACTION_UP){
            pressed = false;
        }

        invalidate();
        return true;
    }
}
