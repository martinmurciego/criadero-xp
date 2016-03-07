# Configuración básica #
## Caso de estudio ##
La configuración más sencilla brinda tan sólo la posibilidad de crear un andamiaje de objetos (llamados "beans").
No ofrece configuraciones para ORM ni para Servlets ni nada parecido.
Como ejemplo de andamiaje vamos a suponer que nuestro sistema tiene una clase Activity que representa a las actividades a ejecutar sobre objetos de otra clase, dejándolo en determinado estado y que éstas tienen algunos requisitos sobre el estado de los objetos (clase State).
Dado que estos requisitos son parte de la configuración del sistema, podríamos configurarlos al iniciarse la aplicación u obtenerlas cuando sea necesario desde alguna factory de la siguiente manera:
```
State encendido = new State("Encendido");
State standBy = new State("StandBy");
State apagado = new State("Apagado");
Activity apagar = new Activity("Apagar");
apagar.addRequisito(encendido);
apagar.addRequisito(standBy);
apagar.setDestino(apagado);
```

Básicamente, estamos tratando de implementar una [inyección de dependencias](http://en.wikipedia.org/wiki/Dependency_injection).
Y tan sólo esto es lo que configuraremos como ejemplo de utilización básico del framework Spring.

## Bibliotecas requeridas ##
En primer lugar es necesario añadir al classpath de la aplicación las siguientes bibliotecas:
  * commons-logging.jar
  * org.springframework.asm.jar
  * org.springframework.beans.jar
  * org.springframework.context.jar
  * org.springframework.core.jar
  * org.springframework.expression.jar

Que se incluyen en el paquete [descargable](http://s3.amazonaws.com/dist.springframework.org/milestone/SPR/spring-framework-3.0.0.RC1-with-docs.zip) de Spring, salvo commons-logging, que puede descargarse [desde aquí](http://commons.apache.org/logging/).

## Definición del andamiaje ##
Es necesario indicarle al framework toda la estructura de objetos necesaria por nuestra aplicación. Esto puede lograrse de diversas maneras; pero, por simplicidad, se eligió un archivo XML para ello.
A continuación podemos ver, con algunos comentarios para que se entienda, este XML; aunque, para más información, puede consultarse [el manual en línea (en inglés)](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/) o el manual contenido en la descarga (en varios idiomas, incluso el español).

```
<?xml version="1.0" encoding="UTF-8"?>
<beans
   xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns:p="http://www.springframework.org/schema/p"
   xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

   <!-- Aquí se está definiendo un bean cuyo identificador es "encendido"
        y es del tipo "my.package.State" -->
   <bean id="encendido" class="my.package.State">
      <!-- Le pasamos un parámetro al constructor (si es necesario) -->
      <constructor-arg value="Encendido" />
   </bean>
   <bean id="standBy" class="my.package.State">
      <constructor-arg value="StandBy" />
   </bean>
   <bean id="apagado" class="my.package.State">
      <constructor-arg value="Apagado" />
   </bean>

   <!-- ... --->
   <!-- Otras definiciones de beans -->
   <!-- ... --->

   <bean id="apagar" class="my.package.Activity">
      <constructor-arg value="Apagar" />
      <!-- Además se puede invocar a los setters del bean que se está creando -->
      <property name="destino" ref="apagado" />

      <!-- Y, si algunas propiedades son conjuntos de elementos, no hay problemas -->
      <property name="requisitos">
         <set>
            <!-- Siempre que se haga referencia a otro bean, es por su ID -->
            <ref bean="encendido" />
            <ref bean="standBy" />
         </set>
      </property>
   </bean>
</beans>
```

Debido a la forma en que se accederá luego a esta configuración, debe encontrarse en algún directorio que pueda ser encontrado por el ClassLoader. En nuestro caso, el archivo se llama "activities.xml" y está dentro del directorio "src", en el _default package_.


## Utilización del BeanFactory ##
Con no más configuraciones que las realizadas hasta el momento ya es posible comenzar a utilizar Spring. Si quisiera obtener el bean apagar, no hay más que solicitárselo a un BeanFactory mediante el id con el que se lo registró:

```
BeanFactory beanFactory = new ClassPathXmlApplicationContext("activities.xml");
Activity apagar = beanFactory.getBean("apagar", Activity.class);
```