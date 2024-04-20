# <span style="color: #4CAF50;">Sistema de Gestión de Puntos de Recogida</span>

Este proyecto implementa un sistema de gestión de puntos de recogida para **AmazingCo**, una empresa especializada en comercio electrónico. El sistema permite manejar diferentes tipos de puntos de recogida, incluyendo **PackageLocker**, **Kiosk** y **PickingPointHub**.

## <span style="color: #A5D6A7;">Características del sistema</span>

- **PackageLocker**: Taquillas autoservicio para recoger y devolver paquetes.
- **Kiosk**: Puntos de recogida en pequeños negocios, con atención personal y posibilidad de pago contra reembolso.
- **PickingPointHub**: Centros de recogida que agrupan varios tipos de puntos de recogida, como PackageLockers y Kiosks.

## <span style="color: #A5D6A7;">Funcionalidades clave</span>

- **Gestión de puntos de recogida**:
  - Añadir o eliminar puntos de recogida.
  - Obtener listas de puntos de recogida operativos y fuera de servicio.
  - Buscar puntos de recogida en un radio específico.
  - Buscar puntos de recogida con espacio disponible.

- **Clases de dominio**:
  - **Package**: Representa un paquete en el sistema, con información sobre código, precio, fecha de almacenamiento, entre otros.
  - **PackageLocker**: Taquillas autoservicio para almacenar paquetes.
  - **Kiosk**: Puntos de recogida atendidos por personal, con capacidad de pago contra reembolso.
  - **PickingPointHub**: Centros de recogida que agrupan PackageLockers y Kiosks.
  - **PostOffice**: Puntos de recogida en oficinas de correo con capacidad ilimitada y entrega "certificada".
  
- **Modificaciones a las clases**:
  - Se han realizado modificaciones a las clases **Package** y **PickingPointSystem** para adaptarlas a las nuevas funcionalidades de los puntos de recogida.

## <span style="color: #A5D6A7;">Pruebas</span>

El proyecto incluye pruebas automatizadas para:
- **PickingPointHubTest**: Pruebas para la clase PickingPointHub.
- **KioskTest**: Pruebas para la clase Kiosk.
- **PickingPointSystemTest**: Pruebas para la clase PickingPointSystem.

## <span style="color: #A5D6A7;">Instrucciones para ejecutar</span>

1. Clone el repositorio en su máquina local.
2. Importe el proyecto en su IDE de Java preferido.
3. Ejecute las pruebas para verificar la funcionalidad.

## <span style="color: #A5D6A7;">Autor</span>

Este proyecto ha sido desarrollado por Jimena Rioja.
