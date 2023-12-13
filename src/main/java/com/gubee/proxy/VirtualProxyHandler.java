package com.gubee.proxy;

import com.gubee.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.function.Supplier;


/*  Essa classe é genérica e pode ter que instanciar um banco de dados de verdade,
 por isso a inicialização da instância do banco com um Supplier.
*/
public final class VirtualProxyHandler<S>
        implements InvocationHandler, Serializable {

    private final Supplier<? extends S> supplier;

    private S subject;

    public VirtualProxyHandler(Supplier<? extends S> supplier) {
        this.supplier = supplier;
    }

    private S getSubject() {
        if (subject == null) subject = supplier.get();
        return subject;

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Transactional.class)) {
            try {
                System.out.println("\nIniciando execução do método: " + method.getName());
                method.invoke(getSubject(), args);
                System.out.println("Finalizando execução do método " + method.getName() + "com sucesso");
            } catch (Exception e) {
                System.err.println("Finalizando execução do método " + method.getName() + " com erro");
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <S> S virtualProxy(
            Class<? super S> subjectInterface,
            Supplier<? extends S> subjectSupplier) {
        //  Objects.requireNonNull(subjectSupplier, "supplier==null");
        return castProxy(subjectInterface,
                new VirtualProxyHandler<>(subjectSupplier));
    }

    @SuppressWarnings("unchecked")
    private static <S> S castProxy(Class<? super S> intf, InvocationHandler handler) {
        Objects.requireNonNull(intf, "intf==null");
        Objects.requireNonNull(handler, "handler==null");
        return (S) Proxy.newProxyInstance(
                intf.getClassLoader(),
                new Class<?>[]{intf},
                handler);
    }

}
