package dev.pivozavr.jnotunit.core;

import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataProviderBuilder {

    private final List<Object[]> list = new ArrayList<>();

    /**
     * Вызывается после создания объекта dataProvider
     * Добавляет в список параметров еще один массив с параметрами,
     * которые нужно использовать в тесте
     * После объекта и строки со значением, могут быть любые другие аргументы
     *
     * @param args Принимает любое количество любых объектов,
     *             но требуется чтобы все добавляемые массивы имели одинаковое количество аргументов
     * @return
     */
    public DataProviderBuilder add(Object... args) {
        if (args != null) {
            list.add(args);
        } else {
            Object[] m = new Object[1];
            list.add(m);
        }

        return this;
    }

    /**
     * Метод позволяет добавить в список тестов объект, поочередно подставив в каждое поле объекта определенное значение
     * Это удобно, когда нужно проверить каждое поле со значением null или пустой строкой
     *
     * @param object
     * @param value
     * @param args
     * @return
     */
    public DataProviderBuilder addWithApplyValue(Object object, String value, Object... args) {
        ArrayList<Object> objects = applyValueToFields(object, value);
        for (Object o : objects) {
            ArrayList<Object> al = new ArrayList<>();
            al.add(o);
            al.addAll(1, List.of(args));
            Object[] m = al.toArray();
            list.add(m);
        }
        return this;
    }

    /**
     * Вызывается после того как добавлены все массивы параметров для теста
     * Далее Фреймворк использует объект Iterator<Object[]> чтобы запустить тест
     * с каждым массивом параметров
     *
     * @return
     */
    public Stream<Arguments> getStream() {
        return list.stream().map(Arguments::of);
    }

    private <T> ArrayList<T> applyValueToFields(T obj, String value) {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        ArrayList<T> objects = new ArrayList<>();

        for (Field field : fields) {
            if (field.getType() == String.class) {
                try {
                    objects.add(copyAnyObject(obj));
                    field.setAccessible(true);
                    field.set(objects.get(objects.size() - 1), value);
                } catch (IllegalAccessException | IOException | ClassNotFoundException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        return objects;
    }

    private <T> T copyAnyObject(T obj) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        Object newObj = objClass.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(newObj, field.get(obj));
        }
        return (T) newObj;
    }


}
