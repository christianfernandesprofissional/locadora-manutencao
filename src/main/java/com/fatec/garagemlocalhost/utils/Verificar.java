/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fatec.garagemlocalhost.utils;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 *
 * @author Christian
 */
public class Verificar {

    /**
     * Este método verifica se todos os atributos de uma classe estão
     * preenchidos com algum valor através dos métodos Get da classe passada como
     * parâmetro. 
     * Caso queira excluir algum metodo "get..." dessa verificação, basta
     * passar o nome do metodo.
     *
     * @author Christian
     * @param <T>
     * @param obj
     * @return Boolean
     */
    public static <T> boolean todosAtributosPreenchidos(T obj) {
        if (obj == null) {
            return false;
        }

        Class<?> clazz = obj.getClass();
        Method[] metodos = clazz.getMethods();

        for (Method metodo : metodos) {
            if (metodo.getName().startsWith("get")
                    && metodo.getParameterCount() == 0
                    && !metodo.getName().equals("getClass")) {

                try {
                    Object valor = metodo.invoke(obj);

                    if (valor == null) {
                        return false;
                    }

                    if (valor instanceof String && ((String) valor).isBlank()) {
                        return false;
                    }

                    if (valor instanceof Collection && ((Collection<?>) valor).isEmpty()) {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false; // erro ao acessar getter → consideramos como não preenchido
                }
            }
        }

        return true; // todos os getters retornaram valores válidos
    }

    /**
     * Este método verifica se todos os atributos de uma classe estão
     * preenchidos com algum valor através dos métodos Get da classe passada como
     * parâmetro. 
     * Caso queira excluir algum metodo "get..." dessa verificação, basta
     * passar o nome do metodo.
     *
     * @author Christian
     * @param <T>
     * @param obj
     * @param metodoExcluidoDaVerificacao
     * @return Boolean
     */
    public static <T> boolean todosAtributosPreenchidos(T obj, String metodoExcluidoDaVerificacao) {
        if (obj == null) {
            return false;
        }

        Class<?> clazz = obj.getClass();
        Method[] metodos = clazz.getMethods();

        for (Method metodo : metodos) {
            if (metodo.getName().startsWith("get")
                    && metodo.getParameterCount() == 0
                    && !metodo.getName().equals("getClass")
                    && !metodo.getName().equals(metodoExcluidoDaVerificacao)) {

                try {
                    Object valor = metodo.invoke(obj);

                    if (valor == null) {
                        return false;
                    }

                    if (valor instanceof String && ((String) valor).isBlank()) {
                        return false;
                    }

                    if (valor instanceof Collection && ((Collection<?>) valor).isEmpty()) {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false; // erro ao acessar getter → consideramos como não preenchido
                }
            }
        }

        return true; // todos os getters retornaram valores válidos
    }
}
