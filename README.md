# PC-CasoPractico3
https://github.com/gechtwitchaa/PC-CasoPractico3.git

# Miembros del grupo
- Chahla Bouchotroch
-  Alberto González Olmedo

---

# Descripcion del proyecto
Este proyecto es una pequeña simulación del procesamiento concurrente de pedidos 
en una tienda online hecha con Spring Boot. Hemos usado @Async para manejar varios 
pedidos en paralelo y también AOP para separar tareas como la auditoría,
medir tiempos y controlar errores sin ensuciar la lógica principal.
Cada pedido se procesa en un hilo independiente y se registran eventos como inicio, 
fin, tiempo de ejecución y posibles fallos.

---
# Logica general de la solucion
El sistema genera 10 pedidos simultaneos desde la clase principal, cada pedido se envia 
al servicio OrderService, donde se procesa en un hilo con @Async. Los aspectos AOP interceptan los 
métodos anotados, registrando un inicio y fin del proceso, calculando el tiempo total y
registrando los posibles errores. Algunos pedidos fallan de forma aleatoria simulando 
problemas reales como "pago rechazado". Finalmente, se imprime por consola un resumen del procesamiento.

---

# Estructura del proyecto

**orders/**
- Order.java → Clase que representa un pedido con id, total y nombre del cliente.

**annotations/**
- Auditable.java → Anotación personalizada para marcar métodos que deben ser auditados.

**service/**
- OrderService.java → Servicio que procesa los pedidos de forma concurrente usando `@Async`, simulando tiempos de espera y posibles errores.

**aspects/**
- LoggingAspect.java → Aspecto AOP que registra el inicio y fin de cada pedido anotado.

**AopOrdersApplication**
- Clase principal de Spring Boot. Lanza la simulación creando varios pedidos y enviándolos al servicio para su procesamiento concurrente.

---
