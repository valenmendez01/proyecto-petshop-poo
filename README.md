# 🐾 PetShop Management System

Sistema integral de gestión para tiendas de mascotas desarrollado en Java con interfaz gráfica Swing y persistencia de datos en archivos de texto.

## 📋 Descripción

Sistema completo para la administración de una tienda de mascotas que permite gestionar clientes, mascotas, historiales médicos, citas veterinarias, inventario de productos, ventas y pedidos a proveedores. Desarrollado como proyecto académico aplicando principios de Programación Orientada a Objetos.

## ✨ Características Principales

### 👥 Gestión de Clientes y Mascotas
- Registro completo de clientes con datos de contacto
- Administración de mascotas asociadas a cada cliente
- Historiales médicos detallados por mascota
- CRUD completo (Crear, Leer, Actualizar, Eliminar)

### 📅 Sistema de Citas
- Agendamiento de turnos veterinarios
- Múltiples tipos de servicios:
  - **Cirugía**: con opción de anestesia
  - **Veterinaria**: consultas regulares y urgencias
  - **Peluquería**: corte, baño y corte de uñas
  - **Paseo**: individual o grupal
- Cálculo automático de costos según especie y servicios adicionales

### 🛒 Gestión de Ventas
- Registro de ventas con múltiples productos
- Diferentes métodos de pago
- Cálculo automático de totales
- Historial completo de transacciones

### 📦 Control de Inventario
- Dos tipos de productos:
  - **Alimentos para mascotas**: con información nutricional
  - **Artículos varios**: accesorios y juguetes
- Gestión de stock en tiempo real
- Sistema de pedidos a proveedores

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java 21
- **IDE**: IntelliJ IDEA
- **Interfaz Gráfica**: Java Swing
- **Persistencia**: Archivos de texto (.txt)
- **Arquitectura**: Programación Orientada a Objetos

## 📁 Estructura del Proyecto

```
proyecto-petshop-poo/
│
├── src/
│   ├── main/
│   │   └── SistemaPetShop.java          # Clase principal con GUI
│   │
│   ├── modelo/
│   │   ├── cliente/
│   │   │   ├── Cliente.java             # Gestión de clientes
│   │   │   ├── Mascota.java             # Gestión de mascotas
│   │   │   └── HistorialMedico.java     # Historiales médicos
│   │   │
│   │   ├── agenda/
│   │   │   └── Turno.java               # Sistema de citas
│   │   │
│   │   ├── servicios/
│   │   │   ├── Servicio.java            # Clase abstracta
│   │   │   ├── Cirugia.java
│   │   │   ├── Veterinaria.java
│   │   │   ├── Peluqueria.java
│   │   │   └── Paseo.java
│   │   │
│   │   ├── producto/
│   │   │   ├── Producto.java            # Clase abstracta
│   │   │   ├── AlimentoMascota.java
│   │   │   └── ArticuloVarios.java
│   │   │
│   │   └── venta/
│   │       ├── Venta.java
│   │       ├── Pedido.java
│   │       ├── ItemVenta.java
│   │       └── ItemPedido.java
│   │
│   └── datos/                           # Archivos de persistencia
│       ├── clientes.txt
│       ├── mascotas.txt
│       ├── historiales.txt
│       ├── turnos.txt
│       ├── productos.txt
│       ├── ventas.txt
│       └── pedidos.txt
│
└── README.md
```

## 🚀 Instalación y Uso

### Requisitos Previos
- Java JDK 21 o superior
- IDE compatible con Java (IntelliJ IDEA recomendado)

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/petshop-management-system.git
cd petshop-management-system
```

2. **Abrir en el IDE**
- Abrir el proyecto en IntelliJ IDEA
- El IDE debería reconocer automáticamente la estructura del proyecto

3. **Ejecutar la aplicación**
- Localizar la clase `SistemaPetShop.java` en `src/main/`
- Ejecutar el método `main()`

### Uso del Sistema

Al iniciar la aplicación, se mostrará un menú principal con 5 opciones:

1. **Gestionar Clientes y Mascotas**: CRUD completo de clientes, mascotas e historiales
2. **Gestionar Citas**: Agendamiento y administración de turnos
3. **Gestionar Ventas**: Registro y consulta de ventas
4. **Gestionar Proveedores y Pedidos**: Administración de pedidos
5. **Gestionar Productos**: Control de inventario

## 💡 Funcionalidades Destacadas

### Cálculo Dinámico de Costos
El sistema calcula automáticamente los costos de los servicios según:
- Especie de la mascota (perro/gato)
- Servicios adicionales específicos de cada tipo

### Persistencia de Datos
Todos los datos se guardan automáticamente en archivos de texto, permitiendo:
- Recuperación de información entre sesiones
- Backup sencillo mediante copia de archivos
- Formato legible para debugging

### Integridad Referencial
El sistema mantiene la integridad de los datos:
- Al eliminar un cliente, se eliminan sus mascotas asociadas
- Al eliminar una mascota, se eliminan sus historiales médicos

## 📊 Ejemplo de Datos

### Formato de Cliente
```
1,pedro,lopez,1122334444,cabildo 123
```

### Formato de Mascota
```
1,1,julian,perro,bulldog,masculino,5,15.0
```

### Formato de Venta
```
1111;walter;11062025;efectivo;[111,1,15.0,]
```

## 🎯 Conceptos de POO Implementados

- **Encapsulamiento**: Atributos privados con getters/setters
- **Herencia**: Jerarquía de clases (Servicio, Producto)
- **Polimorfismo**: Métodos abstractos y sobrescritura
- **Abstracción**: Clases abstractas para comportamiento común

## 🔮 Futuras Mejoras

- [ ] Migración a base de datos relacional (MySQL/PostgreSQL)
- [ ] Implementación de autenticación de usuarios
- [ ] Reportes en PDF
- [ ] Sistema de notificaciones de citas
- [ ] Dashboard con estadísticas
- [ ] API REST para integración con otras aplicaciones
- [ ] Versión web con Spring Boot

## 👥 Autor

**Tu Nombre**
- GitHub: @valenmendez01
- LinkedIn: https://www.linkedin.com/in/valentin-mendez/

## 📝 Licencia

Este proyecto fue desarrollado con fines educativos como parte de un curso de Programación Orientada a Objetos.

## 🙏 Agradecimientos

Proyecto desarrollado como trabajo práctico del curso de Programación Orientada a Objetos.

---

⭐ Si te gustó este proyecto, no olvides darle una estrella en GitHub!
