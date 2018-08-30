package org.aion.avm.core.shadowing.testNio;

import org.aion.avm.core.SimpleAvm;
import org.aion.avm.core.miscvisitors.NamespaceMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NioShadowingTest {
    private SimpleAvm avm;
    private Class<?> clazz;

    @Before
    public void setup() throws ClassNotFoundException {
        this.avm = new SimpleAvm(10000L, TestResource.class);
        this.clazz = avm.getClassLoader().loadUserClassByOriginalName(TestResource.class.getName());
    }

    @After
    public void teardown() {
        this.avm.shutdown();
    }

    @Test
    public void testByteBuffer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object obj = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(NamespaceMapper.mapMethodName("testByteBuffer"));

        Object ret = method.invoke(obj);
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testCharBuffer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object obj = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(NamespaceMapper.mapMethodName("testCharBuffer"));

        Object ret = method.invoke(obj);
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testDoubleBuffer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object obj = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(NamespaceMapper.mapMethodName("testCharBuffer"));

        Object ret = method.invoke(obj);
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testFloatBuffer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object obj = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(NamespaceMapper.mapMethodName("testCharBuffer"));

        Object ret = method.invoke(obj);
        Assert.assertEquals(ret, true);
    }


    @Test
    public void testIntBuffer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object obj = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(NamespaceMapper.mapMethodName("testCharBuffer"));

        Object ret = method.invoke(obj);
        Assert.assertEquals(ret, true);
    }


    @Test
    public void testLongBuffer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object obj = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(NamespaceMapper.mapMethodName("testCharBuffer"));

        Object ret = method.invoke(obj);
        Assert.assertEquals(ret, true);
    }

    @Test
    public void testShortBuffer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object obj = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod(NamespaceMapper.mapMethodName("testCharBuffer"));

        Object ret = method.invoke(obj);
        Assert.assertEquals(ret, true);
    }
}
