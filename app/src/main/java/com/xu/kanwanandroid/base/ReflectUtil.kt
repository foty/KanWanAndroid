
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Create by lxx
 * Date : 2020/12/23 10:02
 * Use by
 */
object ReflectUtil {

    /**
     * 返回第一个泛型类型,适用单泛型情况，多泛型类型注意泛型排列顺序
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getGenericClass(o: Any): Class<T> {
        //获取类泛型的类型
        val type = o.javaClass.genericSuperclass as ParameterizedType
        return type.actualTypeArguments[0] as Class<T>
    }

    /**
     * 返回所有的泛型数组
     */
    fun getGenericClassList(o: Any): Array<out Type> {
        val type = o.javaClass.genericSuperclass as ParameterizedType
        return type.actualTypeArguments
    }


}